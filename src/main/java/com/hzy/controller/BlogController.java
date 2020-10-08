package com.hzy.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hzy.pojo.*;
import com.hzy.service.*;
import com.hzy.utils.*;
import com.hzy.vo.BlogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private FollowService followService;
    @Autowired
    private ElasticSearchService elasticSearchService;

    @PostMapping("/editor")
    public String Editor(@RequestBody BlogVO blogVO, HttpServletRequest request) {

        String token = request.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.getToken(token);
        int userId = Integer.valueOf(decodedJWT.getClaim("userId").asString());

        return blogService.publishBlog(userId, blogVO);
    }

    @RequestMapping("/detail/{blogId}")
    public Map<String, Object> detail(@PathVariable("blogId") int blogId) {

        blogService.updateHitCountByBlogId(blogId);// 每点击一次，点击量加1
        Blog blog = blogService.selectBlogById(blogId);

        Map<String, Object> map = new HashMap<>();
        // 下面是从数据库中获取到博客，然后转化为html传到前端
        String markdownString = blog.getArticle();
        String html = MarkDownUtil.mdToHtml(markdownString);
        blog.setArticle(html);
        User user = userService.selectUserById(blog.getUserId());
        map.put("blog",blog);
        map.put("user", user);

        return map;
    }

    @RequestMapping("/comment/{blogId}")
    public Map<String, Map<String,Object>> comment(@PathVariable("blogId") int blogId) {
        Map<String,Map<String,Object>> mapMap = new LinkedHashMap<>();
        // 下面是评论的信息，包括评论博客的评论和回复用户的评论
        List<Comment> commentList = commentService.selectCommentByBlogId(blogId);

        if (commentList != null) {
            int i = 0;
            // 此处遍历的评论集合属于一级评论，即评论博客的评论
            for (Comment comment : commentList) {
                User user = userService.selectUserById(comment.getUserId());
                Map<String,Object> map = new HashMap<>();
                // selectChildCommentByCommentId通过队列的形式把所有的回复封装到了allList里
                List<Comment> allList = commentService.selectChildCommentByCommentId(comment.getCommentId());

                Map<String,Map<String,Object>> slaveMap = new LinkedHashMap<>();

                // 判断是否有回复信息，如果有，就遍历回复的评论把用户信息和评论信息绑定到一起
                if (!allList.isEmpty()) {
                    int j = 0;
                    for (Comment comment1 : allList) {
                        Map<String,Object> map1 = new HashMap<>();

                        User user1 = userService.selectUserById(comment1.getUserId());
                        map1.put("user",user1);
                        map1.put("comment",comment1);
                        slaveMap.put("map" + j ++, map1);
                    }
                }
                Map<String, Object> masterMap = new HashMap<>();
                masterMap.put("user", user);
                masterMap.put("comment",comment);

                map.put("masterMap",masterMap);
                map.put("slaveMap", slaveMap);

                mapMap.put("map" + i ++,map);
            }
        }
        return mapMap;
    }

    @RequestMapping("/userinfo/{userId}")
    public Map<String,Object> selectHitCountSumByUserId(@PathVariable("userId") int userId) {
        Map<String,Object> map = new HashMap<>();
        User user = userService.selectUserById(userId);
        int blogCountSum = blogService.selectBlogCountSumByUserId(userId);
        int hitCountSum = blogService.selectHitCountSumByUserId(userId);
        int followCount = followService.selectFollowCountByUserId(userId);
        int fansCount = followService.selectFansCountByUserId(userId);
        int likeCount = blogService.selectLikeCountSumByUserId(userId);
        map.put("user",user);
        map.put("blogCountSum",blogCountSum);
        map.put("hitCountSum",hitCountSum);
        map.put("followCount",followCount);
        map.put("fansCount",fansCount);
        map.put("likeCount",likeCount);

        return map;
    }

    @RequestMapping("/search/{msg}")
    public List<BlogVO> search(@PathVariable("msg") String msg) {
        return elasticSearchService.search(msg);
    }

}

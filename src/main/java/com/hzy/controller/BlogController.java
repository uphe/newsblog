package com.hzy.controller;

import com.hzy.pojo.*;
import com.hzy.service.*;
import com.hzy.utils.*;
import com.hzy.vo.BlogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CommentService commentService;
    @Autowired
    private FollowService followService;
    @Autowired
    private ElasticSearchService elasticSearchService;


    @PostMapping("/user/editor")
    public String Editor(@RequestBody BlogVO blogVO, HttpSession session) {

        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();

        return blogService.publishBlog(userId, blogVO);
    }

    @RequestMapping("/all/detail/{blogId}")
    public BlogVO detail(@PathVariable("blogId") int blogId,HttpSession session) {
//        String readKey = StringUtils.getReadKey(blogId);
//        SetOperations setOperations = redisTemplate.opsForSet();
        User user = (User) session.getAttribute("user");
        if (user != null) {
//            Long add = setOperations.add(readKey, blogId);
            blogService.updateHitCountByBlogId(blogId);// 点击量加1
        }

        BlogVO blogVO = blogService.getBlogVOByUserId(blogId);

        Map<String, Object> map = new HashMap<>();
        // 下面是从数据库中获取到博客，然后转化为html传到前端
        String markdownString = blogVO.getArticle();
        String html = MarkDownUtil.mdToHtml(markdownString);
        blogVO.setArticle(html);

        return blogVO;
    }

    @RequestMapping("/search/{msg}")
    public List<BlogVO> search(@PathVariable("msg") String msg) {
        return elasticSearchService.search(msg);
    }

}

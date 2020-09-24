package com.hzy.controller;

import com.hzy.pojo.Blog;
import com.hzy.pojo.Comment;
import com.hzy.pojo.Type;
import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.service.CommentService;
import com.hzy.service.TypeService;
import com.hzy.service.UserService;
import com.hzy.utils.MarkDownUtil;
import com.hzy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import javax.servlet.http.HttpSession;
@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TypeService typeService;

    @RequestMapping("/toWrite")
    public String toWrite(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Type> typeList = typeService.selectTypeByUserId(user.getUserId());
        model.addAttribute("user", user);
        model.addAttribute("typeList", typeList);
        return "write";
    }

    @PostMapping("/editormd")
    @ResponseBody
    public String Editor(String title, String editormd,
                         @RequestParam(value = "typeString",defaultValue = "") String typeString,
                         @RequestParam(value = "labelString",defaultValue = "") String labelString,
                         HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(editormd) && user != null) {
            Blog blog = new Blog();
            blog.setArticle(editormd);
            blog.setCreateDate(new Date());
            if (editormd.length() > 50) {
                blog.setSummary(editormd.substring(0,50));
            } else {
                blog.setSummary(editormd);
            }
            blog.setTitle(title);
            blog.setUserId(user.getUserId());
            // 这里如果没有分离或标签，默认是空串
            blog.setTypeString(typeString);
            blog.setLabelString(labelString);
            blogService.addBlog(blog);
        }
        return "";
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

//    @RequestMapping("/detail")
//    public String Detail(String content,int userId,int blogId,int parentId){
//        if (userId == 0) {
//            System.out.println("请先登录");
//        }
//        Comment comment = new Comment();
//        comment.setBlogId(blogId);
//        comment.setContent(content);
//        comment.setUserId(userId);
//        comment.setCreateDate(new Date());
//        comment.setParentId(parentId);// parentId代表被回复的评论id
//        commentService.addComment(comment);
//        blogService.updateCommentCountByBlogId(blogService.selectBlogById(blogId).getCommentCount() + 1,blogId);
//        return "{\"msg\":\"success\"}";
//    }

}

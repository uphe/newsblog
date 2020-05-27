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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.http.HttpSession;
@Controller
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

    @PostMapping(path = "/editormd")
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

    @RequestMapping("/toDetail")
    public String toDetail(Model model,HttpSession session,int blogId) {

        blogService.updateHitCountByBlogId(blogId);// 每点击一次，点击量加1
        Blog blog = blogService.selectBlogById(blogId);

        // 下面是从数据库中获取到博客，然后转化为html传到前端
        String markdownString = blog.getArticle();
        String html = MarkDownUtil.mdToHtml(markdownString);
        blog.setArticle(html);
        model.addAttribute("blog", blog);

        // 这是获取到当前登录的用户，并将该用户信息传递到前端
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);

        // 下面是评论的信息，包括评论博客的评论和回复用户的评论
        List<Comment> commentList = commentService.selectCommentByBlogId(blogId);
        if (commentList != null) {
            Map<String,Map<String,Object>> mapMap = new LinkedHashMap<>();
            int i = 0;
            // 此处遍历的评论集合属于一级评论，即评论博客的评论
            for (Comment comment : commentList) {
                user = userService.selectUserById(comment.getUserId());
                Map<String,Object> map = new HashMap<>();
                List<Comment> allList = commentService.selectChildCommentByCommentId(comment.getCommentId());

                Map<String,Map<String,Object>> allMap = new LinkedHashMap<>();
                if (!allList.isEmpty()) {
                    int j = 0;
                    for (Comment comment1 : allList) {
                        Map<String,Object> map1 = new HashMap<>();
                        User user1 = userService.selectUserById(comment1.getUserId());

                        Comment comment2 = commentService.selectCommentByCommentId(comment1.getParentId());
                        User user2 = userService.selectUserById(comment2.getUserId());
                        map1.put("user",user1);
                        map1.put("user1",user2);
                        map1.put("comment",comment1);
                        allMap.put("map" + j ++, map1);
                    }
                }

                map.put("user",user);
                map.put("comment",comment);
                map.put("allMap", allMap);

                mapMap.put("map" + i ++,map);
            }
            model.addAttribute("mapMap",mapMap);
        }

        return "detail";
    }

    @RequestMapping("/detail")
    @ResponseBody
    public String Detail(String content,int userId,int blogId,int parentId, Model model){
        if (userId == 0) {
            System.out.println("请先登录");
        }
        Comment comment = new Comment();
        comment.setBlogId(blogId);
        comment.setContent(content);
        comment.setUserId(userId);
        comment.setCreateDate(new Date());
        comment.setParentId(parentId);// parentId代表被回复的评论id
        commentService.addComment(comment);
        blogService.updateCommentCountByBlogId(blogService.selectBlogById(blogId).getCommentCount() + 1,blogId);
        return "{\"msg\":\"success\"}";
    }

}

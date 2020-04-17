package com.hzy.controller;

import com.hzy.pojo.Blog;
import com.hzy.pojo.Comment;
import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.service.CommentService;
import com.hzy.service.UserService;
import com.hzy.utils.MarkDownUtil;
import com.hzy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import javax.servlet.http.HttpSession;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @RequestMapping("/toWrite")
    public String toWrite(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return "write";
    }

    @PostMapping(path = "/editormd")
    @ResponseBody
    public String Editor(String title, String editormd, HttpSession session) {
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
            blogService.addBlog(blog);
        }
        return "";
    }

    @RequestMapping("/toDetail")
    public String toDetail(Model model,HttpSession session,int blogId) {

        blogService.updateHitCountByBlogId(blogId);
        Blog blog = blogService.selectBlogById(blogId);
        List<Comment> commentList = commentService.selectCommentByBlogId(blogId);

        String markdownString = blog.getArticle();
        String html = MarkDownUtil.mdToHtml(markdownString);
        blog.setArticle(html);

        model.addAttribute("blog", blog);
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);


        if (commentList != null) {
            Map<String,Map<String,Object>> mapMap = new LinkedHashMap<>();
            int i = 0;
            for (Comment comment : commentList) {
                user = userService.selectUserById(comment.getUserId());
                Map<String,Object> map = new HashMap<>();

                map.put("user",user);
                map.put("comment",comment);

                mapMap.put("map" + i ++,map);
            }
            model.addAttribute("mapMap",mapMap);
        }

        return "detail";
    }

    @RequestMapping("/detail")
    @ResponseBody
    public String Detail(String content,int userId,int blogId,Model model){
        if (userId == 0) {
            System.out.println("请先登录");
        }
        Comment comment = new Comment();
        comment.setBlogId(blogId);
        comment.setContent(content);
        comment.setUserId(userId);
        comment.setCreateDate(new Date());
        commentService.addComment(comment);
        blogService.updateCommentCountByBlogId(blogService.selectBlogById(blogId).getCommentCount() + 1,blogId);
        return "{\"msg\":\"success\"}";
    }

}

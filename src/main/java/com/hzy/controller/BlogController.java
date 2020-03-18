package com.hzy.controller;

import com.hzy.pojo.Blog;
import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

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
            blog.setSummary("摘要");
            blog.setTitle(title);
            blog.setUserId(user.getUserId());
            System.out.println(blog.toString());
            blogService.addBlog(blog);
        }
        return "";
    }
}

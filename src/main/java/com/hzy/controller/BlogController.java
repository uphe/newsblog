package com.hzy.controller;

import com.hzy.pojo.Blog;
import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.service.UserService;
import com.hzy.utils.MarkDownUtil;
import com.hzy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

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

    @RequestMapping("/toDetails")
    public String toDetails(Model model,HttpSession session,int blogId) {
        Blog blog = blogService.selectBlogById(blogId);
        String markdownString = blog.getArticle();
        String html = MarkDownUtil.mdToHtml(markdownString);
        blog.setArticle(html);

        model.addAttribute("blog", blog);
        model.addAttribute("user",session.getAttribute("user"));
        return "detail";
    }
}

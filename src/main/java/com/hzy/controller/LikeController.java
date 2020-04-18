package com.hzy.controller;

import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

import static java.lang.Math.toIntExact;

@Controller
public class LikeController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private BlogService blogService;

    @RequestMapping("/like")
    //@ResponseBody
    public String like(int blogId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            int likeCount = toIntExact(likeService.like(user.getUserId(), blogId));
            blogService.updateLikeCountByBlogId(likeCount,blogId);

            //return "{\"msg\":" + likeCount + "}";
        }
        //return "{\"msg\":\"error\"}";
        return "redirect:/";
    }
}

package com.hzy.controller;

import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.service.LikeService;
import com.hzy.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/user/like/{blogId}")
    public ResponseVO like(@PathVariable("blogId") int blogId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        ResponseVO responseVO = likeService.like(userId, blogId);
        return responseVO;
    }
}

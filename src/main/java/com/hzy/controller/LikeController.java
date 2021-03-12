package com.hzy.controller;

import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.service.LikeService;
import com.hzy.vo.BaseResult;
import com.hzy.vo.ResponseVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Tag(name = "点赞", description = "点赞controller")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @GetMapping("/user/like/{blogId}")
    @Operation(summary = "给文章点赞")
    public BaseResult like(@PathVariable("blogId") int blogId, HttpSession session) {
        return likeService.like(blogId, session);
    }
}

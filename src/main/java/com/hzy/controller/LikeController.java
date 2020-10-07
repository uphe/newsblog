package com.hzy.controller;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.service.LikeService;
import com.hzy.utils.JSONUtils;
import com.hzy.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static java.lang.Math.toIntExact;

@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private BlogService blogService;

    @RequestMapping("/like/{blogId}")
    public String like(@PathVariable("blogId") int blogId, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.getToken(token);
        int userId = Integer.valueOf(decodedJWT.getClaim("userId").asString());

        int likeCount = toIntExact(likeService.like(userId, blogId));
        blogService.updateLikeCountByBlogId(likeCount,blogId);
        return JSONUtils.getJSONString(0, "点赞成功");
    }
}

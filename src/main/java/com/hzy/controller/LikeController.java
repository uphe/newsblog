package com.hzy.controller;

import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.service.LikeService;
import com.hzy.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static java.lang.Math.toIntExact;

@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private BlogService blogService;

    @RequestMapping("/like/{blogId}")
    public String like(@PathVariable("blogId") int blogId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();

        int likeCount = toIntExact(likeService.like(userId, blogId));
        // 这里的持久化通过定时任务来实现
        // blogService.updateLikeCountByBlogId(likeCount,blogId);
        return JSONUtils.getJSONString(0, "点赞成功");
    }
}

package com.hzy.controller;

import com.hzy.pojo.Comment;
import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.service.CommentService;
import com.hzy.vo.CommentVO;
import com.hzy.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/comment/{blogId}")
    public List<CommentVO> comment(@PathVariable("blogId") int blogId) {
        // 下面是评论的信息，包括评论博客的评论和回复用户的评论
        List<CommentVO> commentVOS = commentService.selectCommentVOByBlogId(blogId,0,10);

        return commentVOS;
    }

    @PostMapping("/user/publishcomment")
    public ResponseVO publishComment(@RequestBody Comment comment, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();

        comment.setCreateDate(new Date());
        comment.setUserId(userId);
        comment.setStatus(0);
        ResponseVO responseVO = commentService.addComment(comment);
        return responseVO;
    }
}

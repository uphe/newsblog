package com.hzy.controller;

import com.hzy.pojo.Comment;
import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.service.CommentService;
import com.hzy.vo.CommentVO;
import com.hzy.vo.ResponseVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@Tag(name = "评论", description = "评论相关的controller")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/comment/{blogId}")
    @Operation(summary = "通过文章id获取评论信息")
    public List<CommentVO> comment(@PathVariable("blogId") int blogId) {
        // 下面是评论的信息，包括评论博客的评论和回复用户的评论
        List<CommentVO> commentVOS = commentService.selectCommentVOByBlogId(blogId,0,10);

        return commentVOS;
    }

    @PostMapping("/user/publishcomment")
    @Operation(summary = "发表评论")
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

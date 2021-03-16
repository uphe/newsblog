package com.hzy.controller;

import com.hzy.dto.CommentDTO;
import com.hzy.pojo.Comment;
import com.hzy.service.CommentService;
import com.hzy.vo.BaseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Author: hzy
 * @Date: 2020/6/12
 */
@RestController
@Tag(name = "评论", description = "评论相关的controller")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/user/addComment")
    @Operation(summary = "发表评论")
    public BaseResult addComment(@RequestBody Comment comment, HttpSession session) {
        return commentService.addComment(comment, session);
    }

    @GetMapping("/user/deleteCommentByCommentId")
    @Operation(summary = "删除评论")
    public BaseResult deleteCommentByCommentId(@NotNull int commentId) {
        return commentService.deleteCommentByCommentId(commentId);
    }

    @PostMapping("/all/getCommentVOByBlogId")
    @Operation(summary = "通过文章id获取评论信息")
    public BaseResult selectCommentVOByBlogId(@RequestBody @Valid CommentDTO commentDTO) {
        return commentService.selectCommentVOByBlogId(commentDTO);
    }
}

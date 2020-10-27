package com.hzy.controller;

import com.hzy.pojo.Comment;
import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.service.CommentService;
import com.hzy.utils.JSONUtils;
import com.hzy.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @RequestMapping("/comment/{blogId}")
    public List<CommentVO> comment(@PathVariable("blogId") int blogId) {
        // 下面是评论的信息，包括评论博客的评论和回复用户的评论
        List<CommentVO> commentVOS = commentService.selectCommentVOByBlogId(blogId,0,10);

        return commentVOS;
    }

    @RequestMapping("/publishcomment")
    public String publishComment(@RequestBody Comment comment, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();

        comment.setCreateDate(new Date());
        comment.setUserId(userId);
        comment.setStatus(0);

        int result = commentService.addComment(comment);

        if (result > 0) {
            blogService.updateCommentCountByBlogId(commentService.selectCommentCountByBlogId(comment.getBlogId()), comment.getBlogId());
            return JSONUtils.getJSONString(0, "success");
        }
        return JSONUtils.getJSONString(-1,"error");
    }
}

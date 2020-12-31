package com.hzy.service;

import com.hzy.pojo.Comment;
import com.hzy.vo.CommentVO;
import com.hzy.vo.ResponseVO;

import java.util.List;

public interface CommentService {

    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    ResponseVO addComment(Comment comment);

    int selectCommentCountByBlogId(int blogId);

    List<Comment> selectCommentByBlogId(int blogId);

    List<CommentVO> selectCommentVOByBlogId(int blogId, int offset, int limit);


    /**
     * 通过父评论的id找到其所有子评论
     *
     * @param commentId
     * @return
     */
    List<CommentVO> selectChildCommentVOByCommentId(int commentId);

    Comment selectCommentByCommentId(int commentId);

}

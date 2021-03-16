package com.hzy.service;

import com.hzy.dto.CommentDTO;
import com.hzy.pojo.Comment;
import com.hzy.vo.BaseResult;

import javax.servlet.http.HttpSession;

/**
 * @Author: hzy
 * @Date: 2020/6/12
 */
public interface CommentService {

    /**
     * 添加评论
     *
     * @param comment
     * @param session
     * @return
     */
    BaseResult addComment(Comment comment, HttpSession session);

    /**
     * 通过id删除评论
     *
     * @param commentId
     * @return
     */
    BaseResult deleteCommentByCommentId(int commentId);

    /**
     * 获取文章评论
     *
     * @param commentDTO
     * @return
     */
    BaseResult getCommentVOByBlogId(CommentDTO commentDTO);

}

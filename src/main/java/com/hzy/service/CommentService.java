package com.hzy.service;

import com.hzy.dto.CommentDTO;
import com.hzy.pojo.Comment;
import com.hzy.vo.BaseResult;
import com.hzy.vo.CommentVO;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: hzy
 * @Date: 2020/6/12
 */
public interface CommentService {

    /**
     * 添加评论
     *
     * @param comment
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

    int selectCommentCountByBlogId(int blogId);

    List<Comment> selectCommentByBlogId(int blogId);

    /**
     * 获取文章评论
     *
     * @param commentDTO
     * @return
     */
    BaseResult selectCommentVOByBlogId(CommentDTO commentDTO);


    /**
     * 通过父评论的id找到其所有子评论
     *
     * @param commentId
     * @return
     */
    List<CommentVO> selectChildCommentVOByCommentId(int commentId);

    Comment selectCommentByCommentId(int commentId);

}

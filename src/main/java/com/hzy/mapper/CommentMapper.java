package com.hzy.mapper;

import com.hzy.pojo.Comment;
import com.hzy.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: hzy
 * @Date: 2020/6/12
 */
@Mapper
@Repository
public interface CommentMapper {
    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    int addComment(Comment comment);

    /**
     * 通过id删除评论
     *
     * @param commentId
     * @return
     */
    int deleteCommentByCommentId(int commentId);

    List<Comment> selectCommentByBlogId(int blogId);

    /**
     * 查询文章的一级评论
     *
     * @param blogId
     * @param offset
     * @param limit
     * @return
     */
    List<CommentVO> selectParentCommentVOByBlogId(int blogId, int offset, int limit);

    List<CommentVO> selectChildCommentVOByCommentId(int commentId);

    Comment selectCommentByCommentId(int commentId);

    int selectCommentCountByBlogId(int blogId);
}

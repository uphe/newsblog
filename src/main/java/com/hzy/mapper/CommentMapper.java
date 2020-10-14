package com.hzy.mapper;

import com.hzy.pojo.Comment;
import com.hzy.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    int addComment(Comment comment);
    List<Comment> selectCommentByBlogId(int blogId);
    List<CommentVO> selectParentCommentVOByBlogId(int blogId,int offset,int limit);
    List<CommentVO> selectChildCommentVOByCommentId(int commentId);
    Comment selectCommentByCommentId(int commentId);
    int selectCommentCountByBlogId(int blogId);
}

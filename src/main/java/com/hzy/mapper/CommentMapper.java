package com.hzy.mapper;

import com.hzy.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    int addComment(Comment comment);
    List<Comment> selectCommentByBlogId(int blogId);
}

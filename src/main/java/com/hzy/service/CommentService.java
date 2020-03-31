package com.hzy.service;

import com.hzy.mapper.CommentMapper;
import com.hzy.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    public int addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }
    public List<Comment> selectCommentByBlogId(int blogId){
        return commentMapper.selectCommentByBlogId(blogId);
    }


}

package com.hzy.service;

import com.hzy.mapper.BlogMapper;
import com.hzy.mapper.CommentMapper;
import com.hzy.mapper.UserMapper;
import com.hzy.pojo.Blog;
import com.hzy.pojo.Comment;
import com.hzy.pojo.Remind;
import com.hzy.pojo.User;
import org.apache.ibatis.annotations.Arg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RemindService remindService;

    public int addComment(Comment comment) {
        int fromId = comment.getUserId();
        Blog blog = blogMapper.selectBlogById(comment.getBlogId());
        int toId = blog.getUserId();
        int blogId = blog.getBlogId();
        Remind remind = new Remind();
        remind.setRemindType(1);// 代表是评论通知
        remind.setRemindContent(userMapper.selectUserById(fromId).getUsername() + "评论了" +
                blog.getTitle() + ":" + comment.getContent());
        remind.setFromId(fromId);
        remind.setToId(toId);
        remind.setState(0);// 0表示未读
        remind.setBlogId(blogId);
        remind.setCreateDate(new Date());
        remindService.addRemind(remind);
        return commentMapper.addComment(comment);
    }
    public List<Comment> selectCommentByBlogId(int blogId){
        return commentMapper.selectCommentByBlogId(blogId);
    }


}

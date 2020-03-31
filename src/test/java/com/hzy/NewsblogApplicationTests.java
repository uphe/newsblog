package com.hzy;

import com.hzy.mapper.BlogMapper;
import com.hzy.mapper.CommentMapper;
import com.hzy.mapper.TicketMapper;
import com.hzy.mapper.UserMapper;
import com.hzy.pojo.Comment;
import com.hzy.pojo.Ticket;
import com.hzy.pojo.User;
import com.hzy.utils.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
class NewsblogApplicationTests {

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Test
    void contextLoads() {
//        Comment comment = new Comment();
//
//        comment.setUserId(1);
//        comment.setCreateDate(new Date());
//        comment.setContent("hello");
//        comment.setBlogId(1);
//        commentMapper.addComment(comment);
//        System.out.println(commentMapper.selectCommentByBlogId(1));
//        System.out.println(blogMapper.selectCommentCountByBlogId(1));
    }

}

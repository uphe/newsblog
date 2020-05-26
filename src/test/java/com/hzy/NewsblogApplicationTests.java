package com.hzy;

import com.hzy.mapper.*;
import com.hzy.pojo.*;
import com.hzy.utils.JedisUtil;
import com.hzy.utils.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.*;

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
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private ReadNoticeMapper readNoticeMapper;
    @Autowired
    private RemindMapper remindMapper;

    @Test
    void contextLoads() {
        List<Comment> commentList = commentMapper.selectChildCommentByCommentId(1);
        System.out.println(commentList);
        Map<Comment,Object> map = new HashMap<>();
        for (Comment comment : commentList) {
            List<Comment> list = commentMapper.selectChildCommentByCommentId(comment.getCommentId());
            if (list != null) {
                for (Comment comment1 : list) {
                    List<Comment> list1 = commentMapper.selectChildCommentByCommentId(comment1.getCommentId());
                    if (list1 != null) {
                        list.addAll(list1);
                    }
                }
            }
            System.out.println(list);
            map.put(comment,list);
            System.out.println(map);
        }
    }
}

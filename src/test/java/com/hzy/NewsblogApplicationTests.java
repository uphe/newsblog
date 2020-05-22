package com.hzy;

import com.hzy.mapper.*;
import com.hzy.pojo.*;
import com.hzy.utils.JedisUtil;
import com.hzy.utils.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;
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
//        Remind remind = new Remind();
//        remind.setFromId(1);
//        remind.setToId(2);
//        remind.setBlogId(1);
//        remind.setRemindContent("hello");
//        remind.setCreateDate(new Date());
//        remind.setState(0);
//        System.out.println(remindMapper.addRemind(remind));
//        System.out.println(remindMapper.selectRemindByToId(2));
//        remindMapper.updateRemindByRemindId(4);
//        remindMapper.updateRemindByToId(2);
    }
}

package com.hzy;

import com.hzy.mapper.*;
import com.hzy.pojo.Comment;
import com.hzy.pojo.Ticket;
import com.hzy.pojo.Type;
import com.hzy.pojo.User;
import com.hzy.utils.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    @Test
    void contextLoads() {
//        Type type = new Type();
//        type.setTypeName("MySQL");
//        type.setUserId(1);
//        type.setBlogId(1);
//        typeMapper.addType(type);
        List<Type> types = typeMapper.selectTypeByUserId(1);
        for (Type type : types) {
            System.out.println(type);
        }
    }

}

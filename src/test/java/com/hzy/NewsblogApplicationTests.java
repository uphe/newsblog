package com.hzy;

import com.hzy.mapper.TicketMapper;
import com.hzy.mapper.UserMapper;
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
    private UserMapper userMapper;
    @Autowired
    private TicketMapper ticketMapper;
    @Test
    void contextLoads() {
        ticketMapper.updateTicket("e2ba8a94a8f2474bb63a6f74e62e4ead",new Date());

    }

}

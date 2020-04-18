package com.hzy.controller;

import com.hzy.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessageController {
    @Autowired
    private MessageMapper messageMapper;

}

package com.hzy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController {
    @RequestMapping("/toWrite")
    public String toWriteB() {
        return "write";
    }
}

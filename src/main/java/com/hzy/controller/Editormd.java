package com.hzy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Editormd {

    @RequestMapping(path = "/editormd")
    public String Editor(String editormd) {
        System.out.println(editormd);
        return "";
    }
}

package com.hzy.controller;

import com.hzy.pojo.User;
import com.hzy.service.UserService;
import com.hzy.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    @RequestMapping(path = "/reg")
    @ResponseBody
    public String register(@RequestParam("username") String username) {
        // 状态码0为正常
        User user = userService.selectUserByName(username);
        if (user != null) {
            return JSONUtils.getJSONString(1,"用户名已存在");
        }
        return "";
    }

    @RequestMapping(path = "/register")
    public String register(Model model,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password) {

        String registerMsg = userService.register(username,password);
        if (registerMsg != null) {
            model.addAttribute("registerMsg",registerMsg);
            return "register";
        }
        return "redirect:/toLogin";
    }
}

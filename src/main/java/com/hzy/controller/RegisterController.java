package com.hzy.controller;

import com.hzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
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

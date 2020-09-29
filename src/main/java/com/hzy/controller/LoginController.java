package com.hzy.controller;

import com.hzy.pojo.User;
import com.hzy.service.UserService;
import com.hzy.utils.JSONUtils;
import com.hzy.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(HttpServletResponse response, @RequestBody User usernameAndPassword) {
        if (usernameAndPassword == null) {
            return JSONUtils.getJSONString(-1,"参数不能为空");
        }

        String username = usernameAndPassword.getUsername();
        String password = usernameAndPassword.getPassword();

        return userService.login(response, username, password);
    }

    @RequestMapping(path = "/register")
    public String register(@RequestBody User usernameAndPassword) {
        if (usernameAndPassword == null) {
            return JSONUtils.getJSONString(-1,"参数不能为空");
        }

        String username = usernameAndPassword.getUsername();
        String password = usernameAndPassword.getPassword();

        return userService.register(username,password);
    }

}

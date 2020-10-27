package com.hzy.controller;

import com.hzy.pojo.User;
import com.hzy.service.TokenService;
import com.hzy.service.UserService;
import com.hzy.utils.DateUtil;
import com.hzy.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.util.Date;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @RequestMapping("/login")
    public String login(HttpSession session, @RequestBody User usernameAndPassword) {
        if (usernameAndPassword == null) {
            return JSONUtils.getJSONString(-1,"参数不能为空");
        }

        String username = usernameAndPassword.getUsername();
        String password = usernameAndPassword.getPassword();

        return userService.login(session, username, password);
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

    @RequestMapping("/islogin")
    public String isLogin(HttpServletResponse response, HttpServletRequest request) {
        try {
            String token = request.getHeader("token");
            if (tokenService.selectTicketByRandomTicket(token).getExpired().after(new Date())) {
                tokenService.updateToken(token, DateUtil.afterSevenDay());
                return JSONUtils.getJSONString(0,"is login");
            }




        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return JSONUtils.getJSONString(-1,"is not login");
    }
}

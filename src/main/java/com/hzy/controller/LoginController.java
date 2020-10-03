package com.hzy.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hzy.pojo.User;
import com.hzy.service.UserService;
import com.hzy.utils.JSONUtils;
import com.hzy.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping("/islogin/{token}")
    public String isLogin(@PathVariable("token") String token, HttpServletResponse response) {
        try {
            JWTUtils.verify(token);
            DecodedJWT decodedJWT = JWTUtils.getToken(token);

            // 将用户的信息封装到token中
            Map<String, String> payload = new HashMap<>();
            payload.put("userId",decodedJWT.getClaim("userId").asString());
            payload.put("username",decodedJWT.getClaim("username").asString());
            payload.put("headUrl",decodedJWT.getClaim("headUrl").asString());
            payload.put("userType",decodedJWT.getClaim("userType").asString());

            response.addHeader("token",JWTUtils.getToken(payload));

            return JSONUtils.getJSONString(0,"is login");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return JSONUtils.getJSONString(-1,"is not login");
    }
}

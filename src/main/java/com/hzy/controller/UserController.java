package com.hzy.controller;

import com.hzy.dto.UserDTO;
import com.hzy.service.UserService;
import com.hzy.utils.JSONUtils;
import com.hzy.vo.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public BaseResult login(@RequestBody UserDTO userDTO, HttpServletResponse response, HttpSession session) {
        return userService.login(userDTO, response, session);
    }

    @PostMapping(path = "/register")
    public String register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @GetMapping("/isLogin")
    public BaseResult isLogin(HttpServletRequest request) {
        return userService.isLogin(request);
    }

    @GetMapping("/user/logout")
    public BaseResult logout(HttpServletRequest request) {
        return userService.logout(request);
    }

    @GetMapping("/user/info/{userId}")
    public BaseResult selectUserInfoByUserId(@PathVariable("userId") int userId) {
        return userService.selectUserInfoByUserId(userId);
    }

}

package com.hzy.controller;

import com.hzy.dto.UserDTO;
import com.hzy.service.UserService;
import com.hzy.utils.JSONUtils;
import com.hzy.vo.BaseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@Tag(name = "用户", description = "用户controller")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @Operation(summary = "登录")
    public BaseResult login(@RequestBody UserDTO userDTO, HttpServletResponse response, HttpSession session) {
        return userService.login(userDTO, response, session);
    }

    @PostMapping(path = "/register")
    @Operation(summary = "注册")
    public String register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @GetMapping("/isLogin")
    @Operation(summary = "判断是否登录")
    public BaseResult isLogin(HttpServletRequest request) {
        return userService.isLogin(request);
    }

    @GetMapping("/user/logout")
    @Operation(summary = "登出")
    public BaseResult logout(HttpServletRequest request) {
        return userService.logout(request);
    }

    @GetMapping("/user/info/{userId}")
    @Operation(summary = "通过用户id查询用户信息")
    public BaseResult selectUserInfoByUserId(@PathVariable("userId") int userId) {
        return userService.selectUserInfoByUserId(userId);
    }

}

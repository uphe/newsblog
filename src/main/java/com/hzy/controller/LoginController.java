package com.hzy.controller;

import com.hzy.pojo.User;
import com.hzy.service.TokenService;
import com.hzy.service.UserService;
import com.hzy.utils.DateUtil;
import com.hzy.utils.JSONUtils;
import com.hzy.vo.ResponseVO;
import com.hzy.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.util.Date;

@RestController
@Slf4j
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @RequestMapping("/login")
    public ResponseVO login(HttpServletResponse response, HttpSession session, @RequestBody User usernameAndPassword) {
        if (usernameAndPassword == null) {
            ResponseVO responseVO = new ResponseVO();
            responseVO.setCode(-1);
            responseVO.setMsg("参数不能为空");
            return responseVO;
        }

        String username = usernameAndPassword.getUsername();
        String password = usernameAndPassword.getPassword();

        return userService.login(response, session, username, password);
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

    @RequestMapping("/user/logout")
    public String logout(HttpServletRequest request) {
        logger.info("执行退出操作");
        String token = request.getHeader("token");
        tokenService.updateToken(token,new Date());
        return JSONUtils.getJSONString(0,"退出成功");
    }
}

package com.hzy.controller;

import com.hzy.pojo.Ticket;
import com.hzy.pojo.User;
import com.hzy.service.TicketService;
import com.hzy.service.UserService;
import com.hzy.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.*;
import javax.xml.transform.Source;
import java.util.Date;
import java.util.UUID;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(Model model, HttpSession session,
                        HttpServletResponse response,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value = "rememberme",defaultValue = "off") String rememberme ) {
        String loginMsg = userService.login(username, password);
        if (loginMsg != null) { //说明有问题
            model.addAttribute("loginMsg",loginMsg);
            return "/login";
        }
        User user = userService.selectUserByName(username);
        if (!rememberme.equals("off")) { //记住密码，这里记住7天
            String randomTicket = UUID.randomUUID().toString().replaceAll("-","");
            Cookie cookie = new Cookie("randomTicket", randomTicket);
            cookie.setMaxAge(60*60*24*7);
            response.addCookie(cookie);

            Ticket ticket = new Ticket();
            Date date = new Date();
            date.setTime(date.getTime() + 1000*60*60*24*7);

            ticket.setRandomTicket(randomTicket);
            ticket.setExpired(date);
            ticket.setUserId(user.getUserId());
            ticketService.addTicket(ticket);

        } else { //这是没有记住密码
            String randomTicket = UUID.randomUUID().toString().replaceAll("-","");
            Cookie cookie = new Cookie("randomTicket", randomTicket);
            //cookie.setMaxAge(60*60*24*7);
            response.addCookie(cookie);

            Ticket ticket = new Ticket();
            Date date = new Date();
            date.setTime(date.getTime() + 1000*60*60*24);

            ticket.setRandomTicket(randomTicket);
            ticket.setExpired(date);
            ticket.setUserId(user.getUserId());
            ticketService.addTicket(ticket);

        }
        // 用户登录成功，把信息放到session里

        session.setAttribute("user",user);

        // 重定向过去，url里没有用户信息
        return "redirect:/";
    }

    @RequestMapping("/user/logout")
    public String logout(Model model, HttpServletRequest request,HttpSession session) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("randomTicket")) {
                    cookie.setMaxAge(0);
                    ticketService.updateTicket(cookie.getValue(), new Date());
                    session.setAttribute("user",null);
                    model.addAttribute("user",null);
                    return "redirect:/";
                }
            }
        }
        return "redirect:/";
    }
}

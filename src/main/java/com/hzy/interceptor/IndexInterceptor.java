package com.hzy.interceptor;

import ch.qos.logback.classic.turbo.TurboFilter;
import com.hzy.pojo.Ticket;
import com.hzy.pojo.User;
import com.hzy.service.TicketService;
import com.hzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/*
 * 配置一个拦截器，并将该拦截器注入到Spring中，该拦截器默认是什么都不拦截
 * 外面需要一个配置类，去配置该拦截器的拦截路径
 * */
@Component
public class IndexInterceptor implements HandlerInterceptor {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;

    /*
     * preHandle：在控制器(controller)前执行，返回值表示是否中断后续执行
     * 当返回值为true时表示继续向下执行，为false时会中断后续所有操作
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return true;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("randomTicket")) {
                Ticket ticket = ticketService.selectTicketByRandomTicket(cookie.getValue());
                if (ticket != null && ticket.getExpired().after(new Date()) && ticket.getRandomTicket().equals(cookie.getValue())) {
                    User user = userService.selectUserById(ticket.getUserId());
                    HttpSession session = request.getSession();
                    session.setAttribute("user",user);
                }
                return true;
            }
        }
        // 这里只是判断一下用户是否保存了密码，如果保存了，就直接给登录上了
        // 如果没保存，也能进入到首页
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

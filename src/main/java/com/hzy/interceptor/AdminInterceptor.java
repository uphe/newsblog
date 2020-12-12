package com.hzy.interceptor;

import com.hzy.pojo.Token;
import com.hzy.pojo.User;
import com.hzy.service.TokenService;
import com.hzy.service.UserService;
import com.hzy.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    /*
     * preHandle：在控制器(controller)前执行，返回值表示是否中断后续执行
     * 当返回值为true时表示继续向下执行，为false时会中断后续所有操作
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if (token != null) {
            Token token1 = tokenService.selectTokenByToken(token);
            if (token1 != null && token1.getExpired().after(new Date()) && token1.getToken().equals(token)) {
                User user = userService.selectUserById(token1.getUserId());
                // 1是管理员
                if (user.getUserType() == 1 || user.getUserType() == 2) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    return true;
                }
            }
        }
        // 需要跳转到登录
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(JSONUtils.getJSONString(-1, "The Token is wrong"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

package com.hzy.interceptor;

import com.hzy.utils.JWTUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 配置一个拦截器，并将该拦截器注入到Spring中，该拦截器默认是什么都不拦截
 * 外面需要一个配置类，去配置该拦截器的拦截路径
 * */
@Component
public class JWTInterceptor implements HandlerInterceptor {

    /*
     * preHandle：在控制器(controller)前执行，返回值表示是否中断后续执行
     * 当返回值为true时表示继续向下执行，为false时会中断后续所有操作
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 获取请求头中的令牌
        String token = request.getHeader("token");
        try {
            JWTUtils.verify(token);// 验证令牌
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}

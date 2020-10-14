package com.hzy.interceptor;

import com.hzy.utils.JSONUtils;
import com.hzy.utils.JWTUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        try {
            // 获取请求头中的令牌
            String token = request.getHeader("token");
            JWTUtils.verify(token);// 验证令牌，成功直接放行，失败不放行，并返回给前端一个JSON串
            return true;
        } catch (Exception e) {
//            System.out.println(e.getMessage());

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.write(JSONUtils.getJSONString(-1,"The Token is wrong"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return false;
    }

}

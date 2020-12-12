package com.hzy.configuration;

import com.hzy.interceptor.AdminInterceptor;
import com.hzy.interceptor.AllInterceptor;
import com.hzy.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //注入到Spring
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private UserInterceptor userInterceptor;
    @Autowired
    private AllInterceptor allInterceptor;
    @Autowired
    private AdminInterceptor adminInterceptor;

    /*
     * 配置indexInterceptor拦截器的拦截路径
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/user/**", "/msg/**");
        registry.addInterceptor(allInterceptor)
                .addPathPatterns("/all/**");
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**");
    }

}

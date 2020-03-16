package com.hzy.configuration;

import com.hzy.interceptor.IndexInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //注入到Spring
public class WebConfiguration implements WebMvcConfigurer {
    @Autowired
    private IndexInterceptor indexInterceptor;

    /*
    * 配置indexInterceptor拦截器的拦截路径，这里只拦截了首页
    * 用于判断用户是否保存了密码
    * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(indexInterceptor)
                .addPathPatterns("/","/index","index.html");
    }
}

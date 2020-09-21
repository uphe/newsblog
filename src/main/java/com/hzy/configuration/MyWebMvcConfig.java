package com.hzy.configuration;

import com.hzy.interceptor.IndexInterceptor;
import com.hzy.interceptor.WriteInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //注入到Spring
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private IndexInterceptor indexInterceptor;
    @Autowired
    private WriteInterceptor writeInterceptor;

    /*
    * 配置indexInterceptor拦截器的拦截路径，这里只拦截了首页
    * 用于判断用户是否保存了密码
    * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(indexInterceptor)
                .addPathPatterns("/","/index","index.html");

        registry.addInterceptor(writeInterceptor)
                .addPathPatterns("/toWrite","/editormd");
    }

    /**
     * 配置跨域访问
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("*");
    }
}

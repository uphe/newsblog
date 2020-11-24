package com.hzy.configuration;
import com.hzy.interceptor.AdminInterceptor;
import com.hzy.interceptor.AllInterceptor;
import com.hzy.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
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
     * 配置indexInterceptor拦截器的拦截路径，这里只拦截了首页
     * 用于判断用户是否保存了密码
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/user/**","/msg/**");
        registry.addInterceptor(allInterceptor)
                .addPathPatterns("/all/**");
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**");
    }



}

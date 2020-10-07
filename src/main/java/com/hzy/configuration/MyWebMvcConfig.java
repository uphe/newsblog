package com.hzy.configuration;

import com.hzy.interceptor.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //注入到Spring
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private JWTInterceptor jwtInterceptor;

    /*
    * 配置indexInterceptor拦截器的拦截路径，这里只拦截了首页
    * 用于判断用户是否保存了密码
    * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/editormd/**", "/like/**", "/uploadImage/**");
    }


    /**
     * 配置跨域访问
     * @return
     */
    private CorsConfiguration corsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedOrigin("*");// 同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
        corsConfiguration.addAllowedHeader("*"); // header，允许哪些header
        corsConfiguration.addAllowedMethod("*"); // 允许的请求方法，PSOT、GET、PUT等
        corsConfiguration.addExposedHeader("token");// 拓展header 浏览器放过redponse的token 不然跨域登录收不到token
        corsConfiguration.setAllowCredentials(true);// 允许浏览器携带cookie

        return corsConfiguration;
    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig());
        return new CorsFilter(source);
    }
}

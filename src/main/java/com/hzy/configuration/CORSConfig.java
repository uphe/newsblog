package com.hzy.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author: hzy
 * @Date: 2020/11/24
 */
@Configuration
public class CORSConfig {
    /**
     * 配置跨域访问
     *
     * @return
     */
    private CorsConfiguration corsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // 同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
        corsConfiguration.addAllowedOrigin("*");
        // header，允许哪些header
        corsConfiguration.addAllowedHeader("*");
        // 允许的请求方法，PSOT、GET、PUT等
        corsConfiguration.addAllowedMethod("*");
        // 拓展header 浏览器放过response的token 不然跨域登录收不到token
        corsConfiguration.addExposedHeader("token");
        // 允许浏览器携带cookie
        corsConfiguration.setAllowCredentials(true);

        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig());
        return new CorsFilter(source);
    }
}

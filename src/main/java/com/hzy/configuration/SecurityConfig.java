//package com.hzy.configuration;
//
//import com.hzy.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.firewall.DefaultHttpFirewall;
//import org.springframework.security.web.firewall.HttpFirewall;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@Configuration
//@EnableWebSecurity //开启security安全框架
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserService userService;
//
//    // 授权
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests() //设置访问权限
//            //.antMatchers(""/home/**").permitAll()  //任何用户都可以访问，可以不登录
//            //.antMatchers("/home/**").hasAnyRole("admin","user") //admin和user 都有权访问
//            //.antMatchers("/home/**").hasRole("admin")  //只有admin有权访问
//            .antMatchers("/follow/**").hasRole("0")  //只有user有权访问
//            .antMatchers("/recommend/**").hasRole("0") // 只有用户
//            //.anyRequest().authenticated() //任何服务 登陆后才可以访问
//
//            //尚未登陆提示  不要给login_page设置任何访问权限
//            .and()
//                .formLogin()
//                .loginPage("http://10.101.76.63:8080/login")
//                .loginProcessingUrl("/login")
////                .defaultSuccessUrl("/login_success")
////                .failureUrl("/login_error")
//                .permitAll()
//            //允许退出登录
//            .and()
//                .logout().logoutSuccessUrl("/logout_success").permitAll()
//            //开启跨域 cors()
//            .and()
//                .cors().configurationSource(corsConfigurationSource())
//            .and()
//                .csrf().disable();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth
//                //配置 UserDetailsService 实现类，实现自定义登录校验
////                .userDetailsService(dbUserDetailService)
//                //配置密码加密规则
////                .passwordEncoder(passwordEncoder());
//    }
//    /**
//     * 密码加密，必须为 @Bean ，否则报错
//     *     作用：实例化密码加密规则，该规则首先会校验数据库中存储的密码是否符合其规则（经过 BCryptPasswordEncoder 加密的密码
//     * 的字符串符合一定的规则）：
//     *     1.若不符合，直接报错；
//     *     2.若符合，则会将前端传递的密码经过 BCryptPasswordEncoder 加密，再和数据库中的密码进行比对，一样则通过
//     *     所以，这里要求，我们存入进数据库的密码不能是明文，而必须是经过 BCryptPasswordEncoder 加密后，才能存入数据库
//     */
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    //设置无需权限就可以访问的资源
////    @Override
////    public void configure(WebSecurity web) throws Exception {
////        web.ignoring().antMatchers("/static/**");
////    }
//
//    //允许多请求地址多加斜杠  比如 /msg/list   //msg/list
//    @Bean
//    public HttpFirewall httpFirewall() {
//        return new DefaultHttpFirewall();
//    }
//
//    //spring security 配置跨域访问资源
//    private CorsConfigurationSource corsConfigurationSource() {
//        CorsConfigurationSource source =   new UrlBasedCorsConfigurationSource();
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("*"); //同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
//        corsConfiguration.addAllowedHeader("*");  //header，允许哪些header
//        corsConfiguration.addAllowedMethod("*");  //允许的请求方法，PSOT、GET、PUT等
//        corsConfiguration.addExposedHeader("token"); //拓展header 浏览器放过redponse的token 不然跨域登录收不到token
//        corsConfiguration.setAllowCredentials(true); //允许浏览器携带cookie
//        ((UrlBasedCorsConfigurationSource) source).registerCorsConfiguration("/**",corsConfiguration); //配置允许跨域访问的url
//        return source;
//    }
//}

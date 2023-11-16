package com.origin.admin.common.config;

import com.origin.admin.common.constant.ControllerConstant;
import com.origin.admin.modules.system.filter.JwtAuthenticationFilter;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.security.AuthProvider;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description: 安全核心配置类
 * @Date 2023/11/14 9:54
 */
@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            //禁用basic明文验证
            .httpBasic(AbstractHttpConfigurer::disable)
            //前后端分离架构不需要csrf保护
            .csrf(AbstractHttpConfigurer::disable)

            //禁用默认登录页面
            .formLogin(AbstractHttpConfigurer::disable)
            //禁用默认退出页面
            .logout(AbstractHttpConfigurer::disable)

            //前后端分离是无状态的，不需要session了，直接禁用
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.GET,WhiteConfig.whiteList()).permitAll()
                                .anyRequest().authenticated()
                                .anyRequest()
                        );
            //异常时认证处理流程
//                .exceptionHandling(exeConfig -> {
//                    exeConfig.authenticationEntryPoint(authenticationEntryPoint())
//                })
            // 添加过滤器
//                .addFilterBefore(authTokenFilter(), CsrfFilter.class);
        return http.build();

    }



    @Bean
    public OncePerRequestFilter authTokenFilter () {
        return new JwtAuthenticationFilter();
    }

}

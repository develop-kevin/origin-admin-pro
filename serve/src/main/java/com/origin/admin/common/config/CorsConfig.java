package com.origin.admin.common.config;

import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/16 13:43
 */
//@Configuration
public class CorsConfig {
//    @Bean
//    public CorsFilter corsFilter(){
//        //1、创建CORS 配置对象
//        CorsConfiguration config = new CorsConfiguration();
//        //支持域
//        config.addAllowedOriginPattern("*");
//        // * 表示对所有的地址都可以访问
//        config.addAllowedOrigin("*");
//        //  跨域的请求头
//        config.addAllowedHeader("*");
//        //支持请求方式
//        config.addAllowedMethod("*");
//        //是否发送Cookie
//        config.setAllowCredentials(true);
//        //2、添加地址映射
//        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        corsConfigurationSource.registerCorsConfiguration("/**",config);
//        //3、返回CorsFilter 对象
//        return new CorsFilter(corsConfigurationSource);
//    }
}

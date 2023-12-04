package com.origin.admin.common.config;

import com.origin.admin.common.web.Interceptor.CheckTokenInterceptor;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/27 15:11
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private CheckTokenInterceptor checkTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(checkTokenInterceptor)
                .addPathPatterns("/**") //拦截所有请求
                .excludePathPatterns(WhiteConfig.whiteList());//放行
    }
}

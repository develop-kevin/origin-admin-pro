package com.origin.admin.common.config;

import com.origin.admin.common.constant.ControllerConstant;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/16 13:50
 */
public class WhiteConfig {
    public static String[] whiteList (){
        return new String[]{
                ControllerConstant.PREFIX_SYSTEM+"auth/**",
                ControllerConstant.PREFIX_CAPTCHA+"/**",
                "/webjars/**",
                "/doc.html",
                "/swagger-ui/**",
                "/v3/api-docs/**"
        };
    }
}

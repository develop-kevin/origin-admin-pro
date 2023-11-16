package com.origin.admin.common.constant;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/13 18:37
 */
public class SecurityConstant {

    /**
     * 登录接口方式
     * */
    public static final String LOGIN_METHOD = "POST";

    /**
     * 默认的登录接口
     * */
    public static final String LOGIN_URL = "/admin/auth/login";

    /**
     * 默认的注销接口
     * */
    public static final String LOGOUT_URL = "/admin/auth/logout";


    public static final Long CATCHA_EXPIRATION = 1000 * 60 * 5L;

    public static final String BEARER_CODE = "Bearer ";

    /**
     * 是否开启验证码
     * */
    public static final Boolean IS_CAPTCHA_VERIFICATION = true;
}

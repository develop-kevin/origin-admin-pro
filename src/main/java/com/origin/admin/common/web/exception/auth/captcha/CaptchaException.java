package com.origin.admin.common.web.exception.auth.captcha;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:  验证码异常基类
 * @Date 2023/11/13 18:55
 */
public class CaptchaException extends AuthenticationException {
    public CaptchaException(String msg) {
        super(msg);
    }
}

package com.origin.admin.common.web.exception.auth.captcha;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description: 验证码过期异常
 * @Date 2023/11/13 18:58
 */
public class CaptchaExpiredException extends CaptchaException{
    public CaptchaExpiredException(String msg) {
        super(msg);
    }
}

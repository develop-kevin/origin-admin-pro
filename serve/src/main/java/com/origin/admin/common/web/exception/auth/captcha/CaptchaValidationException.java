package com.origin.admin.common.web.exception.auth.captcha;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description: 验证码验证异常
 * @Date 2023/11/13 18:59
 */
public class CaptchaValidationException extends CaptchaException{
    public CaptchaValidationException(String msg) {
        super(msg);
    }
}

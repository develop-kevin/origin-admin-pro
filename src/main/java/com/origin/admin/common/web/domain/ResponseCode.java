package com.origin.admin.common.web.domain;


public enum ResponseCode implements StatusCode {


    SUCCESS(200, "成功"),
    FAILURE(500, "失败"),

    SYSTEM_ERROR(500,"系统错误"),

    LOGIN_SUCCESS(200, "登录成功"),
    LOGIN_FAILURE(500, "登录失败"),
    LOGOUT_SUCCESS(200,"注销成功"),
    NOT_LOGIN(401,"未登录"),
    NOT_PERMISSION(403, "未授权"),

    CAPTCHA_EXPIRED(500,"验证码过期"),

    CAPTCHA_ERROR(500,"验证码错误"),
    CAPTCHA_INVALID(500,"验证码无效"),
    CAPTCHA_KEY_MISSION(500,"验证码 KEY 缺失"),
    CAPTCHA_CODE_MISSION(500,"验证码 CODE 缺失"),

    TOKEN_INVALID(501, "Token 无效"),
    TOKEN_EXPIRED(502, "Token 过期"),
    TOKEN_MISSION(503, "Token 缺失"),

    REPEAT_SUBMIT(701,"不允许重复提交，请稍后再试"),

    USER_EXPIRED(604,"账户过期"),
    USER_BAD_CREDENTIALS(603,"密码不正确"),
    USER_USERNAME_NOT_FOUND(602,"用户不存在"),
    USER_LOCKED(601,"用户锁定"),
    USER_NOT_ENABLE(605,"用户未启用");

    private final Integer code;
    private final String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
package com.origin.admin.common.web.domain;


public enum ResponseCode implements StatusCode {


    SUCCESS(20000, "成功"),
    FAILURE(20001, "失败"),


    CREATE_SUCCESS(20000, "新增成功"),

    CREATE_FAILURE(20001, "新增失败"),

    UPDATE_SUCCESS(20000, "更新成功"),

    UPDATE_FAILURE(20001, "更新失败"),

    DELETE_SUCCESS(20000, "删除成功"),

    DELETE_FAILURE(20001, "删除失败"),

    QUERY_SUCCESS(20000, "查询成功"),

    QUERY_FAILURE(20001, "查询失败"),

    SYSTEM_ERROR(50000,"系统错误"),

    LOGIN_SUCCESS(20000, "登录成功"),
    LOGIN_FAILURE(20001, "登录失败"),
    LOGOUT_SUCCESS(20000,"注销成功"),
    NOT_LOGIN(40001,"未登录"),
    NOT_PERMISSION(40003, "未授权"),

    CAPTCHA_EXPIRED(40004,"验证码过期"),

    CAPTCHA_ERROR(40005,"验证码错误"),
    CAPTCHA_INVALID(40006,"验证码无效"),
    CAPTCHA_KEY_MISSION(40007,"验证码 KEY 缺失"),
    CAPTCHA_CODE_MISSION(40008,"验证码 CODE 缺失"),

    CAPTCHA_CODE_ISNULL(40009,"验证码 CODE 为NULL"),

    TOKEN_INVALID(50001, "Token 无效"),
    TOKEN_EXPIRED(50002, "Token 过期"),
    TOKEN_MISSION(50003, "Token 缺失"),

    REPEAT_SUBMIT(70001,"不允许重复提交，请稍后再试"),

    USER_EXPIRED(60001,"账户过期"),
    USER_BAD_CREDENTIALS(60002,"密码不正确"),
    USER_USERNAME_NOT_FOUND(60003,"用户不存在"),

    USER_USERNAME_FOUND(60004,"用户已存在"),
    USER_LOCKED(60005,"用户锁定"),
    USER_NOT_ENABLE(60006,"用户未启用"),

    TITLE_NOT_FOUND(70001,"名称不存在"),

    TITLE_FOUND(70002,"名称已存在");


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
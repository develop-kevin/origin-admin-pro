package com.origin.admin.common.web.exception.auth.jwt;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/16 11:11
 */
public class JwtException extends RuntimeException{
    public JwtException(String msg) {
        super(msg);
    }
}

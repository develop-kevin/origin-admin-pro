package com.origin.admin.common.web.exception.base;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/13 22:20
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String msg){
        super(msg);
    }
}

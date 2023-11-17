package com.origin.admin.common.web.exception;


import com.origin.admin.common.web.domain.Response;
import com.origin.admin.common.web.exception.base.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLSyntaxErrorException;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description: 全局异常
 * @Date 2023/11/13 21:08
 */
@Slf4j
@Order(2)
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 不 支 持 的 请 求 类 型
     * */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response<Object> handlerException(HttpRequestMethodNotSupportedException e){
        log.warn("不支持请求类型，错误信息:{}",  e.getMessage());
        return Response.fail("不支持"+e.getMethod()+"请求");
    }

    /**
     * 未 知 的 运 行 时 异 常
     * @param e 异常
     * @return Response
     */
    @ExceptionHandler(RuntimeException.class)
    public Response<Object> notFount(RuntimeException e){
        log.warn("未发现，错误信息:{}",  (Object) e.getStackTrace());
        return Response.fail("运行时异常："+e.getMessage());
    }

    /**
     * 未 知 的 运 行 时 异 常
     * @param e 异常
     * @return Response
     */
    @ExceptionHandler(SQLSyntaxErrorException.class)
    public Response<Object> notColumn(RuntimeException e){
        log.warn("列不存在，错误信息:{}", (Object) e.getStackTrace());
        return Response.fail("列不存在："+e.getMessage());
    }

    /**
     * 系统异常
     * @param e Exception
     * @return Response
     */
    @ExceptionHandler(Exception.class)
    public Response<Object> handleException(Exception e){
        log.warn("系统异常，错误信息:{}", (Object) e.getStackTrace());
        return Response.fail("服务器错误，请联系管理员");
    }


    /**
     * 业务异常
     * @param request HttpServletRequest
     * @param e BusinessException
     * @return Response
     */
    @ExceptionHandler(BusinessException.class)
    public Response<Object> businessException(HttpServletRequest request, BusinessException e){
        log.warn("业务异常，错误信息:{}", e.getMessage());
        return Response.fail(e.getMessage());
    }
}

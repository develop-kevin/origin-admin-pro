package com.origin.admin.common.web.exception;

import com.origin.admin.common.web.domain.Response;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Set;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/13 22:26
 */
@Order(1)
@RestControllerAdvice
public class ValidationExceptionHandler {
    /**
     * 处理form data方式调用接口校验失败抛出异常信息
     * @param e BindException
     * @return Response
     */
    @ExceptionHandler(BindException.class)
    public Response<Object> bindExceptionHandler(BindException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        return Response.fail(400,"Bad Request",collect);
    }

    /**
     * 处理json请求体调用接口校验失败抛出异常
     * @param e MethodArgumentNotValidException
     * @return Response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        return Response.fail(400,"Bad Request",collect);
    }

    /**
     * 处理单个参数检验失败抛出异常
     * @param e ConstraintViolationException
     * @return Response
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Response<Object> constraintViolationExceptionHandler(ConstraintViolationException e){
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> collect = constraintViolations.stream().map(ConstraintViolation::getMessage).toList();
        return Response.fail(400,"Bad Request",collect);
    }
}

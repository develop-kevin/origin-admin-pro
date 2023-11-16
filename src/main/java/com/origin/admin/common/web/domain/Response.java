package com.origin.admin.common.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "统一结果集处理器")
public class Response<T> {

    /**
     * 状态码
     * */
    private Integer code;

    /**
     * 提示消息
     * */
    private String msg;

    /**
     * 返回结果
     * */
    private boolean success;

    /**
     * 携带数据
     * */
    private Object data;

    /**
     * 时间戳
     * */
    @JsonProperty(value = "time_stamp")
    private long timeStamp = System.currentTimeMillis();

    /**
     * 成 功 操 作
     * */
    public static<T> Response<T> success(){
        return success("");
    }

    /**
     * 成 功 操 作 , 携 带 数 据
     * */
    public static<T> Response<T> success(T data){
        return success(ResponseCode.SUCCESS.getMessage(),data);
    }

    /**
     * 成 功 操 作, 携 带 消 息
     * */
    public static<T> Response<T> success(String message){
        return success(message,null);
    }

    /**
     * 成 功 操 作, 携 带 消 息 和 携 带 数 据
     * */
    public static<T> Response<T> success(String message,T data){
        return success(ResponseCode.SUCCESS.getCode(),message,data);
    }

    /**
     * 成 功 操 作, 携 带 自 定 义 状 态 码 和 消 息
     * */
    public static<T> Response<T> success(ResponseCode ResponseCode){
        return success(ResponseCode.getCode(),ResponseCode.getMessage());
    }

    /**
     * 成 功 操 作, 携 带 自 定 义 状 态 码 和 消 息
     * */
    public static<T> Response<T> success(ResponseCode ResponseCode,String tokenKey,String token){
        Response<T> response = success(ResponseCode.getCode(),ResponseCode.getMessage());
        response.setTokenKey(tokenKey);
        response.setToken(token);
        return response;
    }

    public static<T> Response<T> success(int code,String message){
        return success(code,message,null);
    }

    /**
     * 成 功 操 作, 携 带 自 定义 状 态 码, 消 息 和 数 据
     * */
    public static<T> Response<T> success(int code,String message,T data){
        return response(code,message,data,true);
    }

    /**
     * 失 败 操 作, 默 认 数 据
     * */
    public static<T> Response<T> fail(){
        return fail(ResponseCode.FAILURE.getMessage());
    }

    /**
     * 失 败 操 作, 携 带 自 定 义 消 息
     */
    public static<T> Response<T> fail(String message){
        return fail(message,null);
    }

    /**
     * 失 败 操 作 , 携 带 自 定 义 消 息 , 状 态 码
     * */
    public static<T> Response<T> fail(ResponseCode ResponseCode){
        return fail(ResponseCode.getCode(),ResponseCode.getMessage());
    }

    /**
     * 失 败 操 作, 携 带 自 定 义 消 息 和 数 据
     * */
    public static<T> Response<T> fail(String message,T data){
        return fail(ResponseCode.FAILURE.getCode(),message,data);
    }

    /**
     * 失 败 操 作, 携 带 自 定 义 状 态 码 和 自 定 义 消 息
     * */
    public static<T> Response<T> fail(int code ,String message){
        return fail(code,message,null);
    }

    /**
     * 失 败 操 作, 携 带 自 定 义 状 态 码 , 消 息 和 数 据
     * */
    public static<T> Response<T> fail(int code,String message,T data){
        return response(code,message,data,false);
    }

    /**
     * Boolean 返 回 操 作, 携 带 默 认 返 回 值
     * */
    public static<T> Response<T> auto(boolean b){
        return auto(b,ResponseCode.SUCCESS.getMessage(),ResponseCode.FAILURE.getMessage());
    }

    /**
     * Boolean 返 回 操 作, 携 带 自 定 义 消 息
     * */
    public static<T> Response<T> auto(boolean b,String success,String fail){
        if(b){
            return success(success);
        }else{
            return fail(fail);
        }
    }

    /**
     * Response 构 建 方 法
     * */
    public static<T> Response<T> response(int code,String message,T data,boolean success){
        Response<T> response = new Response<T>();
        response.setCode(code);
        response.setMsg(message);
        response.setSuccess(success);
        response.setTimeStamp(System.currentTimeMillis());
        response.setData(data);
        return response;
    }
}

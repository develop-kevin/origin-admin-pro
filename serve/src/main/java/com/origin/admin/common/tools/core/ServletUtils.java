package com.origin.admin.common.tools.core;

import com.alibaba.druid.support.json.JSONUtils;
import com.origin.admin.common.constant.SystemConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description: 工具类
 * @Date 2023/11/13 22:39
 */
public class ServletUtils {
    /**
     * 获取 HttpServletRequest 对象
     *
     * @return HttpServletRequest
     * */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        return servletRequestAttributes.getRequest();
    }


    /**
     * 获取 HttpServletResponse 对象
     *
     * @return HttpServletResponse
     * */
    public static HttpServletResponse getResponse(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        return servletRequestAttributes.getResponse();
    }

    /**
     * 获取 HttpSession 对象
     *
     * @return HttpSession
     * */
    public static HttpSession getSession(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        return servletRequestAttributes.getRequest().getSession();
    }

    /**
     * 获取 Request 请求参数
     * @param  paramName 请求参数
     * @return String
     * */
    public static String getParameter(String paramName){
        return getRequest().getParameter(paramName);
    }


    /**
     * Response 对象写出 JSON 数据
     * @param data Object
     * @throws IOException 异常
     */
    public static void writeToJson(Object data) throws IOException {
        write(JSONUtils.toJSONString(data));
    }

    /**
     * Response 对象写出数据
     * @param msg 消息
     * @throws IOException 异常
     */
    public static void write(String msg) throws IOException {
        HttpServletResponse response = getResponse();
        response.setHeader("Content-type","application/json;charset="+ SystemConstant.UTF8);
        response.setCharacterEncoding(SystemConstant.UTF8);
        response.getWriter().write(msg);
    }


}

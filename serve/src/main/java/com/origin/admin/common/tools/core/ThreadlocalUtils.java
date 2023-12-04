package com.origin.admin.common.tools.core;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/27 15:08
 */
public class ThreadlocalUtils {
    private static final ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<>();

    public static <T> T get(){
        return (T)THREAD_LOCAL.get();
    }

    public static void set(Object value){
        THREAD_LOCAL.set(value);
    }

    public static void remove(){
        THREAD_LOCAL.remove();
    }

}

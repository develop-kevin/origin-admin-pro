package com.origin.admin.common.tools.core;



import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/29 18:09
 */
public class HelperUtils {

    /**
     * 设置用户ID
     * @param userId 用户ID
     */
    public static void setUserId(Long userId) {
        Map<String,Long> map = new HashMap<>();
        map.put("user_id",userId);
        ThreadlocalUtils.set(map);
    }

    /**
     * 获取用户ID
     * @return Integer
     */
    public static Long getUserId() {
        Map<String,Long> map = ThreadlocalUtils.get();
        return map.get("user_id");
    }

    /**
     * 删除用户信息
     */
    public static void clearUserId() {
        ThreadlocalUtils.remove();
    }
}

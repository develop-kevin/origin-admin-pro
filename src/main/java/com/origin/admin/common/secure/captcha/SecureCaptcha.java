package com.origin.admin.common.secure.captcha;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/13 18:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecureCaptcha {
    /**
     * 标识
     */
    private String key;
    /**
     * 验证码
     */
    private String code;
    /**
     * 验证码生成的图片
     */
    private String image;
}

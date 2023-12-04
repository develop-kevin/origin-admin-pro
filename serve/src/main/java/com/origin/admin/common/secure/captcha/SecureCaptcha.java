package com.origin.admin.common.secure.captcha;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("key")
    private String key;
    /**
     * 验证码
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("code")
    private String code;
    /**
     * 验证码生成的图片
     */
    @JsonProperty("image")
    private String image;
}

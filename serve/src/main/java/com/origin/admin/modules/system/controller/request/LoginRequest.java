package com.origin.admin.modules.system.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/16 0:02
 */
@Data
public class LoginRequest {
    /**
     * 用户名称
     */
    @JsonProperty(value = "username")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "用户名必须有字母、数据组成")
    @NotEmpty(message = "用户名不能为空")
    @Size(min = 5, max = 20, message = "用户名长度必须在5-20字数之间")
    private String username;
    /**
     * 密码
     */
    @JsonProperty(value = "password")
    @NotEmpty(message = "密码不能为空")
    @Size(min = 5, max = 20, message = "密码的长度必须在5-20字数之间")
    private String password;

    /**
     * 验证码
     */
    @JsonProperty(value = "code")
    @NotEmpty(message = "验证码不能为空")
    @Size(min = 6, max = 6, message = "验证码的长度必须在4字数")
    private String code;
    /**
     * 验证码key
     */
    @JsonProperty(value = "key")
    private String key;
}

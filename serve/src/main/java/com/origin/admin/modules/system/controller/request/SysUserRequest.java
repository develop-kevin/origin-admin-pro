package com.origin.admin.modules.system.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.origin.admin.modules.system.controller.validate.ValidatedAction;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description: 
 * @Date 2023/11/16 18:40
 */
@Data
public class SysUserRequest {
    /**
     * 用户名称
     */
    @NotNull(message = "用户名不能为空",groups = {ValidatedAction.Insert.class})
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "用户名必须有字母、数字组成")
    @Size(min = 5, max = 20, message = "用户名长度必须在5-20字数之间")
    @JsonProperty(value = "username")
    private String username;

    /**
     * 用户密码
     */
    @NotNull(message = "密码不能为空",groups = {ValidatedAction.Insert.class,ValidatedAction.Delete.class})
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$",message = "密码必须由字母、数字、符号组成")
    @Size(min = 6, max = 20, message = "密码长度必须在6-20字数之间")
    @JsonProperty(value = "password")
    private String password;

    /**
     * 锁定状态
     */
    @JsonProperty(value = "locked")
    private Boolean locked;

    /**
     * 备注
     */
    @JsonProperty(value = "remark")
    private String remark;
}

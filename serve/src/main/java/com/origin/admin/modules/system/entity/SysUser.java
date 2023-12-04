package com.origin.admin.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @TableName sys_user
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_user")
@Data
public class SysUser extends Base {

    /**
     * 用户账户
     */
    @TableField(value = "username")
    @Schema(description = "用户账户")
    private String username;

    /**
     * 用户密码
     */
    @TableField(value = "password")
    @Schema(description = "用户密码")
    @JsonIgnore
    private String password;

    /**
     * 干扰码
     */
    @TableField(value = "salt")
    @Schema(description = "干扰码")
    @JsonIgnore
    private String salt;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    @Schema(description = "头像")
    private String avatar;

    /**
     * 用户状态（0：正常 1：禁用）
     */
    @TableField(value = "enable")
    @Schema(description = "用户状态")
    private Boolean enable;

    /**
     * 锁定状态
     */
    @TableField(value = "locked")
    @Schema(description = "锁定状态")
    private Boolean locked;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @Schema(description = "备注")
    private String remark;
}
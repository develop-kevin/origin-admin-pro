package com.origin.admin.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

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
    private String username;

    /**
     * 用户密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 干扰码
     */
    @TableField(value = "salt")
    private String salt;

    /**
     * 用户状态（0：正常 1：禁用）
     */
    @TableField(value = "enable")
    private Boolean enable;

    /**
     * 锁定状态
     */
    @TableField(value = "locked")
    private Boolean locked;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;
}
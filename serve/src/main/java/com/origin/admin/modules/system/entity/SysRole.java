package com.origin.admin.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统角色表
 * @TableName sys_role
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_role")
@Data
public class SysRole extends Base {
    /**
     * 角色名称
     */
    @TableField(value = "title")
    @Schema(description = "角色名称")
    private String title;

    /**
     * 角色标识
     */
    @TableField(value = "code")
    @Schema(description = "角色标识")
    private String code;

    /**
     * 图标路径
     */
    @TableField(value = "icon")
    @Schema(description = "图标路径")
    private String icon;

    /**
     * 显示顺序
     */
    @TableField(value = "order_num")
    @JsonProperty(value = "order_num")
    @Schema(description = "显示顺序")
    private Integer orderNum;

    /**
     * 使用状态（0：正常 1：禁用）
     */
    @TableField(value = "enable")
    @Schema(description = "使用状态")
    private Integer enable;

    /**
     * 描述
     */
    @TableField(value = "description")
    @Schema(description = "描述")
    private String description;

}
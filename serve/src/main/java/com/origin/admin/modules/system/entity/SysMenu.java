package com.origin.admin.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 
 * @TableName sys_menu
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_menu")
@Data
public class SysMenu extends Base {

    /**
     * 父类ID
     */
    @TableField(value = "parent_id")
    @JsonProperty(value = "parent_id")
    private Long parentId;

    /**
     * 菜单名称
     */
    @TableField(value = "title")
    private String title;

    /**
     * 图标路径
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 显示顺序
     */
    @TableField(value = "order_num")
    @JsonProperty(value = "order_num")
    private Integer orderNum;

    /**
     * 使用状态（0：正常 1：禁用）
     */
    @TableField(value = "enable")
    private Integer enable;

    /**
     * 路由地址
     */
    @TableField(value = "link_path")
    @JsonProperty(value = "link_path")
    private String linkPath;

    /**
     * 组件路径
     */
    @TableField(value = "component")
    private String component;

    /**
     * 是否为外链（0是 1否）
     */
    @TableField(value = "is_frame")
    @JsonProperty(value = "is_frame")
    private Integer isFrame;

    /**
     * 是否缓存（0缓存 1不缓存）
     */
    @TableField(value = "is_cache")
    @JsonProperty(value = "is_cache")
    private Integer isCache;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @TableField(value = "menu_type")
    @JsonProperty(value = "menu_type")
    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    @TableField(value = "visible")
    private Integer visible;

    /**
     * 权限标识
     */
    @TableField(value = "perms")
    private String perms;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 子类
     */
    @TableField(exist = false)
    private List<SysMenu> children;

}
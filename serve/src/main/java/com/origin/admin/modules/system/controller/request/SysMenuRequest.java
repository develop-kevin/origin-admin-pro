package com.origin.admin.modules.system.controller.request;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.origin.admin.modules.system.controller.validate.ValidatedAction;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description: 
 * @Date 2023/11/16 18:40
 */
@Data
public class SysMenuRequest {

    /**
     * 父类ID
     */
    @JsonProperty(value = "parent_id")
    private Integer parentId;
    /**
     * 菜单名称
     */
    @NotNull(message = "菜单名称不能为空",groups = {ValidatedAction.Insert.class})
    @Size(min = 2, max = 10, message = "菜单名称长度必须在2-10字数之间")
    @JsonProperty(value = "title")
    private String title;

    /**
     * 图标路径
     */
    @NotNull(message = "菜单图标不能为空",groups = {ValidatedAction.Insert.class,ValidatedAction.Update.class})
    @JsonProperty(value = "icon")
    private String icon;

    /**
     * 显示顺序
     */
    @JsonProperty(value = "order_num")
    private Integer orderNum;

    /**
     * 路由地址
     */
    @NotNull(message = "路由地址不能为空",groups = {ValidatedAction.Insert.class,ValidatedAction.Update.class})
    @JsonProperty(value = "link_path")
    private String linkPath;

    /**
     * 组件路径
     */
    @JsonProperty(value = "component")
    private String component;

    /**
     * 是否为外链（0是 1否）
     */
    @Size(min = 0, max = 1, message = "是否为外链必须在0或1之间")
    @JsonProperty(value = "is_frame")
    private Integer isFrame;

    /**
     * 是否缓存（0缓存 1不缓存）
     */
    @Size(min = 0, max = 1, message = "是否缓存必须在0或1之间")
    @JsonProperty(value = "is_cache")
    private Integer isCache;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @JsonProperty(value = "menu_type")
    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    @Size(min = 0, max = 1, message = "菜单状态必须在0或1之间")
    @JsonProperty(value = "visible")
    private Integer visible;

    /**
     * 权限标识
     */
    @JsonProperty(value = "perms")
    private String perms;

    /**
     * 备注
     */
    @JsonProperty(value = "remark")
    private String remark;

}

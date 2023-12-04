package com.origin.admin.modules.system.service;

import com.origin.admin.modules.system.entity.SysMenu;

import java.util.List;

/**
* @author 11297
* @description 针对表【sys_menu】的数据库操作Service
* @createDate 2023-11-28 10:46:45
*/
public interface SysMenuService extends BaseService<SysMenu> {

    /**
     * 获取名称
     * @param title 名称
     */
    SysMenu queryByTitle(String title);
    /**
     * 查询所有查询
     * @return  Page<SysMenu>
     */
    List<SysMenu> queryAll();
}

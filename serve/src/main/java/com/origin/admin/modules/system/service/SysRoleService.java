package com.origin.admin.modules.system.service;

import com.origin.admin.modules.system.entity.SysRole;

/**
* @author 11297
* @description 针对表【sys_role(系统角色表)】的数据库操作Service
* @createDate 2023-11-29 19:01:05
*/
public interface SysRoleService extends BaseService<SysRole> {
    /**
     * 获取名称
     * @param title 名称
     */
    SysRole queryByTitle(String title);
}

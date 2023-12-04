package com.origin.admin.modules.system.service;

import com.origin.admin.modules.system.entity.SysUser;

/**
* @author 11297
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2023-11-15 18:14:10
*/
public interface SysUserService extends BaseService<SysUser> {
    /**
     * 获取用户信息
     * @param username 用户名称
     */
    SysUser queryByUserName(String username);
}

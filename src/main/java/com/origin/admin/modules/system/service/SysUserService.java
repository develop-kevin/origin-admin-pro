package com.origin.admin.modules.system.service;

import com.origin.admin.modules.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 11297
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2023-11-15 18:14:10
*/
public interface SysUserService extends IService<SysUser> {
    long insert(SysUser entity) throws Exception;

    SysUser queryByUserName(String name);
}

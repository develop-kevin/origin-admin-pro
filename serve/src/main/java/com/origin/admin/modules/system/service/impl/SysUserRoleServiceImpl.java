package com.origin.admin.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.origin.admin.common.web.page.PageRequest;
import com.origin.admin.common.web.page.PageResponse;
import com.origin.admin.common.web.page.WhereRequest;
import com.origin.admin.modules.system.entity.SysUserRole;
import com.origin.admin.modules.system.service.SysUserRoleService;
import com.origin.admin.modules.system.mapper.SysUserRoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 11297
* @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Service实现
* @createDate 2023-11-29 23:11:01
*/
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole>
    implements SysUserRoleService{

}





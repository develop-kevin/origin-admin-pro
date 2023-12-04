package com.origin.admin.modules.system.mapper;

import com.origin.admin.modules.system.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 11297
* @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Mapper
* @createDate 2023-11-29 23:11:01
* @Entity com.origin.admin.modules.system.entity.SysUserRole
*/
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

}





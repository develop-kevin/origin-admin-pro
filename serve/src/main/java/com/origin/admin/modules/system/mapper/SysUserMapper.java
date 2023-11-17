package com.origin.admin.modules.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.origin.admin.modules.system.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
* @author 11297
* @description 针对表【sys_user】的数据库操作Mapper
* @createDate 2023-11-15 18:14:10
* @Entity com.origin.admin.modules.system.entity.SysUser
*/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}





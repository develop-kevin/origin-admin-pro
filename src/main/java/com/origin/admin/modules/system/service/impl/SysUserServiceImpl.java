package com.origin.admin.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.origin.admin.common.tools.core.PasswordUtils;
import com.origin.admin.modules.system.entity.SysUser;
import com.origin.admin.modules.system.service.SysUserService;
import com.origin.admin.modules.system.mapper.SysUserMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
* @author 11297
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2023-11-15 18:14:10
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{
    @Override
    public long insert(SysUser entity) throws Exception {
        SysUser sysUser = queryByUserName(entity.getUsername());
        if(sysUser != null){
            throw new Exception("用户名已存在");
        }
        String salt = RandomStringUtils.randomAlphabetic(10);
        String password = entity.getPassword() + salt;
        String passwordSalt = PasswordUtils.encoder(password);
        entity.setSalt(salt);
        entity.setPassword(passwordSalt);
        super.save(entity);
        return entity.getId();
    }

    /**
     * 查询用户名
     * @param username 用户名
     * @return AdminUserEntity
     */
    @Override
    public SysUser queryByUserName(String username){
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return super.baseMapper.selectOne(queryWrapper);
    }

}





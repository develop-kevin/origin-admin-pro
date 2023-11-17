package com.origin.admin.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.origin.admin.common.tools.core.PasswordUtils;
import com.origin.admin.common.web.page.PageRequest;
import com.origin.admin.common.web.page.PageResponse;
import com.origin.admin.common.web.page.WhereRequest;
import com.origin.admin.modules.system.entity.SysUser;
import com.origin.admin.modules.system.service.SysUserService;
import com.origin.admin.modules.system.mapper.SysUserMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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


    /**
     * 判断用户是否存在
     * @param id 用户ID
     * @param username 用户名
     *
     * @return boolean
     */
    @Override
    public boolean exist(Integer id,String username){
        SysUser sysUser;
        if(id == 0){
            sysUser = queryByUserName(username);
        }else{
            sysUser = super.baseMapper.selectById(id);
        }
        return sysUser != null;
    }

    /**
     * 单条查询
     * @param id 查询ID
     * @return AdminUserEntity
     */
    @Override
    public SysUser getDetail(Integer id) {
        return super.baseMapper.selectById(id);
    }

    /**
     * 更新用户
     * @param entity AdminUserEntity
     * @return AdminUserEntity
     */
    @Override
    public Integer update(SysUser entity) throws Exception {
        if(entity.getId() == null){
            throw new Exception("用户ID不存在");
        }
        if(entity.getPassword() != null){
            String salt = RandomStringUtils.randomAlphanumeric(10);
            String password = entity.getPassword() + salt;
            String passwordSalt = PasswordUtils.encoder(password);
            entity.setPassword(passwordSalt);
        }
        super.baseMapper.updateById(entity);
        return entity.getId();
    }

    /**
     * 批量删除数据
     * @param ids 删除用户ID
     * @return int
     */
    @Override
    public Integer delete(List<Integer> ids) {
        return super.baseMapper.deleteBatchIds(ids);
    }

    /**
     * 分页数据
     * @param whereRequest 条件
     * @param page 查询
     * @return Page<SysUser>
     */
    @Override
    public PageResponse<SysUser> queryPage(WhereRequest whereRequest, PageRequest page){
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        String keyword = whereRequest.getKeyword();
        sysUserQueryWrapper.like(StringUtils.isNoneEmpty(whereRequest.getKeyword()),"username",whereRequest.getKeyword())
                .or().like(StringUtils.isNoneEmpty(keyword),"nickname",keyword)
                .or().like(StringUtils.isNoneEmpty(keyword),"email",keyword)
                .orderBy(true,true,"id");
        if(!whereRequest.getBeginTime().isEmpty()){
            String dateTime = !whereRequest.getEndTime().isEmpty() ? whereRequest.getEndTime() : String.valueOf(LocalDateTime.now());
            sysUserQueryWrapper.between("created_at",whereRequest.getBeginTime(),dateTime);
        }
        System.out.println("page_num=="+page.getPageNum());
        System.out.println("page_size=="+page.getPageSize());
        // 两个参数：current的值默认是1，从1开始，不是0。size是每一页的条数。
        Page<SysUser> pageList = new Page<>(page.getPageNum(), page.getPageSize());
        Page<SysUser> userPage = super.baseMapper.selectPage(pageList, sysUserQueryWrapper);

        return new PageResponse<SysUser>(userPage.getCurrent(),userPage.getTotal(),userPage.getRecords());
    }

}





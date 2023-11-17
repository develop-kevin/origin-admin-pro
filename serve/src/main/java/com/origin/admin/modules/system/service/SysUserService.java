package com.origin.admin.modules.system.service;

import com.origin.admin.common.web.page.PageRequest;
import com.origin.admin.common.web.page.PageResponse;
import com.origin.admin.common.web.page.WhereRequest;
import com.origin.admin.modules.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 11297
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2023-11-15 18:14:10
*/
public interface SysUserService extends IService<SysUser> {
    long insert(SysUser entity) throws Exception;

    /**
     * 获取用户
     * @param username 用户名
     * @return SysUser
     */
    SysUser queryByUserName(String username);

    /**
     * 检测用户存在
     * @param id id
     * @param username 用户名
     * @return boolean
     */
    boolean exist(Integer id,String username);

    /**
     * 查询详情
     * @param id ID
     * @return SysUser
     */
    SysUser getDetail(Integer id);

    /**
     * 更新
     * @param entity entity
     * @return Integer
     * @throws Exception 异常
     */
    Integer update(SysUser entity) throws Exception;

    /**
     * 删除
     * @param ids ids
     * @return Integer
     */
    Integer delete(List<Integer> ids);

    /**
     * 分页查询
     * @param whereRequest 条件
     * @param page  分页
     * @return  Page<SysUser>
     */
    PageResponse<SysUser> queryPage(WhereRequest whereRequest, PageRequest page);
}

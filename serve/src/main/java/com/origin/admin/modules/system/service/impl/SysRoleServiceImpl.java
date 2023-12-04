package com.origin.admin.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.origin.admin.common.web.page.PageRequest;
import com.origin.admin.common.web.page.PageResponse;
import com.origin.admin.common.web.page.WhereRequest;
import com.origin.admin.modules.system.entity.SysRole;
import com.origin.admin.modules.system.service.SysRoleService;
import com.origin.admin.modules.system.mapper.SysRoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author 11297
* @description 针对表【sys_role(系统角色表)】的数据库操作Service实现
* @createDate 2023-11-29 19:01:05
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService{
    /**
     * 插入角色
     * @param entity entity
     * @return Long 插入ID
     * @throws Exception 异常
     */
    @Override
    public Long insert(SysRole entity) throws Exception {
        SysRole sysRole = queryByTitle(entity.getTitle());
        if(sysRole != null){
            throw new Exception("角色名已存在");
        }
        super.baseMapper.insert(entity);
        return entity.getId();
    }

    /**
     * 查询名称
     * @param title 查询名称
     * @return entity
     */
    @Override
    public SysRole queryByTitle(String title){
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",title);
        return super.baseMapper.selectOne(queryWrapper);
    }


    /**
     * 判断名称是否存在
     * @param id 名称ID
     * @param title 名称名
     *
     * @return boolean
     */
    @Override
    public Boolean exist(Long id,String title){
        SysRole SysRole;
        if(id.equals(0L)){
            SysRole = queryByTitle(title);
        }else{
            SysRole = super.baseMapper.selectById(id);
        }
        return SysRole == null;
    }

    /**
     * 单条查询
     * @param id 查询ID
     * @return entity
     */
    @Override
    public SysRole getDetail(Long id) {
        return super.baseMapper.selectById(id);
    }

    /**
     * 更新角色
     * @param entity entity
     * @return Long 更新ID
     */
    @Override
    public Long update(SysRole entity) throws Exception {
        if(entity.getId() == null){
            throw new Exception("角色ID不存在");
        }
        super.baseMapper.updateById(entity);
        return entity.getId();
    }

    /**
     * 批量删除数据
     * @param ids 删除ID
     * @return int
     */
    @Override
    public Integer delete(List<Long> ids) {
        return super.baseMapper.deleteBatchIds(ids);
    }

    /**
     * 分页数据
     * @param whereRequest 条件
     * @param page 查询
     * @return Page<SysRole>
     */
    @Override
    public PageResponse<SysRole> queryPage(WhereRequest whereRequest, PageRequest page){
        QueryWrapper<SysRole> SysRoleQueryWrapper = new QueryWrapper<>();
        String keyword = whereRequest.getKeyword();
        SysRoleQueryWrapper.like(StringUtils.isNoneEmpty(whereRequest.getKeyword()),"title",whereRequest.getKeyword())
                .or().like(StringUtils.isNoneEmpty(keyword),"code",keyword)
                .orderBy(true,true,"id");
        if(!whereRequest.getBeginTime().isEmpty()){
            String dateTime = !whereRequest.getEndTime().isEmpty() ? whereRequest.getEndTime() : String.valueOf(LocalDateTime.now());
            SysRoleQueryWrapper.between("create_time",whereRequest.getBeginTime(),dateTime);
        }
        // 两个参数：current的值默认是1，从1开始，不是0。size是每一页的条数。
        Page<SysRole> pageList = new Page<>(page.getPageNum(), page.getPageSize());
        Page<SysRole> userPage = super.baseMapper.selectPage(pageList, SysRoleQueryWrapper);
        return new PageResponse<>(userPage.getCurrent(),userPage.getTotal(),userPage.getRecords());
    }

}





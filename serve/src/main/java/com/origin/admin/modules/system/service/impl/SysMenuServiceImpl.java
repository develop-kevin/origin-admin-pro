package com.origin.admin.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.origin.admin.common.web.page.PageRequest;
import com.origin.admin.common.web.page.PageResponse;
import com.origin.admin.common.web.page.WhereRequest;
import com.origin.admin.modules.system.entity.SysMenu;
import com.origin.admin.modules.system.service.SysMenuService;
import com.origin.admin.modules.system.mapper.SysMenuMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author 11297
* @description 针对表【sys_menu】的数据库操作Service实现
* @createDate 2023-11-28 10:46:45
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService{
    /**
     * 插入菜单
     * @param entity entity
     * @return Long 插入ID
     * @throws Exception 异常
     */
    @Override
    public Long insert(SysMenu entity) throws Exception {
        SysMenu sysMenu = queryByTitle(entity.getTitle());
        if(sysMenu != null){
            throw new Exception("菜单名已存在");
        }
        super.baseMapper.insert(entity);
        return entity.getId();
    }

    /**
     * 查询菜单名
     * @param title 菜单名
     * @return AdminMenuEntity
     */
    @Override
    public SysMenu queryByTitle(String title){
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",title);
        return super.baseMapper.selectOne(queryWrapper);
    }


    /**
     * 判断菜单名是否存在
     * @param id 菜单ID
     * @param title 菜单名
     *
     * @return boolean
     */
    @Override
    public Boolean exist(Long id,String title){
        SysMenu sysMenu;
        if(id.equals(0L)){
            sysMenu = queryByTitle(title);
        }else{
            sysMenu = super.baseMapper.selectById(id);
        }
        return sysMenu == null;
    }

    /**
     * 单条查询
     * @param id 查询ID
     * @return AdminMenuEntity
     */
    @Override
    public SysMenu getDetail(Long id) {
        return super.baseMapper.selectById(id);
    }

    /**
     * 更新菜单
     * @param entity entity
     * @return Long 更新ID
     */
    @Override
    public Long update(SysMenu entity) throws Exception {
        if(entity.getId() == null){
            throw new Exception("菜单ID不存在");
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
     * @return Page<SysMenu>
     */
    @Override
    public PageResponse<SysMenu> queryPage(WhereRequest whereRequest, PageRequest page){
        QueryWrapper<SysMenu> SysMenuQueryWrapper = new QueryWrapper<>();
        SysMenuQueryWrapper.orderBy(true,true,"id");
        if(!whereRequest.getBeginTime().isEmpty()){
            String dateTime = !whereRequest.getEndTime().isEmpty() ? whereRequest.getEndTime() : String.valueOf(LocalDateTime.now());
            SysMenuQueryWrapper.between("create_time",whereRequest.getBeginTime(),dateTime);
        }
        // 两个参数：current的值默认是1，从1开始，不是0。size是每一页的条数。
        Page<SysMenu> pageList = new Page<>(page.getPageNum(), page.getPageSize());
        Page<SysMenu> userPage = super.baseMapper.selectPage(pageList, SysMenuQueryWrapper);
        return new PageResponse<>(userPage.getCurrent(),userPage.getTotal(),userPage.getRecords());
    }

    /**
     * 返回所有递归的数据
     * @return List
     */
    @Override
    public List<SysMenu> queryAll(){
        QueryWrapper<SysMenu> SysMenuQueryWrapper = new QueryWrapper<>();
        SysMenuQueryWrapper.orderBy(true,true,"order_num");
        List<SysMenu> allMenu = super.baseMapper.selectList(SysMenuQueryWrapper);
        List<SysMenu> rootMenu = new ArrayList<>();
        for (SysMenu sm: allMenu){
            if(sm.getParentId().equals(0L)){//父节点是0的，为根节点。
                rootMenu.add(sm);
            }
        }
        for (SysMenu rm: rootMenu){
            //获取根节点下的所有子节点，使用getChildren方法存储
            List<SysMenu> childList = getChild(rm.getId(),allMenu);
            rm.setChildren(childList);
        }
        return rootMenu;
    }


    /**
     * 获取菜单的子类
     * @param id 父类ID
     * @param allMenu 所有菜单
     * @return 递归子菜单
     */
    private List<SysMenu> getChild(Long id,List<SysMenu> allMenu){
        //子菜单
        List<SysMenu> childList = new ArrayList<>();
        for (SysMenu sm: allMenu){
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if(sm.getParentId().equals(id)){
                childList.add(sm);
            }
        }
        //递归
        for (SysMenu cm: childList){
            cm.setChildren(getChild(cm.getId(),allMenu));
        }
        //如果节点下没有子节点，返回一个空list（递归退出）
        if(childList.isEmpty()){
            return new ArrayList<>();
        }
        return childList;
    }


}





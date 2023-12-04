package com.origin.admin.modules.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.origin.admin.common.constant.ControllerConstant;
import com.origin.admin.common.tools.core.ThreadlocalUtils;
import com.origin.admin.common.web.domain.Response;
import com.origin.admin.common.web.domain.ResponseCode;
import com.origin.admin.common.web.page.PageRequest;
import com.origin.admin.common.web.page.WhereRequest;
import com.origin.admin.modules.system.controller.request.SysMenuRequest;
import com.origin.admin.modules.system.controller.request.SysMenuRequest;
import com.origin.admin.modules.system.entity.SysMenu;
import com.origin.admin.modules.system.entity.SysMenu;
import com.origin.admin.modules.system.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/28 10:49
 */
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYSTEM+"/menu/")
@Tag(name = "管理菜单",description = "所有后端菜单接口：包含增删改查等")
@Slf4j
public class SysMenuController {
    @Resource
    private SysMenuService sysMenuService;

    @PostMapping("create")
    @Operation(summary = "创建菜单")
    public Response<SysMenu> create(@RequestBody @Valid SysMenuRequest sysMenuRequest){
        if(sysMenuService.exist(0L,sysMenuRequest.getTitle())){
            return Response.fail(ResponseCode.TITLE_FOUND);
        }
        SysMenu sysMenu = new SysMenu();
        BeanUtil.copyProperties(sysMenuRequest,sysMenu);
        try{
            sysMenuService.insert(sysMenu);
            return Response.success(ResponseCode.CREATE_SUCCESS);
        }catch (Exception e){
            log.error("创建菜单失败:{}",e.getMessage());
            return Response.fail(ResponseCode.CREATE_FAILURE);
        }
    }


    @PostMapping("detail/{id}")
    @Operation(summary = "菜单详情")
    public Response<SysMenu> info(@PathVariable("id") Long id){
        //获取菜单详情
        if(sysMenuService.exist(id,"")){
            return Response.fail(ResponseCode.USER_USERNAME_NOT_FOUND);
        }
        SysMenu sysMenu = sysMenuService.getDetail(id);
        return Response.success(sysMenu);
    }

    @PostMapping("update/{id}")
    @Operation(summary = "更新菜单")
    public Response<SysMenu> update(@PathVariable("id") Long id,@RequestBody @Valid SysMenuRequest SysMenuRequest){
        if(sysMenuService.exist(id,SysMenuRequest.getTitle())){
            return Response.fail(ResponseCode.USER_USERNAME_FOUND);
        }
        SysMenu sysMenu = new SysMenu();
        BeanUtil.copyProperties(SysMenuRequest,sysMenu);
        try {
            sysMenu.setId(id);
            sysMenuService.update(sysMenu);
            return Response.success(ResponseCode.UPDATE_SUCCESS);
        }catch (Exception e){
            log.error("更新菜单失败:{}",e.getMessage());
            return Response.fail(ResponseCode.UPDATE_FAILURE);
        }
    }

    @DeleteMapping("delete/{ids}")
    @Operation(summary = "删除菜单")
    public Response<String> delete(@PathVariable("ids") List<Long> ids){
        try{
            sysMenuService.delete(ids);
            return Response.success(ResponseCode.DELETE_SUCCESS);
        }catch (Exception e){
            log.error("删除菜单失败:{}",e.getMessage());
            return Response.fail(ResponseCode.DELETE_FAILURE);
        }
    }

    @GetMapping("query/all")
    @Operation(summary = "查询所有菜单")
    public Response<Object> query() {
        return Response.success(sysMenuService.queryAll());
    }

}

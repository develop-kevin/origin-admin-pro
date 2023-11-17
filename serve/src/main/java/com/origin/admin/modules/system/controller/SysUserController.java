package com.origin.admin.modules.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.origin.admin.common.constant.ControllerConstant;
import com.origin.admin.common.web.domain.Response;
import com.origin.admin.common.web.domain.ResponseCode;
import com.origin.admin.common.web.page.PageRequest;
import com.origin.admin.modules.system.controller.request.SysUserRequest;
import com.origin.admin.common.web.page.WhereRequest;
import com.origin.admin.modules.system.entity.SysUser;
import com.origin.admin.modules.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/16 18:39
 */
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYSTEM+"/system/user/")
@Tag(name = "管理用户",description = "所有后端用户接口：包含增删改查等")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @PostMapping("create")
    @Operation(summary = "创建用户")
    public Response<SysUser> create(@RequestBody @Valid SysUserRequest sysUserRequest){
        if(sysUserService.exist(0,sysUserRequest.getUsername())){
            return Response.fail(ResponseCode.USER_USERNAME_FOUND);
        }
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(sysUserRequest,sysUser);
        try {
            sysUserService.save(sysUser);
            return Response.success(ResponseCode.CREATE_SUCCESS);
        }catch (Exception e){
            return Response.fail(ResponseCode.CREATE_FAILURE);
        }
    }

    @PostMapping("update")
    @Operation(summary = "更新用户")
    public Response<SysUser> update(@RequestBody @Valid SysUserRequest sysUserRequest){
        if(sysUserService.exist(sysUserRequest.getId(),sysUserRequest.getUsername())){
            return Response.fail(ResponseCode.USER_USERNAME_FOUND);
        }
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(sysUserRequest,sysUser);
        try {
            sysUserService.update(sysUser);
            return Response.success(ResponseCode.CREATE_SUCCESS);
        }catch (Exception e){
            return Response.fail(ResponseCode.CREATE_FAILURE);
        }
    }

    @PostMapping("delete")
    @Operation(summary = "删除用户")
    public Response<String> delete(@RequestBody @Valid SysUserRequest sysUserRequest){
        try{
            sysUserService.delete(sysUserRequest.getIds());
            return Response.success(ResponseCode.DELETE_SUCCESS);
        }catch (Exception e){
            return Response.fail(ResponseCode.DELETE_FAILURE);
        }
    }

    @PostMapping("query/page")
    @Operation(summary = "查询分页用户")
    public Response<Object> query(@RequestBody WhereRequest whereRequest,
                                  @RequestParam(value = "page_num",defaultValue = "1") int pageNum,
                                  @RequestParam(value = "page_size",defaultValue = "10") int pageSize
    ){
        PageRequest pageRequest = new PageRequest(pageNum,pageSize);
        return Response.success(sysUserService.queryPage(whereRequest,pageRequest));
    }

}

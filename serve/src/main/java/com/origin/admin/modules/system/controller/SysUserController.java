package com.origin.admin.modules.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.origin.admin.common.constant.ControllerConstant;
import com.origin.admin.common.tools.core.ThreadlocalUtils;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/16 18:39
 */
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYSTEM+"/user/")
@Tag(name = "管理用户",description = "所有后端用户接口：包含增删改查等")
@Slf4j
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @PostMapping("create")
    @Operation(summary = "创建用户")
    public Response<SysUser> create(@RequestBody @Valid SysUserRequest sysUserRequest){
        if(sysUserService.exist(0L,sysUserRequest.getUsername())){
            return Response.fail(ResponseCode.USER_USERNAME_FOUND);
        }
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(sysUserRequest,sysUser);
        try {
            sysUserService.insert(sysUser);
            return Response.success(ResponseCode.CREATE_SUCCESS);
        }catch (Exception e){
            log.error("创建用户失败:{}",e.getMessage());
            return Response.fail(ResponseCode.CREATE_FAILURE);
        }
    }

    @PostMapping("detail/{id}")
    @Operation(summary = "用户详情")
    public Response<SysUser> info(@PathVariable("id") Long id){
        if(id.equals(0L)){
            Map<String,Object> map = ThreadlocalUtils.get();
            id = (Long) map.get("user_id");
        }
        //获取用户详情
        if(sysUserService.exist(id,"")){
            return Response.fail(ResponseCode.USER_USERNAME_NOT_FOUND);
        }
        SysUser sysUser = sysUserService.getDetail(id);
        return Response.success(sysUser);
    }

    @PostMapping("update/{id}")
    @Operation(summary = "更新用户")
    public Response<SysUser> update(@PathVariable("id") Long id,@RequestBody @Valid SysUserRequest sysUserRequest){
        if(sysUserService.exist(id,sysUserRequest.getUsername())){
            return Response.fail(ResponseCode.USER_USERNAME_FOUND);
        }
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(sysUserRequest,sysUser);
        try {
            sysUser.setId(id);
            sysUserService.update(sysUser);
            return Response.success(ResponseCode.CREATE_SUCCESS);
        }catch (Exception e){
            log.error("更新用户失败:{}",e.getMessage());
            return Response.fail(ResponseCode.CREATE_FAILURE);
        }
    }

    @DeleteMapping("delete/{ids}")
    @Operation(summary = "删除用户")
    public Response<String> delete(@PathVariable("ids") List<Long> ids){
        try{
            sysUserService.delete(ids);
            return Response.success(ResponseCode.DELETE_SUCCESS);
        }catch (Exception e){
            log.error("删除用户失败:{}",e.getMessage());
            return Response.fail(ResponseCode.DELETE_FAILURE);
        }
    }

    @GetMapping("query/page")
    @Operation(summary = "查询分页用户")
    public Response<Object> query(@RequestBody WhereRequest whereRequest,
                                  @RequestParam(value = "page_num",defaultValue = "1") int pageNum,
                                  @RequestParam(value = "page_size",defaultValue = "10") int pageSize
    ){
        PageRequest pageRequest = new PageRequest(pageNum,pageSize);
        return Response.success(sysUserService.queryPage(whereRequest,pageRequest));
    }

}

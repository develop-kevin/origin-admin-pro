package com.origin.admin;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.origin.admin.common.config.WhiteConfig;
import com.origin.admin.modules.system.controller.request.SysUserRequest;
import com.origin.admin.modules.system.entity.SysUser;
import com.origin.admin.modules.system.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/15 18:14
 */
@SpringBootTest
public class SysUserTests {

    @Autowired
    SysUserService sysUserService;
    @Test
    void add(){
//        for (int i = 0; i < 1; i++) {
            try{
                SysUser adminUserEntity = new SysUser();
                adminUserEntity.setUsername("admin");
                adminUserEntity.setPassword("123456");
                System.out.println("adminUserEntity=="+sysUserService.insert(adminUserEntity));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
//        }
    }

    @Test
    void run(){
        System.out.println(Arrays.asList(WhiteConfig.whiteList()).contains("/doc.html"));
    }

    @Test
    void data(){
        SysUser sysUser = new SysUser();
        SysUserRequest sysUserRequest = new SysUserRequest();
        sysUserRequest.setUsername("admin23312312");
        sysUserRequest.setPassword("admin5555555");
        BeanUtil.copyProperties(sysUserRequest,sysUser);
        System.out.println("sysUser"+sysUser);
    }

    @Test
    public void testMybatisPlus_Page(){

        QueryWrapper<SysUser> queryyWapper = new QueryWrapper<>();
//        queryyWapper.eq("number","008");
        // 两个参数：current的值默认是1，从1开始，不是0。size是每一页的条数。
        Page<SysUser> page = new Page<>(1, 10);
        Page<SysUser> userPage = sysUserService.getBaseMapper().selectPage(page, queryyWapper);
        List<SysUser> list = userPage.getRecords();
        for(int i = 0; i < 10; i++) {
            System.out.println(list.get(i).id);
        }

        System.out.println("当前页：" + userPage.getCurrent());
        System.out.println("总页数：" + userPage.getPages());
        System.out.println("记录数：" + userPage.getTotal());
        System.out.println("是否有上一页：" + userPage.hasPrevious());
        System.out.println("是否有下一页：" + userPage.hasNext());


    }
}

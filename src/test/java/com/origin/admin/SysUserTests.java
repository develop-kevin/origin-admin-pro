package com.origin.admin;

import com.origin.admin.common.config.WhiteConfig;
import com.origin.admin.modules.system.entity.SysUser;
import com.origin.admin.modules.system.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

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
        for (int i = 0; i < 1; i++) {
            try{
                SysUser adminUserEntity = new SysUser();
                adminUserEntity.setUsername("salina00"+i);
                adminUserEntity.setPassword("123456");
                System.out.println("adminUserEntity=="+sysUserService.insert(adminUserEntity));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Test
    void run(){
        System.out.println(Arrays.asList(WhiteConfig.whiteList()).contains("/doc.html"));
    }
}

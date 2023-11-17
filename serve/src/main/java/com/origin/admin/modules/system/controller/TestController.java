package com.origin.admin.modules.system.controller;

import com.origin.admin.common.constant.ControllerConstant;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description: 测试
 * @Date 2023/11/15 21:37
 */
@RestController
@Tag(name = "测试")
@RequestMapping(ControllerConstant.PREFIX + "test")
public class TestController {
    @GetMapping("/index")
    public String index(){
        return "hello world";
    }
}

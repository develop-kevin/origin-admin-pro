package com.origin.admin.common.secure.captcha;

import com.origin.admin.common.constant.ControllerConstant;
import com.origin.admin.common.web.domain.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/13 18:29
 */
@RestController
@Tag(name = "验证码")
@RequestMapping(ControllerConstant.PREFIX + "captcha")
public class SecureCaptchaController {

    @Resource
    private SecureCaptchaService secureCaptchaService;

    /**
     * 验证码
     * @return Response
     */
    @GetMapping("/generate")
    @Operation(summary = "验证码")
    public Response<Object> generate(){
        return Response.success(secureCaptchaService.generate());
    }

}

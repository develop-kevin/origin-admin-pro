package com.origin.admin.modules.system.controller;

import com.origin.admin.common.config.JwtConfig;
import com.origin.admin.common.constant.CacheNameConstant;
import com.origin.admin.common.constant.ControllerConstant;
import com.origin.admin.common.constant.SecurityConstant;
import com.origin.admin.common.tools.core.JwtUtils;
import com.origin.admin.common.tools.core.PasswordUtils;
import com.origin.admin.common.tools.core.RedisCacheUtils;
import com.origin.admin.common.web.domain.Response;
import com.origin.admin.common.web.domain.ResponseCode;
import com.origin.admin.modules.system.controller.request.LoginRequest;
import com.origin.admin.modules.system.controller.response.TokenResponse;
import com.origin.admin.modules.system.entity.SysUser;
import com.origin.admin.modules.system.service.impl.SysUserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description: 系统：授权接口
 * @Date 2023/11/15 23:28
 */
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYSTEM+"/auth/")
@Tag(name = "管理用户",description = "所有后端用户接口：包含登录、注册等")
public class AuthorizationController {
    @Resource
    private SysUserServiceImpl sysUserService;

    @Resource
    private RedisCacheUtils redisCacheUtils;

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private JwtConfig jwtConfig;

    @PostMapping("login")
    @Operation(summary = "登录")
    public Response<TokenResponse> login(@RequestBody @Valid LoginRequest loginRequest){
        //1、验证码校验
        String code = redisCacheUtils.getCacheObject(loginRequest.getCaptchaKey());
        if(!code.equals(loginRequest.getCaptchaCode())){
            return Response.fail(ResponseCode.CAPTCHA_ERROR);
        }
        //2、查询用户是否存在
        SysUser sysUser = sysUserService.queryByUserName(loginRequest.getUsername());
        if(sysUser == null){
            return Response.fail(ResponseCode.USER_USERNAME_NOT_FOUND);
        }
        String password = loginRequest.getPassword() + sysUser.getSalt();
        //3、判断密码
        Boolean passBool = PasswordUtils.matches(password,sysUser.getPassword());
        if(!passBool){
            return Response.fail(ResponseCode.USER_BAD_CREDENTIALS);
        }
        var user = User.builder()
                .username(sysUser.getUsername())
                .build();
        TokenResponse tokenResponse = createToken(sysUser,user);
        return Response.success(tokenResponse);
    }

    @PostMapping("logout")
    @Operation(summary = "退出")
    public Response<ResponseCode> logout(HttpServletRequest request){
        //从请求头获取鉴权信息 AUTHORIZATION
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        //如果鉴权信息为空或者不是以Bearer 开头的，直接返回
        if(authHeader == null || !authHeader.startsWith(SecurityConstant.BEARER_CODE)){
            return Response.fail(ResponseCode.TOKEN_MISSION);
        }
        //从鉴权信息中获取refreshToken
        String refreshToken = authHeader.replace(SecurityConstant.BEARER_CODE,"");
        //从refreshToken中获取用户信息
        String userInfo = jwtUtils.extractUsername(refreshToken);
        if(userInfo == null){
            return Response.fail(ResponseCode.USER_USERNAME_NOT_FOUND);
        }
        jwtUtils.setTokenExpired(authHeader);
        SysUser sysUser = sysUserService.queryByUserName(userInfo);
        redisCacheUtils.deleteObject(getKeyCode(sysUser.getId()));
        return Response.success(ResponseCode.LOGOUT_SUCCESS);
    }

    @PostMapping("refresh/token")
    @Operation(summary = "刷新token")
    public Response<TokenResponse> refreshToken(HttpServletRequest request) {
        //从请求头获取鉴权信息 AUTHORIZATION
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        //如果鉴权信息为空或者不是以Bearer 开头的，直接返回
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return Response.fail(ResponseCode.TOKEN_MISSION);
        }
        //从鉴权信息中获取refreshToken
        String refreshToken = authHeader.substring(7);
        //从refreshToken中获取用户信息
        String userInfo = jwtUtils.extractUsername(refreshToken);
        if(userInfo == null){
            return Response.fail(ResponseCode.USER_USERNAME_NOT_FOUND);
        }
        SysUser sysUser = sysUserService.queryByUserName(userInfo);
        var user = User.builder()
                .username(sysUser.getUsername())
                .build();
        //验证token是否有效
        if(jwtUtils.isTokenValid(refreshToken,user)){
            TokenResponse tokenResponse = createToken(sysUser,user);
            return Response.success(tokenResponse);
        }else{
            return Response.fail(ResponseCode.TOKEN_EXPIRED);
        }
    }


    /**
     * 统一创建 token
     * @param sysUser sysUser
     * @param user user
     * @return TokenResponse
     */
    private TokenResponse createToken(SysUser sysUser, UserDetails user){
        //通过JWT方法生成Token
        var accessToken = jwtUtils.generateToken(user);
        //生成RefreshToken(刷新Token使用)
        var refreshToken = jwtUtils.generateRefreshToken(user);
        //保存token
        Map<String,Object> map = new HashMap<>();
        map.put("access_token",accessToken);
        map.put("refresh_token",refreshToken);
        long time = jwtConfig.getExpireAt().longValue();
        redisCacheUtils.setCacheObject(getKeyCode(sysUser.getId()),map,time,TimeUnit.SECONDS);
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setExpireAt(time);
        return tokenResponse;
    }

    private String getKeyCode(long id){
        return CacheNameConstant.TOKEN_NAME_PREFIX+"User-"+id;
    }
}

package com.origin.admin.modules.system.filter;

import com.origin.admin.common.config.WhiteConfig;
import com.origin.admin.common.tools.core.JwtUtils;
import com.origin.admin.common.web.domain.ResponseCode;
import com.origin.admin.common.web.exception.base.BusinessException;
import com.origin.admin.modules.system.entity.SysUser;
import com.origin.admin.modules.system.service.SysUserService;
import com.origin.admin.modules.system.service.impl.SysUserServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/16 8:59
 */
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String TOKEN_INVALID = "501";
    private static final String TOKEN_EXPIRED = "502";

    private static final String TOKEN_MISSION = "503";

    @Resource
    private SysUserServiceImpl sysUserService;

    @Resource
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(request, response);
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String url = request.getRequestURI();
//        log.info("请求路径："+url);
//        //判断是否认证的请求路径
//        //是：直接放行
//        if(Arrays.asList(WhiteConfig.whiteList()).contains(url)){
//            //如果时白名单直接放行
//            System.out.println("我到底有没进来Arrays");
//            filterChain.doFilter(request,response);
//        }else{
//            System.out.println("我到底没进来Arrays");
//            try{
//                String authorization = request.getHeader("Authorization");
//                log.info("携带authorization："+authorization);
//                if(StringUtils.isBlank(authorization)){
//                    throw new BusinessException("无效token");
//                }
//                //删除Bearer 字符串
//                String token = authorization.replace("Bearer ","");
//                if(StringUtils.isBlank(token)){
//                    throw new BusinessException("无效token");
//                }
//                String userInfo = jwtUtils.extractUsername(token);
//                if(StringUtils.isBlank(userInfo)){
//                    request.setAttribute(ResponseCode.TOKEN_INVALID.getMessage(), "无效token");
//                    request.setAttribute(ResponseCode.TOKEN_INVALID.getCode().toString(), TOKEN_INVALID);
//                    throw new RuntimeException("无效token");
//                }
//                // 根据userId获取用户信息
//                SysUser sysUser = sysUserService.queryByUserName(userInfo);
//                if (null == sysUser) {
//                    request.setAttribute(ResponseCode.TOKEN_EXPIRED.getMessage(), "登录已过期");
//                    request.setAttribute(ResponseCode.TOKEN_EXPIRED.getCode().toString(), TOKEN_EXPIRED);
//                    throw new RuntimeException("登录已过期");
//                }
//                // 放行
//                UsernamePasswordAuthenticationToken authenticationToken =
//                        new UsernamePasswordAuthenticationToken(sysUser, null, null);
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                filterChain.doFilter(request, response);
//            }catch (Exception e){
//                request.setAttribute(ResponseCode.SYSTEM_ERROR.getMessage(), "系统错误，请联系管理员");
//                request.setAttribute(ResponseCode.SYSTEM_ERROR.getCode().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
//                throw new RuntimeException("系统错误，请联系管理员");
//            }
//        }
//    }











}

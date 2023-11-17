package com.origin.admin.modules.system.filter;

import com.origin.admin.common.config.WhiteConfig;
import com.origin.admin.common.tools.core.JwtUtils;
import com.origin.admin.common.tools.core.RedisCacheUtils;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
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
    @Resource
    private RedisCacheUtils redisCacheUtils;
    @Resource
    private SysUserServiceImpl sysUserService;

    @Resource
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = "Authorization";
        String tokenPrefix = "Bearer ";
        String authorization = request.getHeader(tokenHeader);
        if (authorization != null && !authorization.isEmpty() && authorization.startsWith(tokenPrefix)) {
            //去掉头部的字符串
            String token = authorization.replace(tokenPrefix, "");
            String username = jwtUtils.extractUsername(token);
            if (username !=null || SecurityContextHolder.getContext().getAuthentication() == null) {
                // 根据jwt解析出来的username，获取数据库中的用户信息，封装sysUserService对象
                SysUser sysUser = sysUserService.queryByUserName(username);
                // TODO 此处token有效性可以从redis｜数据库中获取
                String key = jwtUtils.getKeyCode(sysUser.getId());
                Boolean isTokenValid = redisCacheUtils.getCacheObject(key);

                //再从redis中取出对应jwt（不存在有可能是token有问题，还有就是redis里面token已经过期了）
                if (jwtUtils.isTokenValid(token,sysUser) && isTokenValid) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            sysUser, null, Collections.emptyList());
                    // 更新安全上下文的持有用户
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }


}

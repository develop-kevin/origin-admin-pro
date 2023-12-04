package com.origin.admin.modules.system.filter;

import com.origin.admin.common.constant.SecurityConstant;
import com.origin.admin.common.tools.core.JwtUtils;
import com.origin.admin.common.tools.core.RedisCacheUtils;
import com.origin.admin.modules.system.entity.SysUser;
import com.origin.admin.modules.system.service.impl.SysUserServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

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
        String url = request.getRequestURI();
        log.info("当前访问的地址：{}",url);
        //检验请求的url是否在忽略鉴权的url中
        String token = jwtUtils.getToken(request);
        if(!token.isEmpty()){
            //去掉头部的字符串
            //该接口需要区分后台，前端
            String username = jwtUtils.extractUsername(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 根据jwt解析出来的username，获取数据库中的用户信息，封装sysUserService对象
                SysUser sysUser = sysUserService.queryByUserName(username);
                // TODO 此处token有效性可以从redis｜数据库中获取
                String key = jwtUtils.getKeyCode(sysUser.getId());
                Boolean isTokenValid = redisCacheUtils.getCacheObject(key);
                if(isTokenValid != null && isTokenValid){
                    //再从redis中取出对应jwt（不存在有可能是token有问题，还有就是redis里面token已经过期了）
                    if (jwtUtils.isTokenValid(token,sysUser)) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                sysUser, null, Collections.emptyList());
                        // 更新安全上下文的持有用户
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }


}

package com.origin.admin.common.tools.core;

import com.origin.admin.common.config.JwtConfig;
import com.origin.admin.common.constant.CacheNameConstant;
import com.origin.admin.common.constant.SecurityConstant;
import com.origin.admin.modules.system.entity.SysUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;


/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/16 1:13
 */
@Component
@Slf4j
public class JwtUtils {
    @Resource
    private JwtConfig jwtConfig;

    /**
     * 生成token
     * @param userId 用户ID
     * @param username 用户名
     * @return String token
     */
    public String generateToken(Long userId,String username){
        Calendar instance = Calendar.getInstance();
        //设置过期时间
        instance.add(Calendar.MINUTE, jwtConfig.getExpireAt());//这里改为添加分钟
        Date expirationDate = new Date(System.currentTimeMillis() + jwtConfig.getExpireAt() * 1000);//这里计算从签发到现在的时间，需要乘以1000转为毫秒
        return Jwts.builder()
                .setId(String.valueOf(userId))                                 //设置ID
                .setSubject(username)                                          //设置主题
                .setIssuedAt(new Date(System.currentTimeMillis()))             //签发日期设置为当前时间
                .setExpiration(expirationDate)                                 //设置过期时间
                .signWith(getSignInKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 1、解析token字符串中的加密信息【加密算法&加密密钥】, 提取所有声明的方法
     * @param token token
     * @return Claims
     */
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                // 获取alg开头的信息
                .setSigningKey(getSignInKey())
                .build()
                // 解析token字符串
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 2、获取签名密钥的方法
     * @return 基于指定的密钥字节数组创建用于HMAC-SHA算法的新SecretKey实例
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtConfig.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 3、解析token字符串中的权限信息
     * @param token Token
     * @return 权限信息
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 4、从token中解析出id
     * @param token token
     * @return 用户ID信息
     */
    public Long extractUserId(String token) {
        return Long.valueOf(extractClaim(token, Claims::getId));
    }

    /**
     * 4、从token中解析出username
     * @param token token
     * @return 用户名信息
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 5、判断token是否过期
     * @param token token
     * @return boolean
     */
    public Boolean isTokenValid(String token, SysUser sysUser) {
        // 从token中获取用户名
        final String username = extractUsername(token);
        return (username.equals(sysUser.getUsername())) &&!isTokenExpired(token);
    }

    /**
     * 6、验证token是否过期
     * @param token token
     * @return boolean
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    /**
     * 7、从授权信息中获取token过期时间
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public String getKeyCode(Long id){
        return CacheNameConstant.TOKEN_NAME_PREFIX+"User-"+ id;
    }


    /**
     * 获取token数据
     * @param request 请求
     * @return 返回token
     */
    public String getToken(HttpServletRequest request) {
        String tokenHeader = SecurityConstant.AUTHORIZATION;
        String tokenPrefix = SecurityConstant.BEARER_CODE;
        String authorization = request.getHeader(tokenHeader);
        if (authorization != null && !authorization.isEmpty() && authorization.startsWith(tokenPrefix)) {
            return authorization.replace(tokenPrefix, "");
        }else{
            return "";
        }
    }

}

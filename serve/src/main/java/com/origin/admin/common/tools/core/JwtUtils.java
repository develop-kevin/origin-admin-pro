package com.origin.admin.common.tools.core;

import com.origin.admin.common.config.JwtConfig;
import com.origin.admin.common.constant.CacheNameConstant;
import com.origin.admin.modules.system.entity.SysUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
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

    public String generateToken(long userId,String username){
        Date now = new Date();
        Date expirationDate = new Date(System.currentTimeMillis() + jwtConfig.getExpireAt());
        return Jwts.builder()
                .setId(String.valueOf(userId))
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
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
     * 4、从token中解析出username
     * @param token token
     * @return 用户信息
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 5、判断token是否过期
     * @param
     * @return
     */
    public boolean isTokenValid(String token, SysUser sysUser) {
        // 从token中获取用户名
        final String username = extractUsername(token);
        return (username.equals(sysUser.getUsername())) &&!isTokenExpired(token);
    }

    /**
     * 6、验证token是否过期
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    /**
     * 6.1、从授权信息中获取token过期时间
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String getKeyCode(long id){
        return CacheNameConstant.TOKEN_NAME_PREFIX+"User-"+id;
    }

}

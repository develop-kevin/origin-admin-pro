package com.origin.admin.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description: JWT配置
 * @Date 2023/11/14 9:58
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class JwtConfig {

    /**
     * 发布者
     */
    private String issUser;
    /**
     * jwt加密密钥
     */
    private String secretKey;

    /**
     * Token失效时间
     * 默认：2小时
     */
    private Integer expireAt;
    /**
     * Token刷新时间
     * 默认: 30分钟
     */
    private Integer refreshExpireAt;
}

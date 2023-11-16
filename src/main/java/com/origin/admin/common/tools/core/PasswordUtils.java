package com.origin.admin.common.tools.core;

import jakarta.annotation.Nonnull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description: 加密工具
 * @Date 2023/11/15 20:39
 */
public class PasswordUtils {
    /**
     * 将原始密码进行编码,使用默认编码器
     *
     * @param rawPassword 原密码
     * @return 编码后的密码
     */
    public static String encoder(String rawPassword) {
        return encoder(rawPassword, defaultPasswordEncoder());
    }

    /**
     * 将原始密码进行编码，使用默认编码器
     *
     * @param rawPassword     原密码
     * @param passwordEncoder 编码器
     * @return 编译后的密码
     */
    public static String encoder(String rawPassword,@Nonnull PasswordEncoder passwordEncoder){
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * 校验原始密码与编码后的密码是否为同一个密码，使用默认编码器{@link #defaultPasswordEncoder()}
     *
     * @param rawPassword     原密码
     * @param encodedPassword 编码后的密码
     * @return 是否一致
     */
    public static Boolean matches(String rawPassword, String encodedPassword) {
        return matches(rawPassword, encodedPassword, defaultPasswordEncoder());
    }


    /**
     * 校验原始密码与编码后的密码是否为同一个密码
     *
     * @param rawPassword     原密码
     * @param encodedPassword 编码后的密码
     * @param passwordEncoder 编码器
     * @return 是否一致
     */
    public static Boolean matches(String rawPassword, String encodedPassword,
                                  @Nonnull PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public static PasswordEncoder defaultPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

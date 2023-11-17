package com.origin.admin.common.secure.captcha;

import com.origin.admin.common.constant.CacheNameConstant;
import com.origin.admin.common.constant.SecurityConstant;
import com.origin.admin.common.tools.core.RedisCacheUtils;
import com.origin.admin.common.web.exception.auth.captcha.CaptchaExpiredException;
import com.origin.admin.common.web.exception.auth.captcha.CaptchaValidationException;
import jakarta.annotation.Resource;
import com.wf.captcha.SpecCaptcha;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/13 18:30
 */
@Service
public class SecureCaptchaService {
    @Resource
    private RedisCacheUtils redisCacheUtils;

    /**
     * 生成验证码
     * @return SecureCaptcha
     */
    public SecureCaptcha generate(){
        SpecCaptcha specCaptcha = new SpecCaptcha(142,38);
        String key = UUID.randomUUID().toString();
        String code = specCaptcha.text().toLowerCase();
        redisCacheUtils.setCacheObject(CacheNameConstant.CAPTCHA_NAME_PREFIX+key,code, SecurityConstant.CATCHA_EXPIRATION, TimeUnit.SECONDS);
        return new SecureCaptcha(key,code,specCaptcha.toBase64());
    }

    /**
     * 检验验证码
     * @param key 键
     * @param code 验证码
     */
    public void verfiy(String key,String code){
        String redisCode = redisCacheUtils.getCacheObject(CacheNameConstant.CAPTCHA_NAME_PREFIX + key);
        if(redisCode == null) throw new CaptchaExpiredException("验证码过期");
        if(!redisCode.equals(code)) throw new CaptchaValidationException("验证码错误");
    }

    /***
     * 销毁
     * @param key 键
     */
    public void destroy(String key){
        redisCacheUtils.deleteObject(CacheNameConstant.CAPTCHA_NAME_PREFIX + key);
    }

}

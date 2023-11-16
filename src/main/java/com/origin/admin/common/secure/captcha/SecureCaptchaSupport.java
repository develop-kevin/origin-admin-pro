package com.origin.admin.common.secure.captcha;

import com.origin.admin.common.constant.SecurityConstant;
import com.origin.admin.common.tools.core.ServletUtils;
import com.origin.admin.common.web.domain.Response;
import com.origin.admin.common.web.domain.ResponseCode;
import com.origin.admin.common.web.exception.auth.captcha.CaptchaExpiredException;
import com.origin.admin.common.web.exception.auth.captcha.CaptchaValidationException;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/13 19:02
 */
public class SecureCaptchaSupport extends OncePerRequestFilter {

    public static final String CAPTCHA_KEY = "CaptchaKey";

    public static final String CAPTCHA_CODE = "CaptchaCode";

    @Resource
    private SecureCaptchaService secureCaptchaService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String captchaKey = ServletUtils.getParameter(CAPTCHA_KEY);
        String captchaCode = ServletUtils.getParameter(CAPTCHA_CODE);
        if (Strings.isBlank(captchaKey)) { ServletUtils.writeToJson(Response.fail(ResponseCode.CAPTCHA_KEY_MISSION)); return; }
        if (Strings.isBlank(captchaCode)) { ServletUtils.writeToJson(Response.fail(ResponseCode.CAPTCHA_CODE_MISSION)); return; }
        try{
            secureCaptchaService.verfiy(captchaKey,captchaCode.toLowerCase());
        }catch (CaptchaExpiredException e){
            ServletUtils.writeToJson(Response.fail(ResponseCode.CAPTCHA_EXPIRED));
        }catch (CaptchaValidationException e){
            ServletUtils.writeToJson(Response.fail(ResponseCode.CAPTCHA_INVALID));
        }
        secureCaptchaService.destroy(captchaKey);
        filterChain.doFilter(request,response);
    }

    @Override
    public boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if(SecurityConstant.IS_CAPTCHA_VERIFICATION){
            return !request.getRequestURI().equals(SecurityConstant.LOGIN_URL) || !request.getMethod().equals(SecurityConstant.LOGIN_METHOD);
        }
        return true;
    }
}

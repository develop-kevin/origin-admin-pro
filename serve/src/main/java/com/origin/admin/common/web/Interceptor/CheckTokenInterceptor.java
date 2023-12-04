package com.origin.admin.common.web.Interceptor;

import com.origin.admin.common.constant.SecurityConstant;
import com.origin.admin.common.tools.core.HelperUtils;
import com.origin.admin.common.tools.core.JwtUtils;
import com.origin.admin.common.tools.core.ThreadlocalUtils;
import com.origin.admin.common.web.domain.Response;
import com.origin.admin.common.web.domain.ResponseCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/27 14:29
 */
@Component
@Slf4j
public class CheckTokenInterceptor implements HandlerInterceptor {
    @Resource
    private JwtUtils jwtUtils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
        //检验token网络的第一次握手
        String method = request.getMethod();
        //
        log.info("测试");
        if(SecurityConstant.OPTIONS_METHOD.equalsIgnoreCase(method)){
            return true;
        }
        //检验请求的url是否在忽略鉴权的url中
        String token = jwtUtils.getToken(request);
        if (token.isEmpty()) {
            Response.fail(ResponseCode.NOT_LOGIN);
            return false;
        }else{
            try{
                Long userId = jwtUtils.extractUserId(token);
                HelperUtils.setUserId(userId);
                return true;
            }catch (ExpiredJwtException e){
                Response.fail(ResponseCode.TOKEN_EXPIRED);
                return false;
            }catch (UnsupportedJwtException e){
                Response.fail(ResponseCode.TOKEN_INVALID);
                return false;
            }catch (Exception e){
                Response.fail(ResponseCode.TOKEN_MISSION);
                return false;
            }
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("方法执行完毕之后");
        //释放本地线程 防止内存泄漏
        HelperUtils.clearUserId();
    }

}

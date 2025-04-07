package com.didi.interceptor;

import com.didi.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 令牌校验拦截器
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /*// 1. 获取请求路径
        String requestURI = request.getRequestURI(); // /employee/login

        // 2. 是否是登录请求，如果路径中包含/login,说明是登录操作,放行
        if(requestURI.contains("/login")){
            log.info("登录请求，放行");
            return true;
        }*/

        // 3. 获取请求头中的token
        String token = request.getHeader("token");

        // 4. 判断token是否存在,如果不存在,说明用户没有登录，返回错误信息(响应401状态码)
        if(token == null || token.isEmpty()){
            log.info("token为空，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 5. 如果token存在，校验token，如果校验失败 -> 返回错误信息(响应401状态码)
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e){
            log.info("token非法，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 6. 校验通过, 放行
        log.info("token校验通过，放行");
        return true;
    }
}

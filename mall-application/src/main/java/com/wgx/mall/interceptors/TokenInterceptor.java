package com.wgx.mall.interceptors;

import com.wgx.annotation.TokenCheck;
import com.wgx.enums.StateCodeEnum;
import com.wgx.mall.exception.define.TokenException;
import com.wgx.mall.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            throw new TokenException(StateCodeEnum.TOKEN_ERROR.getMsg());
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(TokenCheck.class)){
            TokenCheck tokenCheck = method.getAnnotation(TokenCheck.class);
            if(tokenCheck.required()){
                if(StringUtils.isEmpty(JwtUtil.parseToken(token))){
                   throw new TokenException(StateCodeEnum.TOKEN_ERROR.getMsg()) ;
                }
                return true;
            }
        }
        return true;
    }
}

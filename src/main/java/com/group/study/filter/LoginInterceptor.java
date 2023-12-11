package com.group.study.filter;

import com.group.study.annotation.PassToken;
import com.group.study.common.state.StatusCode;
import com.group.study.context.UserContextHolder;
import com.group.study.exception.BusinessException;
import com.group.study.model.entity.Role;
import com.group.study.service.UserService;
import com.group.study.service.security.LoginService;
import com.group.study.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private LoginService loginService;

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Method method = ((HandlerMethod) handler).getMethod();
        //检查是否通过有PassToken注解
        if (method.isAnnotationPresent(PassToken.class)) {
            return true;
        }
        //检查token登录状态
        String token = request.getHeader("Authorization");
        if (!loginService.checkToken(token)) throw new BusinessException(StatusCode.NO_LOGIN_ERROR);
        String userId = JwtUtils.getTokenAud(token).get(0);
        Role role = userService.getRoleByUserId(userId);
        //设置用户上下文信息
        UserContextHolder.setContext(userId, role);
        return true;
    }
}
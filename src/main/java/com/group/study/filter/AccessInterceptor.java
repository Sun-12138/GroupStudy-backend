package com.group.study.filter;

import com.group.study.annotation.Access;
import com.group.study.common.state.StatusCode;
import com.group.study.context.UserContextHolder;
import com.group.study.exception.BusinessException;
import com.group.study.model.entity.Role;
import com.group.study.model.enums.AccessRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

@Component
public class AccessInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Method method = ((HandlerMethod) handler).getMethod();
        //检查是否通过有Access注解注解 没有则进行放行
        if (!method.isAnnotationPresent(Access.class)) {
            return true;
        }
        Access access = method.getAnnotation(Access.class);
        //可访问的用户角色列表
        AccessRole[] roles = access.value();
        if (roles.length == 0) {
            //为0则说明没有用户可以访问 该接口封闭
            throw new BusinessException(StatusCode.NO_AUTH_ERROR);
        }
        //查看当前用户角色
        Role role = UserContextHolder.getContext().getRole();
        //检查当前用户是否在可访问列表中
        for (AccessRole accessRole : roles) {
            String accessRoleId = accessRole.getRoleId();
            String accessRoleName = accessRole.getRoleName();
            if (accessRoleId.equals(role.getRoleId()) && accessRoleName.equals(role.getRoleName())) {
                //该用户有访问权限 放行
                return true;
            }
        }
        throw new BusinessException(StatusCode.NO_AUTH_ERROR);
    }
}

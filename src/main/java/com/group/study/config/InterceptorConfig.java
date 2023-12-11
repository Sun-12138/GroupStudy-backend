package com.group.study.config;

import com.group.study.filter.AccessInterceptor;
import com.group.study.filter.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Resource
    private AccessInterceptor accessInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册登录拦截器
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**");
        //注册访问角色拦截器
        registry.addInterceptor(accessInterceptor)
                .addPathPatterns("/**");
    }
}

package com.group.study.annotation;

import com.group.study.model.enums.AccessRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 访问权限
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Access {
    /**
     * 可访问角色列表 若为空则封闭该接口或方法
     */
    AccessRole[] value() default {};
}

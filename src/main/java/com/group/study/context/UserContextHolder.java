package com.group.study.context;

import com.group.study.model.entity.Role;

/**
 * 用户数据上下文对象
 */
public class UserContextHolder {
    /**
     * 用于存放用户上下文数据
     */
    private static final ThreadLocal<UserContextInfo> USER_CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 获取用户id
     */
    public static UserContextInfo getContext() {
        return USER_CONTEXT_HOLDER.get();
    }

    /**
     * 设置用户id
     */
    public static void setContext(String userId, Role role) {
        if (USER_CONTEXT_HOLDER.get() == null) {
            USER_CONTEXT_HOLDER.set(new UserContextInfo(userId, role));
        }
    }

    /**
     * 清空用户上下文
     */
    public static void clear() {
        USER_CONTEXT_HOLDER.remove();
    }
}

package com.group.study.context;

/**
 * 用户数据上下文对象
 */
public class UserContextHolder {
    /**
     * 用于存放用户上下文数据
     */
    private static final ThreadLocal<String> USER_CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 获取用户id
     */
    public static String getUserId() {
        return USER_CONTEXT_HOLDER.get();
    }

    /**
     * 设置用户id
     */
    public static void setUserId(String userId) {
        USER_CONTEXT_HOLDER.set(userId);
    }

    /**
     * 清空用户上下文
     */
    public static void clear() {
        USER_CONTEXT_HOLDER.remove();
    }
}

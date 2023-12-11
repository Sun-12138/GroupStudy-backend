package com.group.study.common.state;

import lombok.Getter;
import lombok.ToString;

/**
 * 错误码
 */
@Getter
@ToString
public enum StatusCode {

    /**
     * 请求参数错误
     */
    PARAMS_ERROR(400, "请求参数错误"),
    /**
     * 无权限
     */
    NO_AUTH_ERROR(401, "无权限"),
    /**
     * 账号密码错误
     */
    AUTH_INFO_ERROR(402, "账号密码错误"),
    /**
     * 未登录
     */
    NO_LOGIN_ERROR(403, "未登录"),
    /**
     * 请求数据不存在
     */
    NOT_FOUND_ERROR(404, "请求数据不存在"),
    /**
     * 系统内部异常
     */
    SYSTEM_ERROR(500, "系统内部异常"),
    /**
     * 操作失败
     */
    OPERATION_ERROR(501, "操作失败");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

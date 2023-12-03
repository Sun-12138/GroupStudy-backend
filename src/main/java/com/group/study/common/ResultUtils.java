package com.group.study.common;


import com.group.study.common.state.StatusCode;

/**
 * @author EDY
 */
public class ResultUtils {

    /**
     * 成功
     *
     * @param data 成功是返回的数据
     * @param <T>  数据类型
     * @return 响应对象
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 成功
     *
     * @param data 成功是返回的数据
     * @param <T>  数据类型
     * @return 响应对象
     */
    public static <T> BaseResponse<T> success(T data, String msg) {
        return new BaseResponse<>(0, data, msg);
    }

    /**
     * 失败
     *
     * @param errorCode 错误码
     * @return 响应对象
     */
    public static BaseResponse<String> error(int errorCode, String message) {
        return new BaseResponse<>(errorCode, null, message);
    }

    /**
     * 失败
     *
     * @param errorCode 错误码
     * @return 响应对象
     */
    public static BaseResponse<String> error(StatusCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param errorCode 错误码
     * @param message   错误消息
     * @return 响应对象
     */
    public static BaseResponse<String> error(StatusCode errorCode, String message) {
        return new BaseResponse<>(errorCode.getCode(), null, message);
    }
}

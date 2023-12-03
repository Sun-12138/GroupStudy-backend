package com.group.study.common;

import com.group.study.common.state.StatusCode;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 *
 * @author EDY
 * @param <T>
 */
@Data
public class BaseResponse<T> implements Serializable {

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应信息
     */
    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(StatusCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}

package com.group.study.exception;

import com.group.study.common.state.StatusCode;
import lombok.Getter;

/**
 * @author EDY
 */
@Getter
public class BusinessException extends RuntimeException {
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(StatusCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }


    public BusinessException(StatusCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

}

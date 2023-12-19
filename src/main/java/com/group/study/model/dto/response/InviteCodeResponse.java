package com.group.study.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class InviteCodeResponse implements Serializable {

    /**
     * 邀请码
     */
    private String code;

    /**
     * 剩余有效期
     */
    private Long expires;
}

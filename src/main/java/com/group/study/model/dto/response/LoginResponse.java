package com.group.study.model.dto.response;

import com.group.study.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LoginResponse implements Serializable {

    /**
     * 用户身份令牌
     */
    private String token;

    /**
     * 用户角色
     */
    private Role role;
}

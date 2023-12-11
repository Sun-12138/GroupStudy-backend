package com.group.study.model.dto.response;

import com.group.study.model.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class LoginResponse implements Serializable {

    /**
     * 用户身份令牌
     */
    @ApiModelProperty("用户令牌")
    private String token;

    /**
     * 用户角色
     */
    @ApiModelProperty("用户角色信息")
    private Role role;
}

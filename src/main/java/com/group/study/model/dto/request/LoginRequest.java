package com.group.study.model.dto.request;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class LoginRequest implements Serializable {

    /**
     * 用户id
     */
    @ApiModelProperty("手机号")
    @NotBlank(message = "手机号不能为空")
    private String telephone;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;
}

package com.group.study.model.dto.request;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
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

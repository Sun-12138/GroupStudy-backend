package com.group.study.model.dto.request;

import com.group.study.model.enums.Sex;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.io.Serializable;


@Getter
public class RegisterRequest implements Serializable {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private Sex sex;

    /**
     * 角色
     */
    @NotBlank(message = "角色不能为空")
    @ApiModelProperty("角色id")
    private String roleId;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String password;

    /**
     * 用户手机号
     */
    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty("手机号")
    private String telephone;
}

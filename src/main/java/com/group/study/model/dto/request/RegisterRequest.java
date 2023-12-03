package com.group.study.model.dto.request;

import com.group.study.model.enums.Sex;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;


@Data
public class RegisterRequest implements Serializable {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 性别
     */
    private Sex sex;

    /**
     * 角色
     */
    @NotBlank(message = "角色不能为空")
    private String roleId;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 用户手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String telephone;
}

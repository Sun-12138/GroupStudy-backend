package com.group.study.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LoginRequest implements Serializable {

    /**
     * 用户id
     */
    @NotBlank(message = "手机号不能为空")
    private String telephone;

    /**
     * 密码
     */
    @NotBlank(message = "用户密码不能为空")
    private String password;
}

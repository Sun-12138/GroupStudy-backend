package com.group.study.model.dto.request;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class InviteCodeRequest implements Serializable {

    /**
     * 班级id
     */
    @NotBlank(message = "班级id不能为空")
    @ApiModelProperty("班级id")
    private String classId;

    /**
     * 邀请码有效期 单位秒
     */
    @NotNull(message = "有效期不能为空")
    @ApiModelProperty("邀请码有效期 单位秒")
    private long expires;
}

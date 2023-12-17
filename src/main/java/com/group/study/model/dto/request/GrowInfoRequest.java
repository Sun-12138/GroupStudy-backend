package com.group.study.model.dto.request;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class GrowInfoRequest implements Serializable {

    /**
     * 信息所处班级
     */
    @NotBlank(message = "班级id不能为空")
    @ApiModelProperty("班级id")
    private String classId;

    /**
     * 信息所有人
     */
    @NotBlank(message = "用户id不能为空")
    @ApiModelProperty("用户id")
    private String userId;

    /**
     * 成长记录内容
     */
    @NotBlank(message = "成长信息不能为空")
    @ApiModelProperty("成长信息")
    private String growData;
}

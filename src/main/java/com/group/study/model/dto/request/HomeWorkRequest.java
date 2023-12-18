package com.group.study.model.dto.request;


import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class HomeWorkRequest implements Serializable {

    /**
     * 班级id
     */
    @NotBlank(message = "班级id不能为空")
    @ApiModelProperty("班级id")
    private String classId;

    /**
     * 作业标题
     */
    @NotBlank(message = "作业标题不能为空")
    @ApiModelProperty("作业标题")
    private String workTitle;

    /**
     * 作业内容
     */
    @ApiModelProperty("作业内容")
    private String workContent;
}

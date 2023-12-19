package com.group.study.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
public class ClassInfoResponse implements Serializable {

    /**
     * 班级id
     */
    private String classId;

    /**
     * 班级名
     */
    private String className;

    /**
     * 班级创建者
     */
    private UserInfoResponse user;

    /**
     * 创建时间
     */
    private Timestamp createTime;

}

package com.group.study.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.io.Serializable;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AccessRole implements Serializable {
    Student("10001", "学生"),
    Teacher("10002", "老师");

    /**
     * 角色在数据库中的字段
     */
    private final String roleId;

    /**
     * 角色名名
     */
    private final String roleName;
    
    AccessRole(String roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
}

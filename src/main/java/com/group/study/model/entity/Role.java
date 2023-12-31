package com.group.study.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@ToString(exclude={"id"})
@TableName("sys_role")
public class Role {

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 角色id
     */
    private String roleId;

    /**
     * 角色名
     */
    private String roleName;
}

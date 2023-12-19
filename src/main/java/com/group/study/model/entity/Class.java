package com.group.study.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Data
@ToString(exclude={"id"})
@TableName("sys_class")
public class Class {

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @TableId(type = IdType.AUTO)
    private Integer id;

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
    private String userId;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;


}

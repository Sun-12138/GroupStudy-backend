package com.group.study.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.sql.Timestamp;

/**
 * 用户成长信息
 */
@Data
@ToString(exclude={"id"})
@TableName("sys_user_class_grow")
public class UserGroup {

    /**
     * 主键
     */
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 班级id
     */
    private String classId;

    /**
     * 用户id 唯一标识符
     */
    private String userId;

    /**
     * 成长记录内容
     */
    private String group_data;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 是否删除
     */
    private Boolean isDelete;

}

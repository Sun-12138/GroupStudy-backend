package com.group.study.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.sql.Timestamp;

@Data
@ToString(exclude={"id"})
@TableName("sys_user_class_grow")
public class GrowUpInfo {

    /**
     * 主键
     */
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 信息所处班级
     */
    private String classId;

    /**
     * 信息所有人
     */
    private String userId;

    /**
     * 成长记录内容
     */
    private String growData;

    /**
     * 添加时间
     */
    private Timestamp createTime;
}

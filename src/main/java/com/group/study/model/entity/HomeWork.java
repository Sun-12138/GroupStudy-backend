package com.group.study.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.sql.Timestamp;

@Data
@ToString(exclude={"id"})
@TableName("sys_class")
public class HomeWork {

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 班级id
     */
    private String classId;

    /**
     * 作业标题
     */
    private String workTitle;

    /**
     * 作业内容
     */
    private String workContent;

    /**
     * 添加时间
     */
    private Timestamp createTime;

}

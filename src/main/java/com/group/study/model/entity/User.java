package com.group.study.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.group.study.model.enums.Sex;
import lombok.*;

import java.sql.Timestamp;

@Data
@ToString(exclude={"id"})
@TableName("sys_user")
public class User {

    /**
     * 主键
     */
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id 唯一标识符
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 性别
     */
    private Sex sex;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户手机号
     */
    private String telephone;

    /**
     * 账号创建时间
     */
    private Timestamp createTime;

    /**
     * 账号信息最后修改时间
     */
    private Timestamp updateTime;

    /**
     * 是否删除
     */
    private Boolean isDeleted;
}

package com.group.study.model.dto.response;

import com.group.study.model.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class UserInfoResponse implements Serializable {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 性别
     */
    private Sex sex;

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
}

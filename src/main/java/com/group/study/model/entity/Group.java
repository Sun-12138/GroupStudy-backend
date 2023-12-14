package com.group.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class Group implements Serializable {

    /**
     * 成长记录内容
     */
    private String content;

    /**
     * 新建时间
     */
    private Timestamp createTime;
}

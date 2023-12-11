package com.group.study.context;

import com.group.study.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 用户上下文信息
 */
@Getter
@ToString
@AllArgsConstructor
public class UserContextInfo {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户角色
     */
    private Role role;

}

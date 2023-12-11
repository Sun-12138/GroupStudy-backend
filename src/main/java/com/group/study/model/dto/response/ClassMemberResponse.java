package com.group.study.model.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class ClassMemberResponse implements Serializable {

    /**
     * 全部成员
     */
    private List<Member> members;

    /**
     * 当前页数
     */
    private long current;

    /**
     * 总页数
     */
    private long total;

    @Data
    @AllArgsConstructor
    public static class Member {
        /**
         * 用户id
         */
        @ApiModelProperty("用户ID")
        private String userId;

        /**
         * 用户名
         */
        @ApiModelProperty("用户名")
        private String userName;
    }
}

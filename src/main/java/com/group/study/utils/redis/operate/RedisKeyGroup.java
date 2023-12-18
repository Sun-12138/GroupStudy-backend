package com.group.study.utils.redis.operate;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * key分组 用于定义redis key的层级
 */
public class RedisKeyGroup {

    /**
     * redis分组名列表
     */
    private List<String> group;

    /**
     * 分祝符
     */
    private String sep;

    public RedisKeyGroup() {
        initSep();
    }

    public RedisKeyGroup(List<String> group) {
        initSep();
        this.group = group;
    }

    public RedisKeyGroup(Sep sep) {
        this.sep = sep.getSep();
    }

    public RedisKeyGroup(List<String> group, Sep sep) {
        this.sep = sep.getSep();
        this.group = group;
    }

    /**
     * 初始化分组符
     */
    private void initSep() {
        //默认为冒号
        this.sep = Sep.COLON.getSep();
    }

    /**
     * 初始化分组列表
     */
    private void initGroup() {
        group = new ArrayList<>(3);
    }

    /**
     * 添加key所在组
     *
     * @param newGroup key的新组名
     */
    public void addGroup(String newGroup) {
        if (group == null) initGroup();
        group.add(newGroup);
    }


    /**
     * 构建当前设置的key的所在组
     *
     * @return key所在组前缀
     */
    public String generateGroup() {
        StringBuilder sb = new StringBuilder();
        for (String group : this.group) {
            sb.append(group).append(sep);
        }
        return sb.toString();
    }

    @Getter
    public enum Sep {
        COLON(":"),
        UNDER_LINE("_"),
        DOT("."),
        SLASH("/");

        private final String sep;

        Sep(String sep) {
            this.sep = sep;
        }
    }
}

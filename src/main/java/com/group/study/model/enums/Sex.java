package com.group.study.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Sex implements Serializable {
    MAN(0, "男"),
    WOMAN(1, "女"),
    UNKNOWN(-1, "未知");

    @TableId
    @EnumValue
    private final Integer id;

    private final String sexName;

    Sex(Integer id, String sexName) {
        this.id = id;
        this.sexName = sexName;
    }

    @JsonValue
    public Map<String, String> getValue() {
        Map<String, String> valueMap = new HashMap<>(2);
        valueMap.put("id", id.toString());
        valueMap.put("name", sexName);
        return valueMap;
    }

    /**
     * 自定义反序列函数
     *
     * @param id 性别id
     * @return 性别枚举
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Sex get(Integer id) {
        if (id == null) return UNKNOWN;
        return Arrays.stream(Sex.values()).filter(i -> i.getId() == id.intValue()).findAny().orElse(UNKNOWN);
    }
}

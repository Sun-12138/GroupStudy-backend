package com.group.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group.study.model.entity.Class;
import com.group.study.model.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClassMapper extends BaseMapper<Class> {

    @Select("SELECT user_id FROM sys_user_class WHERE class_id=#{classId}")
    List<String> getClassMemberId(String classId);

    @Insert("INSERT INTO sys_user_class (class_id, user_id) VALUES (#{classId}, #{userId})")
    void addClassMember(String classId, String userId);

    @Select("SELECT usr.user_id, usr.user_name, usr.sex, usr.telephone, usr.create_time, usr.update_time FROM sys_user_class cls, sys_user usr WHERE cls.class_id = #{classId} AND usr.user_id = cls.user_id")
    IPage<User> getClassMember(Page<User> page, String classId);
}

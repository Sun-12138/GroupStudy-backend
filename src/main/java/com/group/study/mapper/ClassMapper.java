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

    /**
     * 获取所有班级成员的user_id
     */
    @Select("SELECT user_id FROM sys_user_class WHERE class_id=#{classId} AND is_delete=0")
    List<String> getClassMemberId(String classId);

    /**
     * 添加班级成员
     *
     * @param classId 班级成员
     * @param userId 成员id
     */
    @Insert("INSERT INTO sys_user_class (class_id, user_id) VALUES (#{classId}, #{userId})")
    void addClassMember(String classId, String userId);

    /**
     * 获得用户所有班级
     *
     * @param userId 用户id
     */
    @Select("SELECT sys_class.* FROM sys_class, sys_user_class WHERE sys_user_class.user_id = #{userId} AND sys_user_class.class_id = sys_class.class_id AND sys_class.is_delete=0")
    Class getStudentClass(String userId);

    /**
     * 获得班级成员
     *
     * @param page 分页
     * @param classId 班级id
     * @return 分页查询的班级成员
     */
    @Select("SELECT usr.user_id, usr.user_name, usr.sex, usr.telephone, usr.create_time, usr.update_time FROM sys_user_class cls, sys_user usr WHERE cls.class_id = #{classId} AND usr.user_id = cls.user_id AND cls.is_delete=0")
    IPage<User> getClassMember(Page<User> page, String classId);
}

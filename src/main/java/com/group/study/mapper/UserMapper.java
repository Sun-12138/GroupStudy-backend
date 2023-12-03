package com.group.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group.study.model.entity.Role;
import com.group.study.model.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT sr.role_id, sr.role_name FROM sys_role sr,sys_user_role sur WHERE sr.role_id = sur.role_id AND sur.user_id=#{userId}")
    Role getUserRole(String userId);

    @Insert("INSERT INTO sys_user_role (user_id, role_id) VALUES (#{user.userId}, #{roleId})")
    int addUserRole(User user, String roleId);
}

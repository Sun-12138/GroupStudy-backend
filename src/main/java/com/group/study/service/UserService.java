package com.group.study.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group.study.common.state.StatusCode;
import com.group.study.exception.BusinessException;
import com.group.study.mapper.RoleMapper;
import com.group.study.mapper.UserMapper;
import com.group.study.model.entity.Role;
import com.group.study.model.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 与用户有关的业务代码
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    /**
     * 查询用户信息
     *
     * @param userId    查询的用户id
     * @return 查询到的用户信息
     */
    public User getUserByUserId(String userId) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        return userMapper.selectOne(qw);
    }

    /**
     * 查询用户信息
     *
     * @param telephone 手机号
     * @return 查询到的用户信息
     */
    public User getUserByTelephone(String telephone) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("telephone", telephone);
        return userMapper.selectOne(qw);
    }

    /**
     * 查询用户角色
     *
     * @param userId 用户id
     * @return 用户角色
     */
    public Role getRoleByUserId(String userId) {
        return userMapper.getUserRole(userId);
    }

    /**
     * 获得全部角色
     *
     * @return 角色列表
     */
    public List<Role> getAllRole() {
        return roleMapper.selectList(new QueryWrapper<>());
    }

    /**
     * 添加用户信息
     *
     * @param user 添加的用户对象
     * @return 是否添加成功
     */
    @Transactional
    public boolean addUser(User user, String roleId) {
        int changeNum = userMapper.insert(user);
        int changeNum2 = userMapper.addUserRole(user, roleId);
        if (changeNum == 0 || changeNum2 == 0) {
            throw new BusinessException(StatusCode.SYSTEM_ERROR);
        }
        return true;
    }
}

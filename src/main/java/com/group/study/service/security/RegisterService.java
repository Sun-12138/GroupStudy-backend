package com.group.study.service.security;

import com.group.study.common.state.StatusCode;
import com.group.study.exception.BusinessException;
import com.group.study.model.entity.User;
import com.group.study.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.UUID;

@Service
public class RegisterService {

    @Resource
    private UserService userService;

    /**
     * 注册用户
     *
     * @param user 用户信息 userId, createTime, isDeleted可为空
     * @param roleId 角色id
     */
    public void register(User user, String roleId) {
        //生成一个sys_user表中不存在的UUID
        int maxNumber = 5;
        for (int i = 0; i < maxNumber; i++) {
            String newUserId = UUID.randomUUID().toString();
            if (userService.getUserByUserId(newUserId) == null) {
                user.setUserId(newUserId);
                break;
            }
            if (i == maxNumber - 1) {
                //运气不好 随机不到唯一UUID
                throw new BusinessException(StatusCode.SYSTEM_ERROR, "注册失败");
            }
        }
        //检验手机号是否存在是否存在
        if (userService.getUserByTelephone(user.getTelephone()) != null) throw new BusinessException(StatusCode.PARAMS_ERROR, "手机号已存在");
        //设置当前时间为账号创建时间
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        if (!userService.addUser(user, roleId)) {
            throw new BusinessException(StatusCode.SYSTEM_ERROR, "注册失败");
        }
    }
}

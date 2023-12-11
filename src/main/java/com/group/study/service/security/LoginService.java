package com.group.study.service.security;

import cn.hutool.core.text.CharSequenceUtil;
import com.group.study.common.state.StatusCode;
import com.group.study.exception.BusinessException;
import com.group.study.model.entity.User;
import com.group.study.service.RedisService;
import com.group.study.service.UserService;
import com.group.study.utils.JwtUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginService {

    @Resource
    private UserService userService;

    @Resource
    private RedisService redisService;

    /**
     * 用户登录
     *
     * @param telephone 用户手机号
     * @param password  用户密码
     * @return 用户令牌
     */
    public String login(String telephone, String password) {
        User dbUser = userService.getUserByTelephone(telephone);
        if (!dbUser.getPassword().equals(password) || dbUser.getIsDeleted()) {
            throw new BusinessException(StatusCode.AUTH_INFO_ERROR);
        }
        //账号密码正确 创建token
        String token = JwtUtils.createToken(dbUser.getUserId());
        //将新的token放入redis
        redisService.addUserToken(dbUser.getUserId(), token);
        return token;
    }

    /**
     * 检查token是否为登录状态的令牌
     *
     * @param token 待检查token
     * @return 返回true则为通过
     */
    public boolean checkToken(String token) {
        if (!CharSequenceUtil.isBlank(token) && JwtUtils.verifyToken(token)) {
            //在Redis中查询JWT与用户身份是否正确
            String userId = JwtUtils.getTokenAud(token).get(0);
            String redisToken = redisService.getUserToken(userId);
            return token.equals(redisToken);
        }
        return false;
    }
}

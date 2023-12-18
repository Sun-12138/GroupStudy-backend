package com.group.study.service.security;

import cn.hutool.core.text.CharSequenceUtil;
import com.group.study.common.state.StatusCode;
import com.group.study.exception.BusinessException;
import com.group.study.model.entity.User;
import com.group.study.service.UserService;
import com.group.study.utils.JwtUtils;
import com.group.study.utils.redis.operate.RedisValueOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.UUID;

@Service
public class AccountService {

    @Resource
    private UserService userService;

    @Autowired
    @Qualifier("addUserToken")
    private RedisValueOperate<String> addUserToken;

    @Autowired
    @Qualifier("getUserToken")
    private RedisValueOperate<String> getUserToken;

    /**
     * 用户登录
     *
     * @param telephone 用户手机号
     * @param password  用户密码
     * @return 用户令牌
     */
    public String login(String telephone, String password) {
        User dbUser = userService.getUserByTelephone(telephone);
        if (!dbUser.getPassword().equals(password)) {
            throw new BusinessException(StatusCode.AUTH_INFO_ERROR);
        }
        //账号密码正确 创建token
        String token = JwtUtils.createToken(dbUser.getUserId());
        //将新的token放入redis
        addUserToken.exec(dbUser.getUserId(), token);
        return token;
    }

    /**
     * 注册用户
     *
     * @param user   用户信息 userId, createTime, isDeleted可为空
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
        if (userService.getUserByTelephone(user.getTelephone()) != null)
            throw new BusinessException(StatusCode.PARAMS_ERROR, "手机号已存在");
        //设置当前时间为账号创建时间
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        if (!userService.addUser(user, roleId)) {
            throw new BusinessException(StatusCode.SYSTEM_ERROR, "注册失败");
        }
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
            String redisToken = getUserToken.exec(userId)[0];
            return token.equals(redisToken);
        }
        return false;
    }
}

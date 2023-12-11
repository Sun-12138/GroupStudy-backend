package com.group.study.service;

import com.group.study.utils.redis.operate.RedisValueOperate;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    @Qualifier("addUserToken")
    private RedisValueOperate<String, String> redisAddUserToken;

    @Autowired
    @Qualifier("getUserToken")
    private RedisValueOperate<String, String> redisGetUserToken;

    /**
     * 添加用户令牌
     *
     * @param userId 用户id
     * @param token  用户令牌
     */
    public void addUserToken(String userId, String token) {
        redisAddUserToken.exec(userId, token);
    }

    /**
     * 获取用户令牌
     *
     * @param userId 用户id
     * @return 用户令牌
     */
    public String getUserToken(String userId) {
        return redisGetUserToken.exec(userId)[0];
    }

}

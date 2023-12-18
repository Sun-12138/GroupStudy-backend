package com.group.study.config;


import com.group.study.utils.redis.operate.RedisValueOperate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 自定义Bean配置
 */
@Configuration
public class BeanConfig {

    /**
     * 添加用户Token的Redis操作 key为userid value为用户token
     */
    @Bean("addUserToken")
    public RedisValueOperate<String> addUserToken(RedisTemplate<Object, Object> redisTemplate) {
        RedisValueOperate<String> operate = new RedisValueOperate<>(redisTemplate, (param, execute) -> {
            execute.set(param.getKey(), param.getValue());
            return null;
        });
        operate.addGroup("user", "token");
        return operate;
    }

    /**
     * 获得用户Token的Redis操作
     */
    @Bean("getUserToken")
    public RedisValueOperate<String> getUserToken(RedisTemplate<Object, Object> redisTemplate) {
        RedisValueOperate<String> operate = new RedisValueOperate<>(redisTemplate, (param, execute) -> new String[]{(String) execute.get(param.getKey())});
        operate.addGroup("user", "token");
        return operate;
    }

    /**
     * 添加邀请码 key为classID value邀请码
     */
    @Bean("addInviteCode")
    public RedisValueOperate<String> createInviteCode(RedisTemplate<Object, Object> redisTemplate) {
        RedisValueOperate<String> operate = new RedisValueOperate<>(redisTemplate, (param, execute) -> {
            String key = param.getKey();
            //过期时间
            long timeout = param.getTimeout();
            TimeUnit unit = param.getUnit();
            if (timeout != -1L) {
                execute.set(key, param.getValue(), timeout, unit);
            } else {
                execute.set(key, param.getValue());
            }
            return null;
        });
        operate.addGroup("invite");
        return operate;
    }

    /**
     * 通过班级id获取邀请码
     */
    @Bean("getInviteCode")
    public RedisValueOperate<String> getInviteCodeByClassId(RedisTemplate<Object, Object> redisTemplate) {
        RedisValueOperate<String> operate = new RedisValueOperate<>(redisTemplate, (param, execute) -> {
            String key = param.getKey();
            return new String[]{(String) execute.get(key)};
        });
        operate.addGroup("invite");
        return operate;
    }

}

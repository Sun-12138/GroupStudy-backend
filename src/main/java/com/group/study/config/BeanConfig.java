package com.group.study.config;


import com.group.study.utils.redis.operate.RedisValueOperate;
import io.lettuce.core.RedisException;
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
    public RedisValueOperate<String, String> addUserToken(RedisTemplate<Object, Object> redisTemplate) {
        RedisValueOperate<String, String> operate = new RedisValueOperate<>(redisTemplate, (kv, execute, group) -> {
            execute.set(group.generateGroup() + kv.getKey(), kv.getValue());
            return null;
        });
        operate.addGroup("user", "token");
        return operate;
    }

    /**
     * 获得用户Token的Redis操作
     */
    @Bean("getUserToken")
    public RedisValueOperate<String, String> getUserToken(RedisTemplate<Object, Object> redisTemplate) {
        RedisValueOperate<String, String> operate = new RedisValueOperate<>(redisTemplate, (kv, execute, group) -> new String[]{(String) execute.get(group.generateGroup() + kv.getKey())});
        operate.addGroup("user", "token");
        return operate;
    }

    /**
     * 添加邀请码 key为classID value邀请码
     */
    @Bean("addInviteCode")
    public RedisValueOperate<String, String> createInviteCode(RedisTemplate<Object, Object> redisTemplate) {
        RedisValueOperate<String, String> operate = new RedisValueOperate<>(redisTemplate, (kv, execute, group) -> {
            String key = group.generateGroup() + kv.getKey();
            //过期时间
            long timeout = kv.getTimeout();
            TimeUnit unit = kv.getUnit();
//            Boolean insertStatus;
            if (timeout != -1L) {
                execute.set(key, kv.getValue(), timeout, unit);
            } else {
                 execute.set(key, kv.getValue());
            }

//            if (Boolean.FALSE.equals(insertStatus)) {
//                throw new RedisException(key + "已存在");
//            }
            return null;
        });
        operate.addGroup("invite");
        return operate;
    }

    /**
     * 通过班级id获取邀请码
     */
    @Bean("getInviteCode")
    public RedisValueOperate<String, String> getInviteCodeByClassId(RedisTemplate<Object, Object> redisTemplate) {
        RedisValueOperate<String, String> operate = new RedisValueOperate<>(redisTemplate, (kv, execute, group) -> {
            String key = group.generateGroup() + kv.getKey();
            return new String[]{(String) execute.get(key)};
        });
        operate.addGroup("invite");
        return operate;
    }

}

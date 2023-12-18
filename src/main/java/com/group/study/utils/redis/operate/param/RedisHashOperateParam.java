package com.group.study.utils.redis.operate.param;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

@Getter
public class RedisHashOperateParam<V> extends AbstractRedisOperateParam<V> {

    /**
     * Hash中的key
     */
    private final String hKey;

    /**
     * @param key     Redis中的key
     * @param value   Redis中的value或者
     * @param timeout key的过期时间
     * @param unit    key的过期时间单位
     */
    protected RedisHashOperateParam(String key, String hKey, V value, long timeout, TimeUnit unit) {
        super(key, value, timeout, unit);
        this.hKey = hKey;
    }
}

package com.group.study.utils.redis.operate.param;

import java.util.concurrent.TimeUnit;

public class RedisValueOperateParam<V> extends AbstractRedisOperateParam<V> {

    /**
     * @param key     Redis中的key
     * @param value   Redis中的value或者
     * @param timeout key的过期时间
     * @param unit    key的过期时间单位
     */
    public RedisValueOperateParam(String key, V value, long timeout, TimeUnit unit) {
        super(key, value, timeout, unit);
    }
}

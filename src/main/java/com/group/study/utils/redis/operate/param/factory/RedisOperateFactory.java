package com.group.study.utils.redis.operate.param.factory;

import com.group.study.utils.redis.operate.param.AbstractRedisOperateParam;
import com.group.study.utils.redis.operate.param.RedisValueOperateParam;

import java.util.concurrent.TimeUnit;

public class RedisOperateFactory extends AbstractRedisOperateParam.AbstractFactory {
    /**
     * 创建Redis参数对象
     *
     * @param key key值
     * @param <V> value的类型
     * @return Redis操作参数对象
     */
    public static <V> RedisValueOperateParam<V> create(String key) {
        return new RedisValueOperateParam<>(key, null, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT_UNIT);
    }

    /**
     * 创建Redis参数对象
     *
     * @param key     key值
     * @param timeout key的过期时间
     * @param unit    key的过期时间单位
     * @param <V>     value的类型
     * @return Redis操作参数对象
     */
    public static <V> RedisValueOperateParam<V> create(String key, long timeout, TimeUnit unit) {
        return new RedisValueOperateParam<>(key, null, timeout, unit);
    }

    /**
     * 创建Redis参数对象
     *
     * @param key   key值
     * @param value value值
     * @param <V>   value的类型
     * @return Redis操作参数对象
     */
    public static <V> RedisValueOperateParam<V> create(String key, V value) {
        return new RedisValueOperateParam<>(key, value, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT_UNIT);
    }

    /**
     * 创建Redis参数对象
     *
     * @param key     key值
     * @param value   value值
     * @param timeout key的过期时间
     * @param unit    key的过期时间单位
     * @param <V>     value的类型
     * @return Redis操作参数对象
     */
    public static <V> RedisValueOperateParam<V> create(String key, V value, long timeout, TimeUnit unit) {
        return new RedisValueOperateParam<>(key, value, timeout, unit);
    }
}

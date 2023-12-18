package com.group.study.utils.redis.operate.param;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

/**
 * Redis操作参数
 *
 * @param <V> value的类型
 */
@Getter
public abstract class AbstractRedisOperateParam<V> {

    /**
     * Redis中的key
     */
    @Setter
    private String key;

    /**
     * Redis中的value
     */
    private final V value;

    /**
     * Key的过期时间
     */
    private final long timeout;

    /**
     * Key的过期时间单位
     */
    private final TimeUnit unit;

    /**
     * @param key     Redis中的key
     * @param value   Redis中的value或者
     * @param timeout key的过期时间
     * @param unit    key的过期时间单位
     */
    protected AbstractRedisOperateParam(String key, V value, long timeout, TimeUnit unit) {
        this.key = key;
        this.value = value;
        this.timeout = timeout;
        this.unit = unit;
    }

    public static abstract class AbstractFactory {
        protected static final long DEFAULT_TIMEOUT = -1L;

        protected static final TimeUnit DEFAULT_TIMEOUT_UNIT = TimeUnit.SECONDS;
    }
}

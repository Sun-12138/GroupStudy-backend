package com.group.study.utils.redis.callback;

import com.group.study.utils.redis.operate.param.RedisHashOperateParam;
import org.springframework.data.redis.core.HashOperations;


/**
 * 哈希操作回调
 *
 * @param <V> hash中value类型
 */
public interface RedisHashOperateCallBack<V> extends IRedisOperateCallBack<V> {

    /**
     * 具体操作
     *
     * @param kv      key-value值
     * @param execute Redis操作对象
     */
    V[] operate(RedisHashOperateParam<V> kv, HashOperations<Object, Object, Object> execute);
}

package com.group.study.utils.redis.callback;

import com.group.study.utils.redis.operate.RedisKeyGroup;
import com.group.study.utils.redis.operate.OperateKV;
import org.springframework.data.redis.core.HashOperations;


/**
 * 哈希操作回调
 *
 * @param <K>  key和hKey的类型
 * @param <V> hash中value类型
 */
public interface RedisHashOperateCallBack<K, V> extends IRedisOperateCallBack<K, V> {

    /**
     * 具体操作
     *
     * @param kv      key-value值
     * @param execute Redis操作对象
     * @param group   该key所在分组
     */
    V[] operate(OperateKV<K, V> kv, HashOperations<Object, Object, Object> execute, RedisKeyGroup<K> group);
}

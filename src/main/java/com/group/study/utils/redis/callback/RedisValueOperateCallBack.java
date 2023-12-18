package com.group.study.utils.redis.callback;

import com.group.study.utils.redis.operate.param.RedisValueOperateParam;
import org.springframework.data.redis.core.ValueOperations;


/**
 * 字符操作回调
 *
 * @param <V> value类型
 */
public interface RedisValueOperateCallBack<V> extends IRedisOperateCallBack<V> {

    /**
     * 具体操作
     *
     * @param param      key-value值
     * @param execute Redis操作对象
     */
    V[] operate(RedisValueOperateParam<V> param, ValueOperations<Object, Object> execute);
}

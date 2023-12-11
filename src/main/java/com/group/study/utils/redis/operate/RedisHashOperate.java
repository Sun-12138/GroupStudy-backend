package com.group.study.utils.redis.operate;

import com.group.study.utils.redis.callback.RedisHashOperateCallBack;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

public class RedisHashOperate<K, V> extends AbstractRedisOperate<K, V> {


    /**
     * @param redisTemplate Redis操作执行者
     */
    public RedisHashOperate(RedisTemplate<Object, Object> redisTemplate, RedisHashOperateCallBack<K, V> callBack) {
        super(redisTemplate, callBack);
    }

    /**
     * 执行Redis操作
     *
     * @param kv Redis操作需要的Key-Value
     * @return 如果需要获取数据则进行返回 无需则返回null
     */
    @Override
    public V[] exec(OperateKV<K, V> kv) {
        return ((RedisHashOperateCallBack<K, V>) callBack).operate(kv, redisTemplate.opsForHash(), group);
    }

    /**
     * 执行Redis事务操作
     *
     * @param kv Redis操作需要的Key-Value
     * @return 如果需要获取数据则进行返回 无需则返回null
     */
    @Override
    public V[] execTransact(OperateKV<K, V> kv) {
        SessionCallback<V[]> sessionCallback = new SessionCallback<>() {
            @Override
            public <SK, SV> V[] execute(RedisOperations<SK, SV> operations) throws DataAccessException {
                operations.multi(); // 开始事务
                V[] result = RedisHashOperate.this.exec(kv);
                operations.exec(); // 提交事务
                return result;
            }
        };
        return redisTemplate.execute(sessionCallback);
    }
}

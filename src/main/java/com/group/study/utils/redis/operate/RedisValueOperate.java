package com.group.study.utils.redis.operate;

import com.group.study.utils.redis.callback.RedisValueOperateCallBack;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

public class RedisValueOperate<K, V> extends AbstractRedisOperate<K, V> {


    public RedisValueOperate(RedisTemplate<Object, Object> redisTemplate, RedisValueOperateCallBack<K, V> callBack) {
        super(redisTemplate, callBack);
    }

    /**
     * 执行Redis操作
     *
     * @param key key的值
     * @return 如果需要获取数据则进行返回 无需则返回null
     */
    public V[] exec(K key) {
        return exec(OperateKV.createK(key));
    }

    /**
     * 执行Redis操作
     *
     * @param key key值
     * @param value value值
     * @return 如果需要获取数据则进行返回 无需则返回null
     */
    public V[] exec(K key, V value) {
        return exec(OperateKV.createKV(key, value));
    }

    /**
     * 执行Redis操作
     *
     * @param kv Redis操作需要的Key-Value
     * @return 如果需要获取数据则进行返回 无需则返回null
     */
    @Override
    public V[] exec(OperateKV<K, V> kv) {
        return ((RedisValueOperateCallBack<K, V>) callBack).operate(kv, redisTemplate.opsForValue(), group);
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
                V[] result = RedisValueOperate.this.exec(kv);
                operations.exec(); // 提交事务
                return result;
            }
        };
        return redisTemplate.execute(sessionCallback);
    }
}

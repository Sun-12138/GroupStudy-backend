package com.group.study.utils.redis.operate;

import com.group.study.common.state.StatusCode;
import com.group.study.exception.BusinessException;
import com.group.study.utils.redis.callback.RedisValueOperateCallBack;
import com.group.study.utils.redis.operate.param.AbstractRedisOperateParam;
import com.group.study.utils.redis.operate.param.RedisValueOperateParam;
import com.group.study.utils.redis.operate.param.factory.RedisOperateFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

public class RedisValueOperate<V> extends AbstractRedisOperate<V> {

    public RedisValueOperate(RedisTemplate<Object, Object> redisTemplate, RedisValueOperateCallBack<V> callBack) {
        super(redisTemplate, callBack);
    }

    /**
     * 执行Redis操作
     *
     * @param key key的值
     * @return 如果需要获取数据则进行返回 无需则返回null
     */
    public V[] exec(String key) {
        return exec(RedisOperateFactory.create(key));
    }

    /**
     * 执行Redis操作
     *
     * @param key key值
     * @param value value值
     * @return 如果需要获取数据则进行返回 无需则返回null
     */
    public V[] exec(String key, V value) {
        return exec(RedisOperateFactory.create(key, value));
    }

    /**
     * 执行Redis操作
     *
     * @param kv Redis操作需要的Key-Value
     * @return 如果需要获取数据则进行返回 无需则返回null
     */
    @Override
    public V[] exec(AbstractRedisOperateParam<V> kv) {
        if (!(kv instanceof RedisValueOperateParam<V> param)) {
            throw new BusinessException(StatusCode.SYSTEM_ERROR);
        }
        kv.setKey(group.generateGroup() + kv.getKey());
        return ((RedisValueOperateCallBack<V>) callBack).operate(param, redisTemplate.opsForValue());
    }

    /**
     * 执行Redis事务操作
     *
     * @param kv Redis操作需要的Key-Value
     * @return 如果需要获取数据则进行返回 无需则返回null
     */
    @Override
    public V[] execTransact(AbstractRedisOperateParam<V> kv) {
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

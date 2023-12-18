package com.group.study.utils.redis.operate;

import com.group.study.utils.redis.operate.param.AbstractRedisOperateParam;

import java.util.List;

public interface IRedisOperate<V> {

    /**
     * 执行Redis操作
     *
     * @param kv Redis操作需要的Key-Value
     * @return 如果需要获取数据则进行返回 无需则返回null
     */
    V[] exec(AbstractRedisOperateParam<V> kv);

    /**
     * 执行Redis事务操作
     *
     * @param kv Redis操作需要的Key-Value
     * @return 如果需要获取数据则进行返回 无需则返回null
     */
    V[] execTransact(AbstractRedisOperateParam<V> kv);

    /**
     * 执行Redis操作
     *
     * @param kv Redis操作需要的Key-Value
     * @return 如果需要获取数据则进行返回 无需则返回null
     */
    default List<V> execOfList(AbstractRedisOperateParam<V> kv) {
        return List.of(exec(kv));
    }

    /**
     * 执行Redis事务操作
     *
     * @param kv Redis操作需要的Key-Value
     * @return 如果需要获取数据则进行返回 无需则返回null
     */
    default List<V> execTransactOfList(AbstractRedisOperateParam<V> kv) {
        return List.of(execTransact(kv));
    }
}

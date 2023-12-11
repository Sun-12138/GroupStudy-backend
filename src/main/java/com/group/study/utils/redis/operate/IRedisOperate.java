package com.group.study.utils.redis.operate;

import java.util.List;

public interface IRedisOperate<K, V> {

    /**
     * 执行Redis操作
     *
     * @param kv Redis操作需要的Key-Value
     * @return 如果需要获取数据则进行返回 无需则返回null
     */
    V[] exec(OperateKV<K, V> kv);

    /**
     * 执行Redis事务操作
     *
     * @param kv Redis操作需要的Key-Value
     * @return 如果需要获取数据则进行返回 无需则返回null
     */
    V[] execTransact(OperateKV<K, V> kv);

    /**
     * 执行Redis操作
     *
     * @param kv Redis操作需要的Key-Value
     * @return 如果需要获取数据则进行返回 无需则返回null
     */
    default List<V> execOfList(OperateKV<K, V> kv) {
        return List.of(exec(kv));
    }

    /**
     * 执行Redis事务操作
     *
     * @param kv Redis操作需要的Key-Value
     * @return 如果需要获取数据则进行返回 无需则返回null
     */
    default List<V> execTransactOfList(OperateKV<K, V> kv) {
        return List.of(execTransact(kv));
    }
}

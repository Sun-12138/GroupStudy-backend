package com.group.study.utils.redis.operate;

import com.group.study.utils.redis.callback.IRedisOperateCallBack;
import org.springframework.data.redis.core.RedisTemplate;

public abstract class AbstractRedisOperate<K, V> implements IRedisOperate<K, V>{
    /**
     * key所在组
     */
    RedisKeyGroup<K> group;

    /**
     * Redis操作执行者
     */
    RedisTemplate<Object, Object> redisTemplate;

    /**
     * Redis操作回调对象
     */
    final IRedisOperateCallBack<K, V> callBack;

    /**
     * @param redisTemplate Redis操作执行者
     * @param callBack Redis操作回调对象
     */
    public AbstractRedisOperate(RedisTemplate<Object, Object> redisTemplate, IRedisOperateCallBack<K, V> callBack) {
        this.redisTemplate = redisTemplate;
        this.callBack = callBack;
    }

    /**
     * 添加key所在组
     *
     * @param newGroup key的新组名
     */
    public void addGroup(K newGroup) {
        if (group == null) group = new RedisKeyGroup<>();
        group.addGroup(newGroup);
    }

    /**
     * 添加key所在组
     *
     * @param newGroup key的新组名
     */
    @SafeVarargs
    public final void addGroup(K... newGroup) {
        if (group == null) group = new RedisKeyGroup<>();
        for (K group : newGroup) {
            addGroup(group);
        }
    }

    /**
     * 构建当前设置的key的所在组
     *
     * @return key所在组前缀
     */
    Object generateGroup() {
        return group.generateGroup();
    }
}

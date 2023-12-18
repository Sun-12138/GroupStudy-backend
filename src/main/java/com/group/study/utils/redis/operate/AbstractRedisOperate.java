package com.group.study.utils.redis.operate;

import com.group.study.utils.redis.callback.IRedisOperateCallBack;
import org.springframework.data.redis.core.RedisTemplate;

public abstract class AbstractRedisOperate<V> implements IRedisOperate<V> {
    /**
     * key所在组
     */
    RedisKeyGroup group;

    /**
     * Redis操作执行者
     */
    RedisTemplate<Object, Object> redisTemplate;

    /**
     * Redis操作回调对象
     */
    final IRedisOperateCallBack<V> callBack;

    /**
     * @param redisTemplate Redis操作执行者
     * @param callBack Redis操作回调对象
     */
    public AbstractRedisOperate(RedisTemplate<Object, Object> redisTemplate, IRedisOperateCallBack<V> callBack) {
        this.redisTemplate = redisTemplate;
        this.callBack = callBack;
    }

    /**
     * 添加key所在组
     *
     * @param newGroup key的新组名
     */
    public void addGroup(String newGroup) {
        if (group == null) group = new RedisKeyGroup();
        group.addGroup(newGroup);
    }

    /**
     * 添加key所在组
     *
     * @param newGroup key的新组名
     */
    public void addGroup(String... newGroup) {
        if (group == null) group = new RedisKeyGroup();
        for (String group : newGroup) {
            addGroup(group);
        }
    }

    /**
     * 构建当前设置的key的所在组
     *
     * @return key所在组前缀
     */
    String generateGroup() {
        return group.generateGroup();
    }
}

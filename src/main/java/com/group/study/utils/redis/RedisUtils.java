package com.group.study.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class RedisUtils {

    /**
     * Redis对象
     */
    private static RedisTemplate<String, String> redis;
    /**
     * 字符串操作
     */
    private static ValueOperations<String, String> strOps;
    /**
     * 列表操作
     */
    private static ListOperations<String, String> listOps;
    /**
     * 哈希操作
     */
    private static HashOperations<String, String, String> hashOps;
    /**
     * 集合操作
     */
    private static SetOperations<String, String> setOps;
    /**
     * 有序集合操作
     */
    private static ZSetOperations<String, String> zSetOps;

    @Autowired
    @Qualifier("redisTemplate")
    public void setRedis(RedisTemplate<String, String> redis) {
        RedisUtils.redis = redis;
        //初始化基础操作
        RedisUtils.strOps = redis.opsForValue();
        RedisUtils.listOps = redis.opsForList();
        RedisUtils.hashOps = redis.opsForHash();
        RedisUtils.setOps = redis.opsForSet();
        RedisUtils.zSetOps = redis.opsForZSet();
    }

    /**
     * 添加字符串类型数据
     *
     * @param key 键
     * @param value 值
     */
    public static void add(String key, String value) {
        strOps.set(key, value);
    }

    /**
     * 添加列表类型数据
     *
     * @param key 键
     * @param list 值
     */
    public static void add(String key, List<String> list) {
        for (String o : list) {
            listOps.rightPush(key, o);
        }
    }

    /**
     * 添加字典类型数据
     *
     * @param key 键
     * @param map 值
     */
    public static void add(String key, HashMap<String, String> map) {
        for (String mKey : map.keySet()) {
            add(key, mKey, map.get(mKey));
        }
    }

    /**
     * 添加字典类型数据
     *
     * @param key redis中的key
     * @param hashKey 字典中的key
     * @param value 字典中的值
     */
    public static void add(String key, String hashKey, String value) {
        hashOps.put(key, hashKey, value);
    }

    /**
     * 添加集合类型数据
     *
     * @param key 键
     * @param values 值
     */
    public static void add(String key, Set<String> values) {
        for (String o : values) {
            setOps.add(key, o);
        }
    }

    /**
     * 添加有序集合类型数据
     *
     * @param key 键
     * @param value 值
     * @param score 用于排序的值
     */
    public static void add(String key, String value, double score) {
        zSetOps.add(key, value, score);
    }

    public static String getStr(String key) {
        return strOps.get(key);
    }

    public static Map<String, String> getHash(String key) {
        return hashOps.entries(key);
    }

//    // String 操作
//    public void setValue(String key, Object value) {
//        strOps.set(key, value);
//    }
//
//    public Object getValue(String key) {
//        return strOps.get(key);
//    }
//
//    // List 操作
//    public void pushToList(String key, Object value) {
//        listOps.rightPush(key, value);
//    }
//
//    public List<Object> getList(String key, long start, long end) {
//        return listOps.range(key, start, end);
//    }
//
//    // Hash 操作
//    public void setHash(String key, Object hashKey, Object value) {
//        hashOps.put(key, hashKey, value);
//    }
//
//    public Map<Object, Object> getHash(String key) {
//        return hashOps.entries(key);
//    }
//
//    // Set 操作
//    public void addToSet(String key, Object... values) {
//        setOps.add(key, values);
//    }
//
//    public Set<Object> getSet(String key) {
//        return setOps.members(key);
//    }
//
//    // ZSet 操作
//    public void addToZSet(String key, Object value, double score) {
//        zSetOps.add(key, value, score);
//    }
//
//    public Set<Object> getZSetByRange(String key, long start, long end) {
//        return zSetOps.range(key, start, end);
//    }
}

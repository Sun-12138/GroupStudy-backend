package com.group.study.utils.redis.operate;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

public class OperateKV<K, V> {
    /**
     * Redis中的key
     */
    @Getter
    @Setter
    private K key;

    /**
     * Redis中的value或者 Hash中的hValue
     */
    private V value;

    /**
     * hash中的key
     */
    private K hKey;

    /**
     * 过期时间
     */
    @Getter
    private long timeout;

    /**
     * 过期时间单位
     */
    @Getter
    private TimeUnit unit;

    private final Mode mode;

    private OperateKV(K key, Mode mode) {
        this.key = key;
        this.mode = mode;
    }

    /**
     * 若该Operate为取值操作，无需value，则调用该方法生成OperateKV对象
     */
    public static <K, V> OperateKV<K, V> createK(K key) {
        return createK(key, -1L, null);
    }

    public static <K, V> OperateKV<K, V> createK(K key, long timeout, TimeUnit unit) {
        OperateKV<K, V> instance = new OperateKV<>(key, Mode.K);
        instance.setTimeout(timeout, unit);
        return instance;
    }

    /**
     * 若该Operate为数组操作，则调用该方法生成OperateKV对象
     */
    public static <K, V> OperateKV<K, V> createKV(K key, V value) {
        return createKV(key, value, -1L, null);
    }

    public static <K, V> OperateKV<K, V> createKV(K key, V value, long timeout, TimeUnit unit) {
        OperateKV<K, V> instance = new OperateKV<>(key, Mode.KV);
        instance.value = value;
        instance.setTimeout(timeout, unit);
        return instance;
    }

    /**
     * 若该Operate为Hash操作，则调用该方法生成OperateKV对象
     */
    public static <K, V> OperateKV<K, V> createHashKV(K key, K hKey, V value) {
        return createHashKV(key, hKey, value, -1L, null);
    }

    public static <K, V> OperateKV<K, V> createHashKV(K key, K hKey, V value, long timeout, TimeUnit unit) {
        OperateKV<K, V> instance = new OperateKV<>(key, Mode.HASH);
        instance.hKey = hKey;
        instance.value = value;
        instance.setTimeout(timeout, unit);
        return instance;
    }

    /**
     * 获取Value
     */
    public V getValue() {
        if (mode != Mode.KV && mode != Mode.HASH) {
            throw new RuntimeException("当前对象类型无法获取Value");
        }
        return value;
    }


    /**
     * 获取Hash Key
     */
    public K getHKey() {
        if (mode != Mode.HASH) {
            throw new RuntimeException("当前对象类型无法获取Hash Key");
        }
        return hKey;
    }

    /**
     * 设置过期时间
     *
     * @param timeout 过期时间
     * @param unit    过期时间单位
     */
    public void setTimeout(long timeout, TimeUnit unit) {
        this.timeout = timeout;
        this.unit = unit;
    }

    private enum Mode {
        /**
         * 单Key模式
         */
        K,
        /**
         * Key-Value类型
         */
        KV,

        /**
         * HASH类型
         */
        HASH
    }
}

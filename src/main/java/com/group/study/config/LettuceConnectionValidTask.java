package com.group.study.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class LettuceConnectionValidTask {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 五分钟检测一次Redis连接
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void task() {
        if (redisConnectionFactory instanceof LettuceConnectionFactory lettuceConnectionFactory) {
            lettuceConnectionFactory.validateConnection();
        }
    }
}

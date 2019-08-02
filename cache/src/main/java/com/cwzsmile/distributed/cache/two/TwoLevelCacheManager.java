package com.cwzsmile.distributed.cache.two;

import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author csh9016
 * @date 2019/8/2
 */
public class TwoLevelCacheManager extends RedisCacheManager {

    private RedisTemplate redisTemplate;

    public TwoLevelCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, RedisTemplate redisTemplate) {
        super(cacheWriter, defaultCacheConfiguration);
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected Cache decorateCache(Cache cache) {
        return new RedisAndLocalCache((RedisCache) cache, this);
    }

    public void receiver(String name) {
        RedisAndLocalCache cache = (RedisAndLocalCache) this.getCache(name);
        if (cache != null) {
            cache.clearLocal();
        }
    }

    public void publishMessage(String cacheName) {
        //this.redisTemplate.convertAndSend(topicName,cacheName);
    }
}

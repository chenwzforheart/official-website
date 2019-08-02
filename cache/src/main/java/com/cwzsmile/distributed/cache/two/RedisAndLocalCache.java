package com.cwzsmile.distributed.cache.two;

import com.cwzsmile.distributed.cache.two.TwoLevelCacheManager;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author csh9016
 * @date 2019/8/2
 */
public class RedisAndLocalCache implements Cache {

    ConcurrentHashMap<Object, Object> local = new ConcurrentHashMap<Object, Object>();
    RedisCache redisCache;
    TwoLevelCacheManager cacheManager;

    public RedisAndLocalCache(RedisCache redisCache, TwoLevelCacheManager cacheManager) {
        this.redisCache = redisCache;
        this.cacheManager = cacheManager;
    }

    @Override
    public String getName() {
        return redisCache.getName();
    }

    @Override
    public Object getNativeCache() {
        return redisCache.getNativeCache();
    }

    @Override
    public ValueWrapper get(Object key) {
        //一级缓存先取
        ValueWrapper wrapper = (ValueWrapper) local.get(key);
        if (wrapper != null) {
            return wrapper;
        } else {
            //二级缓存先取
            wrapper = redisCache.get(key);
            if (wrapper != null) {
                local.put(key, wrapper);
            }
            return wrapper;
        }
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        redisCache.put(key, value);
        //通知其他节点缓存更新
        clearOtherJVM();
    }

    protected void clearOtherJVM() {
        //cacheManager.publishMessage(redisCache.getName());
    }

    /**
     * 提供给cacheManager清空一级缓存
     */
    public void clearLocal() {
        this.local.clear();
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return null;
    }

    @Override
    public void evict(Object key) {
        redisCache.evict(key);
    }

    @Override
    public void clear() {

    }
}

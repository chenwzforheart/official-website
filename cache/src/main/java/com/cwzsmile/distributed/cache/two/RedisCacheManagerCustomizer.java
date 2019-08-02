package com.cwzsmile.distributed.cache.two;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.HashMap;

/**
 * @author csh9016
 * @date 2019/8/1
 */
@Configuration
public class RedisCacheManagerCustomizer {

    public CacheManagerCustomizer<RedisCacheManager> cacheManagerCustomizer() {
        return new CacheManagerCustomizer<RedisCacheManager>() {
            @Override
            public void customize(RedisCacheManager cacheManager) {
                HashMap<String, Long> expires = new HashMap<String, Long>();
                expires.put("menu", 60L);
                //cacheManager.setExpires(expires);

            }
        };
    }

}

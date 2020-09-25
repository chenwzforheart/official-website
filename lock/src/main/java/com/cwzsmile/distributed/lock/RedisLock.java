package com.cwzsmile.distributed.lock;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.RedissonWriteLock;
import org.redisson.api.RLock;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author csh9016
 * @date 2019/7/11
 */
public class RedisLock {

    private RedisTemplate<String, String> redisTemplate;

    private static Redisson redisson;

    public static void main(String[] args) {
        RLock lock = redisson.getLock("TestLock");
        try {
            if (lock.tryLock(300, 30, TimeUnit.MILLISECONDS)) {
                try {
                    //执行业务逻辑
                    TimeUnit.SECONDS.sleep(2);
                } finally {
                    lock.unlock();
                }

            }else {
                //未获取锁
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * setNX做分布式锁，获取锁
     */
    public void getNXLock() {
        redisTemplate.opsForValue().setIfAbsent("lock_key", "lock_value", 30, TimeUnit.SECONDS);
    }

    /**
     * 释放锁
     */
    public void releaseNXLock() {
        //redisTemplate.execute();
    }

    /**
     * redisson获取锁
     */
    public void redissonLock() {
        RLock lock1 = redisson.getFairLock("lock1");
        RLock lock2 = redisson.getFairLock("lock2");
        RLock lock3 = redisson.getFairLock("lock3");
        RedissonRedLock multiLock = new RedissonRedLock(lock1, lock2, lock3);
        multiLock.lock();
        multiLock.unlock();
        //redisson.getRedLock(new RedissonWriteLock(null, "LL"));
    }

    /**
     * redisson释放锁
     */
    public void releaseRedissonLock() {
        //redisson.getRedLock(new RedissonWriteLock(null, "LL"));
    }
}

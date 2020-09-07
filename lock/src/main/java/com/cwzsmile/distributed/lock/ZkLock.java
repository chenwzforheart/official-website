package com.cwzsmile.distributed.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author csh9016
 * @date 2019/7/11
 */
public class ZkLock {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            es.execute(()->{
                new ZkLock().getLock();
            });
        }

    }

    public void getLock() {
        String zkUrl = "zookeeper01.scm.test.baozun.cn:2181,zookeeper02.scm.test.baozun.cn:2181,zookeeper03.scm.test.baozun.cn:2181";
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkUrl, retryPolicy);
        client.start();
        InterProcessMutex lock = new InterProcessMutex(client, "/test_lock");
        try {
            if (lock.acquire(200, TimeUnit.MILLISECONDS)) {
                try{
                    System.out.println(Thread.currentThread().getName() + " ::处理业务");
                    Thread.sleep(20_000);
                }finally {
                    lock.release();
                }
            }else {
                System.out.println(Thread.currentThread().getName() + " ::未获取锁");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.cwzsmile.distributed.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.*;

/**
 * @author mc
 * @date 2020-08-26.
 */
public class AppB {

    private static final InterProcessMutex interProcessMutex;
    private static final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(2);
    private static final CuratorFramework client;
    private static final String zkUrl = "localhost:2181";
    private static final String path = "/zklock/distribution_lock";
    private static final Integer delayTimeForClean = 10_000;

    static {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.newClient(zkUrl, retryPolicy);
        client.start();
        interProcessMutex = new InterProcessMutex(client,path);
    }

    // 加锁，支持超时，可重入
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        try {
            return interProcessMutex.acquire(timeout, unit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    // 解锁
    public boolean unlock() {
        try {
            interProcessMutex.release();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            executorService.schedule(new Runnable() {
                @Override
                public void run() {
                    client.close();
                }
            }, delayTimeForClean, TimeUnit.MILLISECONDS);
        }
        return true;
    }

    public static void main(String[] args) throws Exception{
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            es.execute(()->{
                try {
                    AppB appB = new AppB();
                    appB.tryLock(10, TimeUnit.SECONDS);
                    System.out.println(Thread.currentThread().getName()+"::Transaction start");
                    TimeUnit.SECONDS.sleep(30);
                    System.out.println(Thread.currentThread().getName()+"::Transaction end");
                    appB.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


    }
}

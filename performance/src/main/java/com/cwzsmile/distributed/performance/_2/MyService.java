package com.cwzsmile.distributed.performance._2;

import java.util.concurrent.CountDownLatch;

/**
 * @author csh9016
 * @date 2019/11/13
 */
public class MyService {

    private CountDownLatch down = new CountDownLatch(1);

    public void testMethod() {
        try {
            System.out.println(Thread.currentThread().getName() + "准备");
            down.await();
            System.out.println(Thread.currentThread().getName() + "结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void downMethod() {
        System.out.println("开始");
        down.countDown();
    }
}

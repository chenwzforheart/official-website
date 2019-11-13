package com.cwzsmile.distributed.performance._1;

import java.util.concurrent.CountDownLatch;

/**
 * @author csh9016
 * @date 2019/11/13
 */
public class MyThread extends Thread {

    private CountDownLatch maxRunner;

    public MyThread(CountDownLatch maxRunner) {
        super();
        this.maxRunner = maxRunner;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            maxRunner.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

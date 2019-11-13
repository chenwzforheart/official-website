package com.cwzsmile.distributed.performance._4;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author csh9016
 * @date 2019/11/13
 */
public class MyThread extends Thread {

    private CyclicBarrier cbRef;

    public MyThread(CyclicBarrier cbRef) {
        this.cbRef = cbRef;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((int) (Math.random()*1000));
            System.out.println(Thread.currentThread().getName() + " 到了！" + System.currentTimeMillis());
            cbRef.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

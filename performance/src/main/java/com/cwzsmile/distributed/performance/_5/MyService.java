package com.cwzsmile.distributed.performance._5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author csh9016
 * @date 2019/11/13
 */
public class MyService {

    private CyclicBarrier cbRef;

    public MyService(CyclicBarrier cbRef) {
        this.cbRef = cbRef;
    }

    public void beginRun() {
        try {
            long sleepValue = (int) (Math.random() * 10_000);
            Thread.sleep(sleepValue);
            System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() + " begin跑第1阶段" + (cbRef.getNumberWaiting() + 1));
            cbRef.await();
            System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() + "   end跑第1阶段" + (cbRef.getNumberWaiting()));
            sleepValue = (int) (Math.random() * 10_000);
            Thread.sleep(sleepValue);
            System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() + " begin跑第2阶段" + (cbRef.getNumberWaiting() + 1));
            cbRef.await();
            System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() + "   end跑第2阶段" + (cbRef.getNumberWaiting()));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

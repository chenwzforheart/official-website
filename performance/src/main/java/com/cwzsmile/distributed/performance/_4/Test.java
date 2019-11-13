package com.cwzsmile.distributed.performance._4;

import java.util.concurrent.CyclicBarrier;

/**
 * @author csh9016
 * @date 2019/11/13
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cbRef = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("全来了！");
            }
        });
        for (int i = 0; i < 4; i++) {
            ThreadA threadA1 = new ThreadA(cbRef);
            threadA1.start();
            Thread.sleep(2000);
        }
    }
}

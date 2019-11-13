package com.cwzsmile.distributed.performance._4;

import java.util.concurrent.CyclicBarrier;

/**
 * @author csh9016
 * @date 2019/11/13
 */
public class Run {

    public static void main(String[] args) {
        CyclicBarrier cbRef = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("全都到了！");
            }
        });
        MyThread[] threadArray = new MyThread[5];
        for (int i = 0; i < threadArray.length; i++) {
            threadArray[i] = new MyThread(cbRef);
        }
        for (int i = 0; i < threadArray.length; i++) {
            threadArray[i].start();
        }
        System.out.println("nihao");
    }
}

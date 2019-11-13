package com.cwzsmile.distributed.performance._3;

import java.util.concurrent.CountDownLatch;

/**
 * @author csh9016
 * @date 2019/11/13
 */
public class MyThread extends Thread {

    private CountDownLatch comingTag;
    private CountDownLatch waitTag;
    private CountDownLatch waitRunTag;
    private CountDownLatch beginTag;
    private CountDownLatch endTag;

    public MyThread(CountDownLatch comingTag, CountDownLatch waitTag, CountDownLatch waitRunTag, CountDownLatch beginTag, CountDownLatch endTag) {
        this.comingTag = comingTag;
        this.waitTag = waitTag;
        this.waitRunTag = waitRunTag;
        this.beginTag = beginTag;
        this.endTag = endTag;
    }

    @Override
    public void run() {
        try {
            System.out.println("运动员使用不同交通工具不同速度到达起点，正向这头走");
            Thread.sleep((int) (Math.random() * 10_000));
            System.out.println(Thread.currentThread().getName() + "到起跑点了！");
            comingTag.countDown();
            System.out.println("等到裁判说准备！");
            waitTag.await();
            System.out.println("各就各位！准备起跑姿势！");
            Thread.sleep((int) (Math.random() * 10_000));
            waitRunTag.countDown();
            beginTag.await();
            System.out.println(Thread.currentThread().getName() + " 运动员起跑 并且跑赛过程用时不确定");
            Thread.sleep((int) (Math.random() * 10_000));
            endTag.countDown();
            System.out.println(Thread.currentThread().getName() + " 运动员到达终点");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

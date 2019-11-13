package com.cwzsmile.distributed.performance._5;

/**
 * @author csh9016
 * @date 2019/11/13
 */
public class ThreadA extends Thread {

    private MyService myService;

    public ThreadA(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.beginRun();
    }
}

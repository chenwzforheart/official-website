package com.cwzsmile.distributed.performance._2;

/**
 * @author csh9016
 * @date 2019/11/13
 */
public class MyThread extends Thread{

    private MyService myService;

    public MyThread(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod();
    }
}

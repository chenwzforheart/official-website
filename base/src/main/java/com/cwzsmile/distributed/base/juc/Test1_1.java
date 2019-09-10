package com.cwzsmile.distributed.base.juc;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author csh9016
 * @date 2019/9/10
 */

public class Test1_1 {
    public static void main(String[] args) {
        MyService1 service = new MyService1();
        Thread1A a = new Thread1A(service);
        a.start();
    }
}

class Test1_2 {
    public static void main(String[] args) {
        MyService1 service = new MyService1();
        Thread1A a = new Thread1A(service);
        Thread1B b = new Thread1B(service);
        a.start();
        b.start();
        try {
            Thread.sleep(3_1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("map容量：" + service.map.size());
        Iterator iterator = service.map.entrySet().iterator();
    }
}

class Test5{
    public static void main(String[] args) {
        try {
            MyService5 myService = new MyService5();
            Thread5A a = new Thread5A(myService);
            a.start();
            Thread.sleep(1000);
            Thread5B b = new Thread5B(myService);
            b.start();
        } catch (InterruptedException e) {

        }
    }
}

class MyService1 {

    public ConcurrentMap map = new ConcurrentHashMap();


}
class MyService5 {

    public ConcurrentMap map = new ConcurrentSkipListMap();

    public MyService5() {
        for (int i = 0; i < 5; i++) {
            map.put("key" + (i + 1), "value" + (i + 1));
        }
    }
}

class Thread5A extends Thread {
    private MyService5 service;

    public Thread5A(MyService5 service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        try {
            Iterator iterator = service.map.keySet().iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
                Thread.sleep(3_000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Thread5B extends Thread {
    private MyService5 service;

    public Thread5B(MyService5 service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.map.put("z", "value");
    }
}


class Thread1A extends Thread {
    private MyService1 service;

    public Thread1A(MyService1 service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50000; i++) {
            service.map.put("ThreadA" + (i + 1), "ThreadA" + i + 1);
            System.out.println("ThreadA" + (i + 1));
        }
    }
}

class Thread1B extends Thread {
    private MyService1 service;

    public Thread1B(MyService1 service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50000; i++) {
            service.map.put("ThreadB" + (i + 1), "ThreadB" + i + 1);
            System.out.println("ThreadB" + (i + 1));
        }
    }
}



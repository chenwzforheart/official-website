package com.cwzsmile.distributed.base.juc;

import lombok.Data;

import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author csh9016
 * @date 2019/9/10
 */
public class Test2_2 {

    public static void main(String[] args) {
        MyService111 service = new MyService111();
        ThreadAA a = new ThreadAA(service);
        ThreadAA b = new ThreadAA(service);
        a.start();
        b.start();
    }
}

@Data
class Userinfo1 implements Comparable<Userinfo1> {
    private int id;
    private String username;

    public Userinfo1(int id, String username) {
        this.id = id;
        this.username = username;
    }

    @Override
    public int compareTo(Userinfo1 u) {
        if (this.getId() < u.getId()) {
            return -1;
        }
        if (this.getId() > u.getId()) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Userinfo1 userinfo1 = (Userinfo1) o;

        if (id != userinfo1.id) return false;
        return username != null ? username.equals(userinfo1.username) : userinfo1.username == null;
    }

    @Override
    public int hashCode() {
        /*int result = 31+id;
        result = 31 * result + (username != null ? username.hashCode() : 0);*/
        final int prime=31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }
}

class MyService111 {
    public ConcurrentSkipListSet map = new ConcurrentSkipListSet();

    public MyService111() {
        Userinfo1 userInfo1 = new Userinfo1(1, "username1");
        Userinfo1 userInfo2 = new Userinfo1(3, "username3");
        Userinfo1 userInfo5 = new Userinfo1(5, "username5");
        Userinfo1 userInfo4 = new Userinfo1(4, "username4");
        Userinfo1 userInfo44 = new Userinfo1(4, "username4");
        Userinfo1 userInfo3 = new Userinfo1(2, "username2");
        map.add(userInfo1);
        map.add(userInfo3);
        map.add(userInfo5);
        map.add(userInfo4);
        map.add(userInfo44);
        map.add(userInfo2);
    }
}

class ThreadAA extends Thread {
    private MyService111 service;

    public ThreadAA(MyService111 service) {
        this.service = service;
    }

    @Override
    public void run() {
        try {
            while (!service.map.isEmpty()) {
                Userinfo1 userinfo = (Userinfo1) service.map.pollFirst();
                System.out.println(userinfo.getId() + " " + userinfo.getUsername());
                Thread.sleep((long) (Math.random() * 1000));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

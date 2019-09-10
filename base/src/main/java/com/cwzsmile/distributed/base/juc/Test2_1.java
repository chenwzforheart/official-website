package com.cwzsmile.distributed.base.juc;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author csh9016
 * @date 2019/9/10
 */
public class Test2_1 {
    public static void main(String[] args) {
        MyService11 service11 = new MyService11();
        ThreadA a = new ThreadA(service11);
        ThreadA b = new ThreadA(service11);
        a.start();
        b.start();
    }


}

@Data
class UserInfo implements Comparable<UserInfo> {

    private int id;
    private String username;

    public UserInfo(int id, String username) {
        this.id = id;
        this.username = username;
    }

    @Override
    public int compareTo(UserInfo u) {
        if (this.getId() < u.getId()) {
            return -1;
        }
        if (this.getId() > u.getId()) {
            return 1;
        }
        return 0;
    }
}

class MyService11 {
    public ConcurrentSkipListMap map = new ConcurrentSkipListMap();

    public MyService11() {
        UserInfo userInfo1 = new UserInfo(1, "username1");
        UserInfo userInfo2 = new UserInfo(3, "username3");
        UserInfo userInfo5 = new UserInfo(5, "username5");
        UserInfo userInfo4 = new UserInfo(4, "username4");
        UserInfo userInfo3 = new UserInfo(2, "username2");
        map.put(userInfo1, "value1");
        map.put(userInfo3, "value3");
        map.put(userInfo5, "value5");
        map.put(userInfo4, "value4");
        map.put(userInfo2, "value2");
    }
}

class ThreadA extends Thread {
    private MyService11 myService;

    public ThreadA(MyService11 myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        try {
            while (!myService.map.isEmpty()) {
                Map.Entry entry = myService.map.pollFirstEntry();
                UserInfo userinfo = (UserInfo) entry.getKey();
                System.out.println(userinfo.getId() + " " + userinfo.getUsername() + " " + entry.getValue());
                Thread.sleep((long) (Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

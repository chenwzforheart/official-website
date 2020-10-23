package com.cwzsmile.distributed.base.file;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author csh9016
 * @date 2020/10/23
 */
public class ByteTest {

    public static void main(String[] args) {
        byte[] bytes = new byte[100];
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i]);
        }
        System.out.println(bytes.toString());
    }

    public static void main1(String[] args) {
        //byte[] a = new byte[Integer.MAX_VALUE-2];
        //Object[] a = new Object[Integer.MAX_VALUE / 4];
        List list = new ArrayList(100*1024*1024);
        for (int i = 0; i < 100*1024*1024; i++) {
            list.add(new Object());
        }
        System.out.println("end");
        try {
            TimeUnit.SECONDS.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

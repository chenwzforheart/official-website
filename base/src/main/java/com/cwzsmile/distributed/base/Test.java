package com.cwzsmile.distributed.base;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author csh9016
 * @date 2019/9/20
 */
public class Test {

    public static void main(String[] args) {
        Queue<String> aa = new ConcurrentLinkedQueue<>();
        aa.add("1");
        aa.add("2");
        aa.add("2");
        System.out.println(aa.peek());
        System.out.println(aa);
    }
}

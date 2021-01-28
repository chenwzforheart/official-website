package com.cwzsmile.distributed.gateway;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author csh9016
 * @date 2021/1/28
 */
public class EventBusMain {

    public static void main2(String[] args) {
        EventBus eventBus = new EventBus("test");
        // 注册监听者
        eventBus.register(new OrderEventListener());
        eventBus.register(new OrderEventListener());
        // 发布消息
        for (int i = 0; i < 1; i++) {
            eventBus.post(new OrderMessage("123123"));
        }
    }

    public static void main(String[] args) {
        EventBus eventBus = new AsyncEventBus("test", Executors.newSingleThreadExecutor());
        // 注册监听者
        eventBus.register(new OrderEventListener());
        eventBus.register(new OrderEventListener());
        // 发布消息
        for (int i = 0; i < 1; i++) {
            eventBus.post(new OrderMessage("123123"));
        }
    }
}

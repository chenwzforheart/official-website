package com.cwzsmile.distributed.gateway.event;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author csh9016
 * @date 2021/2/7
 */
public class DisruptorMain {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Disruptor<LongEvent> disruptor = new Disruptor<>(new LongEventFactory(), 1024 * 1024, executor, ProducerType.SINGLE, new YieldingWaitStrategy());
        // // 一个Handler对应一条线程,串行操作
        disruptor.handleEventsWith(new LongEventHandler()).handleEventsWith(new LongEventHandler()).handleEventsWith(new LongEventHandler());

        /*disruptor.handleEventsWith(new LongEventHandler());
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.handleEventsWith(new LongEventHandler());*/

        //disruptor.handleEventsWith(new LongEventHandler(), new LongEventHandler(), new LongEventHandler());

        //disruptor.handleEventsWithWorkerPool(new LongEventHandler(),new LongEventHandler());

        /*EventHandlerGroup<LongEvent> group = disruptor.handleEventsWith(new LongEventHandler(), new LongEventHandler());
        group.then(new LongEventHandler());*/

        /*LongEventHandler handler1 = new LongEventHandler();
        LongEventHandler handler2 = new LongEventHandler();
        LongEventHandler handler3 = new LongEventHandler();
        LongEventHandler handler4 = new LongEventHandler();
        LongEventHandler handler5 = new LongEventHandler();
        disruptor.handleEventsWith(handler1, handler4);
        disruptor.after(handler1).handleEventsWith(handler2);
        disruptor.after(handler4).handleEventsWith(handler5);
        disruptor.after(handler2, handler5).handleEventsWith(handler3);*/

        disruptor.start();

        // 7.创建RingBuffer容器
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        // 8.创建生产者
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        // 9.指定缓冲区大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (int i = 1; i <= 100; i++) {
            byteBuffer.putLong(0, i);
            producer.onData(byteBuffer);
        }
        //10.关闭disruptor和executor
        disruptor.shutdown();
        executor.shutdown();
    }
}

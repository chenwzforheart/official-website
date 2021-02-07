package com.cwzsmile.distributed.gateway.event;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author csh9016
 * @date 2021/2/7
 */
public class LongEventHandler implements EventHandler<LongEvent> ,WorkHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName() + "::消费者(Only once):" + System.identityHashCode(this) + " value:" + event.getValue());
    }

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName() + "::消费者:" + System.identityHashCode(this) + " value:" + event.getValue());
    }
}

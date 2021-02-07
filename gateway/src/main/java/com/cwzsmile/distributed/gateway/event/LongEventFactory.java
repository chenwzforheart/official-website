package com.cwzsmile.distributed.gateway.event;

import com.lmax.disruptor.EventFactory;

/**
 * @author csh9016
 * @date 2021/2/7
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}

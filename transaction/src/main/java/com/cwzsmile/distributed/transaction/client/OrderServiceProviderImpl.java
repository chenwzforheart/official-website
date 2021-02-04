package com.cwzsmile.distributed.transaction.client;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author csh9016
 * @date 2021/2/4
 */
public class OrderServiceProviderImpl implements IOrderServiceProvider{

    private AtomicInteger orderIdCounter=new AtomicInteger(0);

    @Override
    public Integer queryByOrderId() {
        try {
            return CompletableFuture.supplyAsync(this::queryByOrderId1).get(500, TimeUnit.MILLISECONDS);
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }


    public Integer queryByOrderId1() {
        int c = orderIdCounter.getAndIncrement();
        if (c < 10) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return c;
    }


    public void reset() {
        orderIdCounter.getAndSet(0);
    }
}

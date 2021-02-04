package com.cwzsmile.distributed.transaction.client;

/**
 * @author csh9016
 * @date 2021/2/4
 */
public class OrderServiceProvider implements IOrderServiceProvider {

    @Override
    public Integer queryByOrderId(){
        System.out.println(Thread.currentThread().getName());
        return 1;
    }
}

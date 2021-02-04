package com.cwzsmile.distributed.transaction.client;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author csh9016
 * @date 2021/2/4
 */
public class OrderServiceProviderTest {

    @Test
    public void queryByOrderId() {
        OrderServiceProvider orderServiceProvider = new OrderServiceProvider();
        Integer r = new QueryOrderIdCommand(orderServiceProvider).execute();
        System.out.println(r);
    }
}

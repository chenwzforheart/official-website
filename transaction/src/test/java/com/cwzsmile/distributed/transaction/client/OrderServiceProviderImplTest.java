package com.cwzsmile.distributed.transaction.client;

import com.netflix.hystrix.HystrixCommand;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author csh9016
 * @date 2021/2/4
 */
public class OrderServiceProviderImplTest {

    private OrderServiceProviderImpl orderServiceProvider = new OrderServiceProviderImpl();

    public static String rep(String base, Object... vars) {
        String result = base;
        for (int i = 0; i < vars.length; i++) {
            result = result.replaceFirst("\\{\\}", vars[i].toString());
        }
        return result;
    }

    @Test
    public void queryByOrderId() throws InterruptedException {
        orderServiceProvider.reset();
        int i = 1;
        for (; i < 15; i++) {
            HystrixCommand<Integer> command = new QueryOrderIdCommand(orderServiceProvider);
            Integer r = command.execute();
            String method = r == -1 ? "fallback" : "run";
            System.out.println(rep("call {} times,result:{},method:{},isCircuitBreakerOpen:{}", i, r, method, command.isCircuitBreakerOpen()));
        }

        Thread.sleep(6000);

        for (; i < 20; i++) {
            HystrixCommand<Integer> command = new QueryOrderIdCommand(orderServiceProvider);
            Integer r = command.execute();
            String method = r == -1 ? "fallback" : "run";
            System.out.println(rep("call {} times,result:{},method:{},isCircuitBreakerOpen:{}", i, r, method, command.isCircuitBreakerOpen()));
        }

        System.out.println("end");
    }
}

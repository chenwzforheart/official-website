package com.cwzsmile.distributed.transaction.client;

import com.netflix.hystrix.*;

/**
 * @author csh9016
 * @date 2021/2/4
 */
public class QueryOrderIdCommand extends HystrixCommand<Integer> {


    private IOrderServiceProvider orderServiceProvider;

    public QueryOrderIdCommand(IOrderServiceProvider orderServiceProvider) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("orderService"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("queryByOrderId"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerRequestVolumeThreshold(9)
                        .withCircuitBreakerSleepWindowInMilliseconds(5000)
                        .withCircuitBreakerErrorThresholdPercentage(50)
                        //.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        //.withExecutionIsolationSemaphoreMaxConcurrentRequests(10)
                        .withExecutionTimeoutEnabled(true))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(10))
        );
        this.orderServiceProvider = orderServiceProvider;
    }

    @Override
    protected Integer run() throws Exception {
        return orderServiceProvider.queryByOrderId();
    }

    @Override
    protected Integer getFallback() {
        return -1;
    }
}

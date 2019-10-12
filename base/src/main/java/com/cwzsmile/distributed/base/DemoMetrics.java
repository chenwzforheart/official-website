package com.cwzsmile.distributed.base;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author csh9016
 * @date 2019/10/11
 */
@Component
public class DemoMetrics implements MeterBinder {

    AtomicInteger count = new AtomicInteger(0);

    @Lazy
    @Autowired
    private Map<String,ThreadPoolExecutor> poolMap;

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        Gauge.builder("pool.cl", "9999", c -> this.queueSize())
                .tags("host", "localhost")
                .description("demo of custom meter binder")
                .register(meterRegistry);
        Gauge.builder("pool.mw", "9999", c -> this.queueSize())
                .tags("host", "localhost")
                .description("demo of custom meter binder")
                .register(meterRegistry);
    }

    public int queueSize() {
        return poolMap.size();
    }
}

package com.cwzsmile.distributed.base.spring;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * @author csh9016
 * @date 2019/7/30
 */
public class MethodExecutionEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    public void methodToMonitor() {
        MethodExecutionEvent beginEvt = new MethodExecutionEvent(this, "methodToMonitor", MethodExecutionEvent.MethodExecutionStatus.BEGIN);
        this.eventPublisher.publishEvent(beginEvt);
        //执行实际方法逻辑
        MethodExecutionEvent endEvt = new MethodExecutionEvent(this, "methodToMonitor", MethodExecutionEvent.MethodExecutionStatus.END);
        this.eventPublisher.publishEvent(endEvt);
    }
}

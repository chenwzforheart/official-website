package com.cwzsmile.distributed.base.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author csh9016
 * @date 2019/7/30
 */
public class MethodExecutionEventListener implements ApplicationListener {

    public void onApplicationEvent(ApplicationEvent evt) {
        if (evt instanceof MethodExecutionEvent) {
            //执行处理逻辑
        }
    }
}

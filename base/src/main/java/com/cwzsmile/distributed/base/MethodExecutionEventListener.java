package com.cwzsmile.distributed.base;

import java.util.EventListener;

/**
 * @author csh9016
 * @date 2019/7/30
 */
public interface MethodExecutionEventListener extends EventListener {

    /**
     * @param evt
     */
    void onMethodBegin(MethodExecutionEvent evt);

    /**
     * @param evt
     */
    void onMethodEnd(MethodExecutionEvent evt);

}

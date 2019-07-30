package com.cwzsmile.distributed.base;

/**
 * @author csh9016
 * @date 2019/7/30
 */
public class SimpleMethodExecutionEventListener implements MethodExecutionEventListener {

    public void onMethodBegin(MethodExecutionEvent evt) {
        String methodName = evt.getMethodName();
        System.out.println("start to execute the method[" + methodName + "].");
    }

    public void onMethodEnd(MethodExecutionEvent evt) {
        String methodName = evt.getMethodName();
        System.out.println("finished to execute the method[" + methodName + "].");
    }
}

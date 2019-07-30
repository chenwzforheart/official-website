package com.cwzsmile.distributed.base;

import java.util.EventObject;

/**
 * @author csh9016
 * @date 2019/7/30
 */
public class MethodExecutionEvent extends EventObject {

    private String methodName;

    public MethodExecutionEvent(Object source) {
        super(source);
    }

    public MethodExecutionEvent(Object source, String methodName) {
        super(source);
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}

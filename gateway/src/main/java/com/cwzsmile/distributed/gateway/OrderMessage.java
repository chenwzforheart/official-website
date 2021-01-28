package com.cwzsmile.distributed.gateway;

/**
 * @author csh9016
 * @date 2021/1/28
 */
public class OrderMessage {

    /**
     * 命令对应的内容
     */
    private String orderContent;

    public OrderMessage() {
    }

    public OrderMessage(String orderContent) {
        this.orderContent = orderContent;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }
}

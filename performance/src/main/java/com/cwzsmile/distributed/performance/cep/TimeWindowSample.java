package com.cwzsmile.distributed.performance.cep;

import io.siddhi.core.SiddhiAppRuntime;
import io.siddhi.core.SiddhiManager;
import io.siddhi.core.event.Event;
import io.siddhi.core.stream.input.InputHandler;
import io.siddhi.core.stream.output.StreamCallback;
import io.siddhi.core.util.EventPrinter;

/**
 * @author csh9016
 * @date 2021/4/13
 */
public class TimeWindowSample {
    public static void main(String[] args) throws InterruptedException {
        //创建一个Siddhi Manager
        SiddhiManager siddhiManager = new SiddhiManager();
        // Siddhi Application
        //先定义流的格式
        String siddhiApp = "define stream StockEventStream (symbol string, price float, volume long);" +
                "@info(name = 'query1') " +
                "from StockEventStream#window.time(5 sec) " +
                "select symbol, sum(price) as price, sum(volume) as volume " +
                "group by symbol " +
                "insert into AggregateStockStream ;";
        // 生成运行环境
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(siddhiApp);
        // 给AggregateStockStream 添加回调 -- 从流中检索匹配的事件，并输出
        siddhiAppRuntime.addCallback("AggregateStockStream", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                for (int i = 0; i < events.length; i++) {
                    System.out.println(events[i].toString());
                }
                EventPrinter.print(events);
            }
        });
        // 检索输入处理程序以将事件推入Siddhi
        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("StockEventStream");
        // 启动事件处理
        siddhiAppRuntime.start();
        // 发送事件到Siddhi
        inputHandler.send(new Object[]{"IBM", 100f, 100L});
        Thread.sleep(1000);
        inputHandler.send(new Object[]{"IBM", 200f, 300L});
        inputHandler.send(new Object[]{"WSO2", 60f, 200L});
        Thread.sleep(1000);
        inputHandler.send(new Object[]{"WSO2", 70f, 400L});
        inputHandler.send(new Object[]{"GOOG", 50f, 30L});
        Thread.sleep(1000);
        inputHandler.send(new Object[]{"IBM", 200f, 400L});
        Thread.sleep(2000);
        inputHandler.send(new Object[]{"WSO2", 70f, 50L});
        Thread.sleep(2000);
        inputHandler.send(new Object[]{"WSO2", 80f, 400L});
        inputHandler.send(new Object[]{"GOOG", 60f, 30L});
        Thread.sleep(1000);
        inputHandler.send(new Object[]{"WSO2", 80f, 400L});
        inputHandler.send(new Object[]{"GOOG", 60f, 30L});
        Thread.sleep(1000);
        // Shutdown the runtime
        siddhiAppRuntime.shutdown();
        // Shutdown Siddhi Manager
        siddhiManager.shutdown();
    }
}

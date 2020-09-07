package com.cwzsmile.distributed.lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author mc
 * @date 2020-08-26.
 */
public class AppA {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("localhost:2181", 10 * 1000);
        zkClient.createPersistent("/app/running", true);
        zkClient.subscribeDataChanges("/app/running", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {

            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("AppA stoped!!");
            }
        }));
    }
}

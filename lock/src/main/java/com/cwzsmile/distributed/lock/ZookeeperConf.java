package com.cwzsmile.distributed.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author csh9016
 * @date 2019/9/5
 */
@Configuration
public class ZookeeperConf {

    @Value("${zk.url:localhost:2181}")
    private String zkUrl;

    @Bean
    public CuratorFramework getCuratorFramework() {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkUrl, retryPolicy);
        client.start();
        return client;
    }

}

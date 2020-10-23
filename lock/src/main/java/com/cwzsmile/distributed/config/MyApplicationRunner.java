package com.cwzsmile.distributed.config;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author csh9016
 * @date 2020/10/23
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
    //创建zk连接
    private ZkClient zkClient = new ZkClient("127.0.0.1:2181");
    private String path = "/election";
    @Value("${server.port}")
    private String serverPort;

    {
        zkClient.setZkSerializer(new ZkSerializer() {
            @Override
            public byte[] serialize(Object o) throws ZkMarshallingError {
                return o.toString().getBytes();
            }

            @Override
            public Object deserialize(byte[] bytes) throws ZkMarshallingError {
                if (bytes == null) {
                    return bytes;
                }
                return new String(bytes);
            }
        });
    }


    //项目启动时候执行
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("项目启动成功...");
        createEphemeral();
        //创建事件监听
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("开启重新选举");
                createEphemeral();
            }
        });
    }

    private String pid() {
        return ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
    }

    private String host() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "-1";
        }
    }

    private void createEphemeral() {
        try {
            zkClient.createEphemeral(path, host() + ":" + pid());
            ElectionMaster.isSurvival = true;
            System.out.println("serverPort：" + serverPort + ",选举成功....");
        } catch (Exception e) {
            ElectionMaster.isSurvival = false;
        }

    }
}

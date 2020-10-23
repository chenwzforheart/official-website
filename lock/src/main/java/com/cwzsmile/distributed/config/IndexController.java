package com.cwzsmile.distributed.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author csh9016
 * @date 2020/10/23
 */

@RestController
public class IndexController {
    //1.项目启动时会在zk上创建一个相同临时节点
    //2.谁能够创建成功谁就是主服务器
    //3.使用服务器监听节点是否被删除，如果节点被删除的话重新开始选举（重新创建）
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/getServerInfo")
    public String getServerInfo() {
        return "serverPort:" + serverPort + (ElectionMaster.isSurvival ? "选举为主服务器" : "选举为从服务器");
    }
}

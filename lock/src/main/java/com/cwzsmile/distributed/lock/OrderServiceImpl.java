package com.cwzsmile.distributed.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author csh9016
 * @date 2019/9/5
 */
@Slf4j
@Service
public class OrderServiceImpl {

    String lockPath = "/lock/order";
    @Autowired
    private CuratorFramework zkClient;

    public void makeOrderType(String type) {
        String path = lockPath + "/" + type;
        log.info("try do job for {}", type);
        try {
            InterProcessMutex lock = new InterProcessMutex(zkClient, path);
            if (lock.acquire(10, TimeUnit.HOURS)) {
                try {
                    Thread.sleep(5_000);
                    log.info("do job " + type + "done");
                    //即使获取了锁，也应该坚持数据是否处理过
                } finally {
                    lock.release();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

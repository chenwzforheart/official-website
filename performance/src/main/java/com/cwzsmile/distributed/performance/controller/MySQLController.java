package com.cwzsmile.distributed.performance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author csh9016
 * @date 2019/11/11
 */
@RestController
@RequestMapping("/sql")
public class MySQLController {

    private static ExecutorService ex = Executors.newFixedThreadPool(16);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/insert")
    public String testInsert() {
        StopWatch stopWatch = new StopWatch("测试jdbcTemplate-1万数据");
        stopWatch.start("1.准备数据");
        SmsRequestDtl detail = null;
        List<SmsRequestDtl> list = new ArrayList<>();
        int taskCount = 1000;
        for (int i = 0; i < taskCount; i++) {
            detail = new SmsRequestDtl();
            detail.setSmsRequestId(UUID.randomUUID().toString());
            detail.setActivityName("act::" + i);
            detail.setTemplateId(-1L);
            list.add(detail);
        }
        stopWatch.stop();
        /*StringBuffer sql = new StringBuffer("insert into t_msg_sms_request_dtl(sms_request_id, activity_name, template_id) VALUES");
        for (SmsRequestDtl temp : list) {
            sql.append(String.format("(\"%s\",\"%s\",%d),", temp.getSmsRequestId(), temp.getActivityName(), temp.getTemplateId()));
        }
        sql.setCharAt(sql.length() - 1, ';');*/

        stopWatch.start("2.数据入库");
        String prefix = "insert into t_msg_sms_request_dtl(sms_request_id, activity_name, template_id) VALUES";
        final CountDownLatch endGate = new CountDownLatch(taskCount);
        for (SmsRequestDtl temp : list) {
            ex.execute(()->{
                jdbcTemplate.update(prefix + String.format("(\"%s\",\"%s\",%d);", temp.getSmsRequestId(), temp.getActivityName(), temp.getTemplateId()));
                endGate.countDown();
            });
        }
        try {
            endGate.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        return stopWatch.prettyPrint();
    }
}

package com.cwzsmile.distributed.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

/**
 * @author csh9016
 * @date 2019/9/25
 */
@Configuration
@EnableScheduling
@Slf4j
public class ScheduleConfig {

    @Scheduled(cron = "0/5 * * * * ?")
    public void one(){
        log.info("in one...");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void two() {
        log.info("in two...");
    }
}

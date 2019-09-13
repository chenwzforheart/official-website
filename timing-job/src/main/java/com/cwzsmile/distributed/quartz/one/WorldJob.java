package com.cwzsmile.distributed.quartz.one;

import com.cwzsmile.distributed.quartz.DemoService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author mc
 * @date 2019-09-12.
 */
@Data
@Slf4j
public class WorldJob implements Job {

    @Autowired
    private DemoService demoService;

    private String name;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        name = context.getJobDetail().getJobDataMap().getString("name");
        demoService.get();
        log.info("Hello " + name);
    }
}

package com.cwzsmile.distributed.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author csh9016
 * @date 2019/9/6
 */
public class MyTask implements Job {

    @Autowired
    private DemoService demoService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        demoService.get();
    }
}

package com.cwzsmile.distributed.quartz;

import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

/**
 * @author csh9016
 * @date 2019/9/6
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public CronTriggerFactoryBean trigger() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setJobClass(Job.class);
        jobDetailFactoryBean.setGroup("a");
        jobDetailFactoryBean.setName("an");
        cronTriggerFactoryBean.setCronExpression("0/10 * * * * ?");
        cronTriggerFactoryBean.setName("triName");
        return cronTriggerFactoryBean;
    }

    @Bean
    public SchedulerFactoryBean scheduler() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(trigger().getObject());
        schedulerFactoryBean.setConfigLocation(new ClassPathResource("application.properties"));
        schedulerFactoryBean.setStartupDelay(3);
        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setJobFactory(new AutoWiringSpringBeanJobFactory());
        return schedulerFactoryBean;
    }
}

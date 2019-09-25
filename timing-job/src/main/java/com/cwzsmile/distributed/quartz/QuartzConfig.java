package com.cwzsmile.distributed.quartz;

import com.cwzsmile.distributed.quartz.one.HelloJob;
import com.cwzsmile.distributed.quartz.one.WorldJob;
import io.netty.util.NetUtil;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author csh9016
 * @date 2019/9/6
 */
//@Configuration
public class QuartzConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(HelloJob.class);
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("name", "Quartz");
        bean.setJobDataAsMap(map);
        bean.setDurability(true);
        return bean;
    }

    @Bean
    public JobDetailFactoryBean workDetail() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(WorldJob.class);
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("name", "Quartz");
        bean.setJobDataAsMap(map);
        bean.setDurability(true);
        return bean;
    }

    @Bean
    public SimpleTriggerFactoryBean simpleTrigger() {
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        bean.setJobDetail(jobDetail().getObject());
        bean.setStartDelay(0);
        bean.setRepeatInterval(1000);
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean cronTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(jobDetail().getObject());
        bean.setCronExpression("0/10 * * * * ?");
        bean.setMisfireInstruction(2);
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean cron5Trigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(workDetail().getObject());
        //todo cron表达式变更需要修改数据库，新增时代码里设置才管用
        bean.setCronExpression("1/10 * * * * ?");
        return bean;
    }

    @Bean
    public SchedulerFactoryBean myScheduler() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(cronTrigger().getObject(),cron5Trigger().getObject());
        bean.setJobFactory(jobFactory());
        //todo 这三行控制quartz集群模式
        bean.setConfigLocation(new ClassPathResource("application.properties"));
        bean.setDataSource(dataSource);
        //bean.setOverwriteExistingJobs(true);
        return bean;
    }

    @Bean
    public JobFactory jobFactory() {
        return new JobFactory();
    }

    public static class JobFactory extends AdaptableJobFactory {
        @Autowired
        private AutowireCapableBeanFactory beanFactory;

        @Override
        protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
            Object jobInstance = super.createJobInstance(bundle);
            beanFactory.autowireBean(jobInstance);
            return jobInstance;
        }
    }

    /*@Autowired
    private DataSource dataSource;

    //配置JobFactory
    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public CronTriggerFactoryBean trigger() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setCronExpression("0/10 * * * * ?");
        cronTriggerFactoryBean.setName("triName");
        return cronTriggerFactoryBean;
    }

    @Bean
    public SchedulerFactoryBean scheduler(JobFactory jobFactory) throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setStartupDelay(3);
        Properties properties = quartzProperties();
        properties.setProperty("org.quartz.scheduler.instanceName", NetworkInterfaceManager.INSTANCE.getLocalHostAddress() + ":" + properties.get("server.port"));
        schedulerFactoryBean.setQuartzProperties(properties);
        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setJobFactory(jobFactory);
        return schedulerFactoryBean;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("application.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    public final class AutoWiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

        private transient AutowireCapableBeanFactory beanFactory;

        @Override
        public void setApplicationContext(final ApplicationContext context) {
            beanFactory = context.getAutowireCapableBeanFactory();
        }

        @Override
        protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
            final Object job = super.createJobInstance(bundle);
            beanFactory.autowireBean(job);
            return job;
        }
    }*/

}

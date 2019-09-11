package com.cwzsmile.distributed.quartz;

import io.netty.util.NetUtil;
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
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author csh9016
 * @date 2019/9/6
 */
@Configuration
public class QuartzConfig {

    @Autowired
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
    }

}

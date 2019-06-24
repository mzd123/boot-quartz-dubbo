package com.test.mzd.quartz;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class SchedulerConfig {
    @Autowired
    private SpringJobFactory springJobFactory;

    @Bean(name = "SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setAutoStartup(true);
        factory.setStartupDelay(5);//延时5秒启动
        factory.setQuartzProperties(quartzProperties());
        //将job放入spring容器中，不然job中没法注入spring中的对象
        factory.setJobFactory(springJobFactory);
        return factory;
    }
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        //这个东西如果不配置的话，quartz有自己默认的配置文件
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
    /*
     * 通过SchedulerFactoryBean获取Scheduler
     */
    @Bean(name = "Scheduler")
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }

}
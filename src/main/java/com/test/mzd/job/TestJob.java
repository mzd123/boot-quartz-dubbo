package com.test.mzd.job;

import com.test.mzd.service.TestService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestJob implements Job {
    @Autowired
    private TestService testService;


    @Value(value = "${server.port}")
    private Integer port;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("aaaa====" + port + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        testService.updategoods();
    }
}

package com.augurit.ads.fw.base;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.Assert;
import org.springside.modules.utils.ThreadUtils;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class SpringCronJob implements Runnable {
    private String cronExpression;
    private int shutdownTimeout = 2147483647;
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    public SpringCronJob() {
    }

    @PostConstruct
    public void start() {
        Assert.hasText(this.cronExpression,"cronExpression不能为空！");
        this.threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        this.threadPoolTaskScheduler.setThreadNamePrefix("SpringCronJob");
        this.threadPoolTaskScheduler.initialize();
        this.threadPoolTaskScheduler.schedule(this, new CronTrigger(this.cronExpression));
    }

    @PreDestroy
    public void stop() {
        ScheduledExecutorService scheduledExecutorService = this.threadPoolTaskScheduler.getScheduledExecutor();
        ThreadUtils.normalShutdown(scheduledExecutorService, this.shutdownTimeout, TimeUnit.SECONDS);
    }

    public abstract void run();

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public void setShutdownTimeout(int shutdownTimeout) {
        this.shutdownTimeout = shutdownTimeout;
    }
}
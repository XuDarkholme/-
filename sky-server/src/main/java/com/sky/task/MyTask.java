package com.sky.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 自定义人物类
 */
@Component
@Slf4j
public class MyTask {

    /**
     * 定时任务
     */
    @Scheduled(cron = "0/88888 * * * * ?")
    public void executeTask(){
        log.info("定时任务执行");
    }
}

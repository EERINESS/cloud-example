package com.eureka.client.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * Created by zzq on 2020/7/13.
 */
@Component
public class ScheduleRunner {

    @Scheduled(fixedRate = 2000)
    public void job1() {
        System.out.println(Thread.currentThread() + ", job1@"  + LocalTime.now());
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
        }
    }

    @Scheduled(fixedDelay = 2000)
    public void job2() {
        System.out.println(Thread.currentThread() + ", job2@" + LocalTime.now());
    }
}

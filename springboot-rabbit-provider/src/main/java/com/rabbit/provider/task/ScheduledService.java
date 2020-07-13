package com.rabbit.provider.task;

import com.rabbit.provider.service.StudentService;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by zzq on 2020/7/13.
 */
public class ScheduledService {
    private StudentService studentService;
    /**
     * 每一秒触发一次，写入 IVR 数据到数据库
     */
    @Scheduled(cron = "0/1 * * * * ?")
    public void refreshData() {
        if (studentService != null) {
            //studentService.refreshStudent();
        }
    }
}

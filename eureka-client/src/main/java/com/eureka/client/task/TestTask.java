package com.eureka.client.task;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.SQLDataException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zzq on 2020/7/14.
 */
//@Service
@Log
public class TestTask implements Runnable{
    private Thread thread = null;
    private boolean flag = false;

    @PostConstruct
    protected void init() {
        thread = new Thread(this);
        flag = true;
        thread.start();
    }

    @PreDestroy
    protected void finish() throws Exception {
        if (thread != null) {
            flag = false;
            thread.join();
        }
    }

    @Override
    public void run() {
        while (flag) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date = new Date();
                log.info(format.format(date) + " run : TestTask ");
                Thread.sleep(9000);
                throw new SQLDataException();
            } catch (Exception e) {
                //e.printStackTrace();
                //Thread.currentThread().interrupt();
                log.info("TestTask () run()>>" + e.getMessage());
            }
        }
    }
}

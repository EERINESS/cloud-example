package com.eureka.client.controller;

import com.eureka.client.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zzq on 2020/10/29.
 */
@RestController
public class TestController {
    @Autowired
    private SchoolService schoolService;

    @GetMapping("test")
    public String test(){
        return "hi this is client";
    }

    @PostMapping("insert_school")
    public void insertSchool(@RequestParam("id") Integer id,
                             @RequestParam("name") String name,
                             @RequestParam("date") String date){
        schoolService.insertSchool(id, name, date);
    }
}

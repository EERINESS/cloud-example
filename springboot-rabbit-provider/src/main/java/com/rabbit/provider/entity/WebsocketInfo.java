package com.rabbit.provider.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by zzq on 2020/7/13.
 */
@Setter
@Getter
public class WebsocketInfo {
    private String schoolId;
    private String sessionId;
    private List<Student> studentList;
    private Integer type;
}

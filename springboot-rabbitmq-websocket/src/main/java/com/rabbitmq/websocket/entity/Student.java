package com.rabbitmq.websocket.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by zzq on 2020/4/21.
 */
@Setter
@Getter
public class Student {
    private int id;
    private String name;
    private String sex;
    private int schoolId;
}

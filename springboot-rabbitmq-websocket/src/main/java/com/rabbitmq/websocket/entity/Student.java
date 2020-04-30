package com.rabbitmq.websocket.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by zzq on 2020/4/21.
 */
@Setter
@Getter
public class Student implements Serializable {
    private int id;
    private String name;
    private String sex;
    private int schoolId;
}

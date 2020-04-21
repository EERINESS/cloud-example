package com.rabbitmq.websocket.service;

import com.rabbitmq.websocket.entity.School;

import java.util.List;

/**
 * Created by zzq on 2020/4/21.
 */
public interface SchoolService {
    List<School> selectAllSchool();
}

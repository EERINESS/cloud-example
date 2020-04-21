package com.rabbitmq.websocket.service.impl;

import com.rabbitmq.websocket.entity.School;
import com.rabbitmq.websocket.mapper.SchoolMapper;
import com.rabbitmq.websocket.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zzq on 2020/4/21.
 */
@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public List<School> selectAllSchool() {
        return schoolMapper.selectAllSchool();
    }
}

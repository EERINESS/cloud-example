package com.rabbit.provider.service.impl;

import com.rabbit.provider.entity.School;
import com.rabbit.provider.mapper.SchoolMapper;
import com.rabbit.provider.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zzq on 2020/7/9.
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

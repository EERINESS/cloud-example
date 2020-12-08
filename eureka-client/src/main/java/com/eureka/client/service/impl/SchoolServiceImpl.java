package com.eureka.client.service.impl;

import com.eureka.client.service.SchoolService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zzq on 2020/12/8.
 */
@Service
public class SchoolServiceImpl implements SchoolService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public void insertSchool(Integer id, String name, String date) {
        String strSql = String.format("INSERT INTO school(id, name, date) VALUES (%d, '%s', '%s')",
                id, name, date);
        jdbcTemplate.update(strSql);
        updateName();
    }

    @Override
    public void updateName() {
        String strSql = "UPDATE school SET name = 'AAA' WHERE id = 11";
        int count = jdbcTemplate.update(strSql);
        int i = 1/0;
    }
}

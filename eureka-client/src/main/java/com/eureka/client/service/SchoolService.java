package com.eureka.client.service;

import org.springframework.stereotype.Service;

/**
 * Created by zzq on 2020/12/8.
 */
@Service
public interface SchoolService {
    void insertSchool(Integer id, String name, String date);

    void updateName();
}

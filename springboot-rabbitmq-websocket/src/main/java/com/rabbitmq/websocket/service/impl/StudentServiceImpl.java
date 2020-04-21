package com.rabbitmq.websocket.service.impl;

import com.rabbitmq.websocket.entity.Student;
import com.rabbitmq.websocket.mapper.StudentMapper;
import com.rabbitmq.websocket.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zzq on 2020/4/21.
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> selectAllStudent() {
        return studentMapper.selectAllStudent();
    }

    @Override
    public List<Student>  selectStudentBySchoolId(int schoolId) {
        return studentMapper.selectStudentBySchoolId(schoolId);
    }
}

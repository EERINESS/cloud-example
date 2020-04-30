package com.rabbitmq.websocket.service;

import com.rabbitmq.websocket.entity.Student;

import java.util.List;

/**
 * Created by zzq on 2020/4/21.
 */
public interface StudentService {
    List<Student> selectAllStudent();

    List<Student>  selectStudentBySchoolId(String schoolId);

    Integer updateStudent(Student student);

    void updateWebsocket(int schoolId);
}

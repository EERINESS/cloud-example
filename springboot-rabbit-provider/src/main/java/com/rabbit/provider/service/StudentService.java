package com.rabbit.provider.service;

import com.rabbit.provider.entity.Student;

import java.util.List;

/**
 * Created by zzq on 2020/7/9.
 */
public interface StudentService {
    List<Student> selectAllStudent();

    List<Student>  selectStudentBySchoolId(String schoolId);

    Integer updateStudent(Student student);

    List<Student> getStudent();

    void refreshStudent(String schoolId);
}

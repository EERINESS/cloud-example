package com.rabbitmq.websocket.controller;

import com.rabbitmq.websocket.entity.School;
import com.rabbitmq.websocket.entity.Student;
import com.rabbitmq.websocket.service.SchoolService;
import com.rabbitmq.websocket.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zzq on 2020/4/21.
 */
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private SchoolService schoolService;

    @GetMapping("/all_school")
    public List<School> selectAllSchool(){
        return schoolService.selectAllSchool();
    }

    @GetMapping("/all_student")
    public List<Student> selectAllStudent(){
        return studentService.selectAllStudent();
    }

    @GetMapping("get_student")
    public List<Student>  selectStudentBySchoolId(@RequestParam("school_id") int schoolId){
        return studentService.selectStudentBySchoolId(schoolId);
    }

    @GetMapping("update_student")
    public Integer updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @GetMapping("test")
    public void testWebsocket(@RequestParam int id){
        studentService.updateWebsocket(id);
    }

}

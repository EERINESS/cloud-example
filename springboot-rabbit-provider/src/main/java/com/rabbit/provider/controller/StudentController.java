package com.rabbit.provider.controller;

import com.rabbit.provider.entity.Student;
import com.rabbit.provider.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zzq on 2020/7/8.
 */
@RestController
@Api(tags = {"student相关操作"})
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("get_student")
    @ApiOperation(value = "获取对应school的student信息")
    public List<Student> selectStudentBySchoolId(@RequestParam("school_id") @ApiParam(value = "school的ID", example = "0") String schoolId){
        return studentService.selectStudentBySchoolId(schoolId);
    }

    @GetMapping("cancel_get_student")
    @ApiOperation(value = "获取对应school的student信息")
    public List<Student> cancelSelectStudentBySchoolId(@RequestParam("school_id") @ApiParam(value = "school的ID", example = "0") String schoolId){
        return studentService.selectStudentBySchoolId(schoolId);
    }

    @GetMapping("update_student")
    @ApiOperation(value = "修改student信息")
    public Integer updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @GetMapping("test")
    @ApiOperation(value = "测试student信息")
    public void testWebsocket(@RequestParam @ApiParam(value = "student的ID", example = "0") int id ){

    }
}

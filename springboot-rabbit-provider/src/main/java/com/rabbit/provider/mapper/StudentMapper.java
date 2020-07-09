package com.rabbit.provider.mapper;

import com.rabbit.provider.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by zzq on 2020/7/8.
 */
@Mapper
public interface StudentMapper {
    @Select("select * from student")
    List<Student> selectAllStudent();

    @Select("select * from student where school_id in (#{schoolId})")
    List<Student> selectStudentBySchoolId(String schoolId);

    @Update("update student set name=#{name},sex=#{sex},school_id=#{schoolId} where id=#{id}")
    Integer updateStudent(Student student);
}

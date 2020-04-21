package com.rabbitmq.websocket.mapper;

import com.rabbitmq.websocket.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zzq on 2020/4/21.
 */
@Mapper
public interface StudentMapper {
    @Select("select * from student")
    List<Student> selectAllStudent();

    @Select("select * from student where school_id = #{schoolId}")
    List<Student>  selectStudentBySchoolId(int schoolId);
}

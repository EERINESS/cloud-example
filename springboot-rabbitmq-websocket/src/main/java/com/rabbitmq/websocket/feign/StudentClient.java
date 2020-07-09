package com.rabbitmq.websocket.feign;

import com.rabbitmq.websocket.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by zzq on 2020/7/9.
 */
@FeignClient(value = "rabbitmq-provider")
public interface StudentClient {
    @GetMapping("get_student")
    List<Student> selectStudentBySchoolId(@RequestParam("school_id") String schoolId);

    @GetMapping("update_student")
    Integer updateStudent(@RequestBody Student student);

    @GetMapping("test")
    void testWebsocket(@RequestParam int id );
}

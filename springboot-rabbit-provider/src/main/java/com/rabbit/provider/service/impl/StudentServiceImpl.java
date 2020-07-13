package com.rabbit.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rabbit.provider.entity.Student;
import com.rabbit.provider.entity.WebReturn;
import com.rabbit.provider.entity.WebsocketInfo;
import com.rabbit.provider.mapper.StudentMapper;
import com.rabbit.provider.service.StudentService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zzq on 2020/7/9.
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public void refreshStudent(String schoolId){
        List<Student> studentList = selectStudentBySchoolId(schoolId);
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String websocketStr = operations.get("websocket_session");
        if (websocketStr != null){
            JSONArray jsonArray = JSONArray.parseArray(websocketStr);
            List<WebsocketInfo> websocketInfoList = jsonArray.toJavaList(WebsocketInfo.class);
            for (WebsocketInfo websocketInfo : websocketInfoList){
                if (websocketInfo.getSchoolId().indexOf(schoolId) != -1){
                    websocketInfo.setStudentList(studentList);
                    websocketInfo.setType(202);
                    rabbitTemplate.convertAndSend("studentExchange", "student.update", JSON.toJSONString(websocketInfo));
                }
            }
        }
    }

    @Override
    public List<Student> getStudent(){
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String agentStatusStr = operations.get("agent_status");
        List<Student> studentList;
        if (agentStatusStr == null){
            studentList = selectAllStudent();
            String studentStr = JSONObject.toJSONString(studentList);
            operations.set("agent_status", studentStr);
        }else {
            JSONArray jsonArray = JSONArray.parseArray(agentStatusStr);
            studentList = jsonArray.toJavaList(Student.class);
        }
        return studentList;
    }

    @Override
    public List<Student> selectAllStudent() {
        return studentMapper.selectAllStudent();
    }

    @Override
    public List<Student>  selectStudentBySchoolId(String schoolId) {
        return studentMapper.selectStudentBySchoolId(schoolId);
    }

    @Override
    public Integer updateStudent(Student student) {
        Integer count = studentMapper.updateStudent(student);
        return count;
    }
}

package com.rabbitmq.websocket.service.impl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.websocket.entity.Student;
import com.rabbitmq.websocket.entity.WebReturn;
import com.rabbitmq.websocket.mapper.StudentMapper;
import com.rabbitmq.websocket.service.CacheService;
import com.rabbitmq.websocket.service.StudentService;
import com.rabbitmq.websocket.websocket.StudentWebsocket;
import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zzq on 2020/4/21.
 */
@Log
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    private CacheService cacheService;
    @Autowired
    StudentWebsocket studentWebsocket;

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

    @Override
    public void updateWebsocket(int schoolId){
        WebReturn webReturn  = new WebReturn();
//        if (cacheService.gainCache() == null){
//            return;
//        }
        Map<String, Map<String, Object>> mapMap = StudentWebsocket.sessionMap;
        Session session;
        for (Map map : mapMap.values()){
            String mapSchoolId = map.get("schoolId").toString();
            String[] arrayId = mapSchoolId.split(",");
            for (String id : arrayId){
                if (Integer.valueOf(id) == schoolId){
                    session = (Session) map.get("session");
                    if (session != null){
                        List<Student> studentList = selectStudentBySchoolId(mapSchoolId);
                        webReturn.setData(studentList);
                        webReturn.setCode(202);
                        Map<String, Object> map1 = new HashMap<>();
                        map1.put("session", session);
                        map1.put("result", webReturn);
                        //rabbitTemplate.convertAndSend("topicExchange", "topic.da", JSON.toJSONString(map1));
                        studentWebsocket.onMessage(session, JSON.toJSONString(webReturn));
                        break;
                    }
                }
            }
        }
    }

}

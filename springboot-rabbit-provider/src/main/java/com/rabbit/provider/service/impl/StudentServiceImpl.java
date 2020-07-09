package com.rabbit.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.rabbit.provider.entity.Student;
import com.rabbit.provider.entity.WebReturn;
import com.rabbit.provider.mapper.StudentMapper;
import com.rabbit.provider.service.CacheService;
import com.rabbit.provider.service.StudentService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CacheService cacheService;

    public static Map<String, Map<String, Object>> sessionMap = new HashMap<>();

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
        //Map<String, Map<String, Object>> mapMap = StudentWebsocket.sessionMap;
        Map<String, Map<String, Object>> mapMap = new HashMap<>();
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
                        rabbitTemplate.convertAndSend("topicExchange", "topic.da", JSON.toJSONString(map1));
                        break;
                    }
                }
            }
        }
    }
}

package com.rabbitmq.websocket.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.websocket.entity.Student;
import com.rabbitmq.websocket.entity.WebReturn;
import com.rabbitmq.websocket.mapper.StudentMapper;
import com.rabbitmq.websocket.service.CacheService;
import com.rabbitmq.websocket.service.StudentService;
import com.rabbitmq.websocket.websocket.StudentWebsocket;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
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
    private StudentWebsocket studentWebsocket;
    @Autowired
    private CacheService cacheService;

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
        updateWebsocket(student.getSchoolId());
        return count;
    }

    @Override
    public void updateWebsocket(int schoolId){
        WebReturn webReturn  = new WebReturn();
        if (cacheService.gainCache() == null){
            return;
        }
        Map<String, Map<String, Object>> mapMap = cacheService.gainCache();
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
                        studentWebsocket.onMessage(session, JSONObject.toJSONString(webReturn));
                        break;
                    }
                }
            }
        }
    }

}

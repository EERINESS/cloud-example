package com.rabbitmq.websocket.mapper;

import com.rabbitmq.websocket.entity.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zzq on 2020/4/21.
 */
@Mapper
public interface SchoolMapper {
    @Select("select * from school")
    List<School> selectAllSchool();
}

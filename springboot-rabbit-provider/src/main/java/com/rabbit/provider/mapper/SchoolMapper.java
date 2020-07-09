package com.rabbit.provider.mapper;

import com.rabbit.provider.entity.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zzq on 2020/7/8.
 */
@Mapper
public interface SchoolMapper {
    @Select("select * from school")
    List<School> selectAllSchool();
}

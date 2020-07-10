package com.rabbit.provider.mapper;

import com.rabbit.provider.entity.WebsocketSession;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zzq on 2020/7/9.
 */
@Mapper
public interface WebsocketSessionMapper {
    @Insert("insert into websocket_session values(#{sessionId},#{schoolId})")
    Integer insertWebsocketSession(WebsocketSession session);

    @Delete("delete from websocket_session where school_id in (#{schoolId})")
    Integer deleteWebsocketSessionBySchoolId(String schoolId);

    @Update("update websocket_session set school_id=#{schoolId} where session_id=#{sessionId}")
    Integer updateWebsocketSession(WebsocketSession session);

    @Select("select * from websocket_session")
    List<WebsocketSession> selectAllWebsocketSession();

    @Select("select * from websocket_session where session_id=#{sessionId}")
    WebsocketSession selectWebsocketSessionBySessionId(String schoolId);

}

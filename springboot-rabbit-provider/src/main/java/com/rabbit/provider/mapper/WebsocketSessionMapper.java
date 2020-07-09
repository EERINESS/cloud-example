package com.rabbit.provider.mapper;

import com.rabbit.provider.entity.WebsocketSession;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zzq on 2020/7/9.
 */
@Mapper
public interface WebsocketSessionMapper {
    @Insert("insert into websocket_session set session_id=#{sessionId},school_id=#{schoolId}")
    Integer insertWebsocketSession(WebsocketSession session);

    @Delete("select * from websocket_session where school_id in (#{schoolId})")
    List<WebsocketSession> deleteWebsocketSessionBySchoolId(String schoolId);

    @Update("update websocket_session set school_id=#{schoolId} where session_id=#{sessionId}")
    Integer updateWebsocketSession(WebsocketSession session);

    @Select("select * from websocket_session")
    List<WebsocketSession> selectAllWebsocketSession();

    @Select("select * from websocket_session where session_id=#{sessionId}")
    WebsocketSession selectWebsocketSessionBySessionId(String schoolId);

}

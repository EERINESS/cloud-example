package com.rabbit.provider.service;

import com.rabbit.provider.entity.WebsocketSession;

import java.util.List;


/**
 * Created by zzq on 2020/7/9.
 */
public interface CacheService {
    Integer insertWebsocketSession(WebsocketSession session);

    Integer deleteWebsocketSessionBySchoolId(String schoolId);

    Integer updateWebsocketSession(WebsocketSession session);

    List<WebsocketSession> selectAllWebsocketSession();

    WebsocketSession selectWebsocketSessionBySessionId(String schoolId);
}

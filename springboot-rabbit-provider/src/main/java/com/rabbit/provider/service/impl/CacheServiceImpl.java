package com.rabbit.provider.service.impl;

import com.rabbit.provider.entity.WebsocketSession;
import com.rabbit.provider.mapper.WebsocketSessionMapper;
import com.rabbit.provider.service.CacheService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zzq on 2020/7/9.
 */
@Log
@Service
@CacheConfig(cacheNames = "websocket")
public class CacheServiceImpl implements CacheService {
    @Autowired
    private WebsocketSessionMapper websocketSessionMapper;

    @CachePut(key = "#result.sessionId",condition = "#result!=null")
    @Override
    public Integer insertWebsocketSession(WebsocketSession session) {
        return websocketSessionMapper.insertWebsocketSession(session);
    }

    @CacheEvict(key="#id")
    @Override
    public List<WebsocketSession> deleteWebsocketSessionBySchoolId(String schoolId) {
        return websocketSessionMapper.deleteWebsocketSessionBySchoolId(schoolId);
    }

    @CachePut(key = "#result.sessionId",condition = "#result!=null")
    @Override
    public Integer updateWebsocketSession(WebsocketSession session) {
        return websocketSessionMapper.updateWebsocketSession(session);
    }

    @Caching(
            cacheable = {@Cacheable(key="#sessionId")},
            put = {
                    @CachePut(key="#result.sessionId"),
                    @CachePut(key="#result.schoolId")
            }
    )
    @Override
    public WebsocketSession selectWebsocketSessionBySessionId(String sessionId) {
        return websocketSessionMapper.selectWebsocketSessionBySessionId(sessionId);
    }

    @Override
    public List<WebsocketSession> selectAllWebsocketSession() {
        return websocketSessionMapper.selectAllWebsocketSession();
    }

}

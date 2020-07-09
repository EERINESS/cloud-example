package com.rabbitmq.websocket.service;

import java.util.Map;

/**
 * Created by zzq on 2020/5/6.
 */
public interface CacheService {
    Map<String, Object>  saveCache(String sessionId, Map<String, Object> group);

    Map<String, Object> gainCacheBySession(String sessionId);

    Map<String, Map<String, Object>> gainCache();

    void deleteCacheBySession(String sessionId);
}

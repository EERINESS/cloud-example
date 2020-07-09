package com.rabbitmq.websocket.service.impl;

import com.rabbitmq.websocket.service.CacheService;
import lombok.extern.java.Log;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by zzq on 2020/5/6.
 */
@Log
@Service
@CacheConfig(cacheNames = "websocket")
public class CacheServiceImpl implements CacheService {
    /**
     * 保存用户session到播放列表中
     * @param sessionId
     * @param group
     * @return
     */
    @CachePut(cacheNames = "websocket", key = "#sessionId")
    @Override
    public Map<String, Object> saveCache(String sessionId, Map<String, Object> group){
        log.info("save value " + group.get("schoolId") + " with sessionId  -->" + sessionId);
        return  group;
    }

    /**
     * 获取对应用户的session数据
     * @param sessionId
     * @return
     */
    @Cacheable(cacheNames = "websocket", sync = true)
    @Override
    public Map<String, Object> gainCacheBySession(String sessionId) {
        return null;
    }

    /**
     * 获取所有用户的session数据
     * @return
     */
    @Cacheable(cacheNames = "websocket", sync = true)
    @Override
    public Map<String, Map<String, Object>> gainCache() {
        return null;
    }

    /**
     * 删除对应用户的缓存session数据
     * @param sessionId
     */
    @CacheEvict(cacheNames = "websocket")
    @Override
    public void deleteCacheBySession(String sessionId) {}
}

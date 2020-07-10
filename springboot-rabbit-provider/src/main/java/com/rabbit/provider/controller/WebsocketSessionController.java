package com.rabbit.provider.controller;

import com.rabbit.provider.entity.Student;
import com.rabbit.provider.entity.WebsocketSession;
import com.rabbit.provider.service.CacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zzq on 2020/7/10.
 */
@RestController
@Api(tags = {"websocket的会话相关操作"})
public class WebsocketSessionController {
    @Autowired
    private CacheService cacheService;

    @PutMapping("insert_session")
    @ApiOperation(value = "添加session信息")
    public Integer insertWebsocketSession(@RequestBody WebsocketSession session){
        return cacheService.insertWebsocketSession(session);
    }

    @DeleteMapping("delete_session")
    @ApiOperation(value = "删除对应session信息")
    public Integer deleteWebsocketSessionBySchoolId(@RequestParam("session_id") @ApiParam(value = "websocketSession的ID", example = "0") String sessionId){
        return cacheService.deleteWebsocketSessionBySchoolId(sessionId);
    }

    @GetMapping("update_session")
    @ApiOperation(value = "修改session信息")
    public Integer updateWebsocketSession(@RequestBody WebsocketSession session){
        return cacheService.updateWebsocketSession(session);
    }

    @GetMapping("get_session")
    @ApiOperation(value = "获取对应session信息")
    public WebsocketSession selectWebsocketSessionBySessionId(@RequestParam("session_id") @ApiParam(value = "websocketSession的ID", example = "0") String sessionId){
        return cacheService.selectWebsocketSessionBySessionId(sessionId);
    }

    @GetMapping("all_session")
    @ApiOperation(value = "获取所有session信息")
    public List<WebsocketSession> selectAllWebsocketSession(){
        return cacheService.selectAllWebsocketSession();
    }

}

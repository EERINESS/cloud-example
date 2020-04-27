package com.rabbitmq.websocket.entity;

/**
 * Created by zzq on 2020/4/20.
 */
public class SocketMsg {
    private String messageId;//发送者.
    private String messageData;//消息内容.
    private String createTime;//时间

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageData() {
        return messageData;
    }

    public void setMessageData(String messageData) {
        this.messageData = messageData;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}

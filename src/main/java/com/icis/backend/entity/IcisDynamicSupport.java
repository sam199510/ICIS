package com.icis.backend.entity;

import java.util.Date;

/**
 * 与社区动态点赞表对应的实体类
 */
public class IcisDynamicSupport {
    private Long id;

    private Long dynamicId;

    private Long supportorId;

    private Date supporterTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Long dynamicId) {
        this.dynamicId = dynamicId;
    }

    public Long getSupportorId() {
        return supportorId;
    }

    public void setSupportorId(Long supportorId) {
        this.supportorId = supportorId;
    }

    public Date getSupporterTime() {
        return supporterTime;
    }

    public void setSupporterTime(Date supporterTime) {
        this.supporterTime = supporterTime;
    }
}
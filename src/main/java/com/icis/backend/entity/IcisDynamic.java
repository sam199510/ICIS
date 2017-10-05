package com.icis.backend.entity;

import java.util.Date;

/**
 * 与社区动态表对应的实体类
 */
public class IcisDynamic {
    private Long id;

    private Long creatorId;

    private Date publishTime;

    private String publishContent;

    private String publishPhoto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishContent() {
        return publishContent;
    }

    public void setPublishContent(String publishContent) {
        this.publishContent = publishContent == null ? null : publishContent.trim();
    }

    public String getPublishPhoto() {
        return publishPhoto;
    }

    public void setPublishPhoto(String publishPhoto) {
        this.publishPhoto = publishPhoto == null ? null : publishPhoto.trim();
    }
}
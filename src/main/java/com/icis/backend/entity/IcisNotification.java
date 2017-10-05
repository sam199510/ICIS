package com.icis.backend.entity;

import java.util.Date;

/**
 * 与通知表对应的实体类
 */
public class IcisNotification {
    private Long id;

    private Long extent;

    private String content;

    private String unit;

    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExtent() {
        return extent;
    }

    public void setExtent(Long extent) {
        this.extent = extent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
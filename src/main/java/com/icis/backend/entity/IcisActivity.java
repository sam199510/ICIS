package com.icis.backend.entity;

import java.util.Date;

/**
 * 活动表实体类
 */
public class IcisActivity {
    private Long id;

    private String title;

    private String image;

    private Date publishTime;

    private String time;

    private String position;

    private String content1;

    private String content2;

    private String content3;

    private Date startTime;

    private Date finalTime;

    private Date allowSignInStartTime;

    private Date allowSignInFinalTime;

    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1 == null ? null : content1.trim();
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2 == null ? null : content2.trim();
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3 == null ? null : content3.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(Date finalTime) {
        this.finalTime = finalTime;
    }

    public Date getAllowSignInStartTime() {
        return allowSignInStartTime;
    }

    public void setAllowSignInStartTime(Date allowSignInStartTime) {
        this.allowSignInStartTime = allowSignInStartTime;
    }

    public Date getAllowSignInFinalTime() {
        return allowSignInFinalTime;
    }

    public void setAllowSignInFinalTime(Date allowSignInFinalTime) {
        this.allowSignInFinalTime = allowSignInFinalTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
package com.icis.backend.model;

import java.util.Date;

public class IcisDynamicComment {
    private String dynamicCommentorNickname;

    private String dynamicCommentContent;

    private Date dynamicCommentTime;

    public String getDynamicCommentorNickname() {
        return dynamicCommentorNickname;
    }

    public void setDynamicCommentorNickname(String dynamicCommentorNickname) {
        this.dynamicCommentorNickname = dynamicCommentorNickname;
    }

    public String getDynamicCommentContent() {
        return dynamicCommentContent;
    }

    public void setDynamicCommentContent(String dynamicCommentContent) {
        this.dynamicCommentContent = dynamicCommentContent;
    }

    public Date getDynamicCommentTime() {
        return dynamicCommentTime;
    }

    public void setDynamicCommentTime(Date dynamicCommentTime) {
        this.dynamicCommentTime = dynamicCommentTime;
    }
}

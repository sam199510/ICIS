package com.icis.backend.model;

import java.util.Date;

public class IcisDynamicSupport {
    private String dynamicSupportorNickname;

    private Date dynamicSupportTime;

    public String getDynamicSupportorNickname() {
        return dynamicSupportorNickname;
    }

    public void setDynamicSupportorNickname(String dynamicSupportorNickname) {
        this.dynamicSupportorNickname = dynamicSupportorNickname;
    }

    public Date getDynamicSupportTime() {
        return dynamicSupportTime;
    }

    public void setDynamicSupportTime(Date dynamicSupportTime) {
        this.dynamicSupportTime = dynamicSupportTime;
    }

}

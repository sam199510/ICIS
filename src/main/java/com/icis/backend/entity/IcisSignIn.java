package com.icis.backend.entity;

import java.util.Date;

public class IcisSignIn {
    private Long id;

    private Date signInTime;

    private String signInAddress;

    private Long signInOrId;

    private String signInActivity;

    private Date signInActivityStartTime;

    private Date signInActivityFinalTime;

    private String signInActivityContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Date signInTime) {
        this.signInTime = signInTime;
    }

    public String getSignInAddress() {
        return signInAddress;
    }

    public void setSignInAddress(String signInAddress) {
        this.signInAddress = signInAddress == null ? null : signInAddress.trim();
    }

    public Long getSignInOrId() {
        return signInOrId;
    }

    public void setSignInOrId(Long signInOrId) {
        this.signInOrId = signInOrId;
    }

    public String getSignInActivity() {
        return signInActivity;
    }

    public void setSignInActivity(String signInActivity) {
        this.signInActivity = signInActivity == null ? null : signInActivity.trim();
    }

    public Date getSignInActivityStartTime() {
        return signInActivityStartTime;
    }

    public void setSignInActivityStartTime(Date signInActivityStartTime) {
        this.signInActivityStartTime = signInActivityStartTime;
    }

    public Date getSignInActivityFinalTime() {
        return signInActivityFinalTime;
    }

    public void setSignInActivityFinalTime(Date signInActivityFinalTime) {
        this.signInActivityFinalTime = signInActivityFinalTime;
    }

    public String getSignInActivityContent() {
        return signInActivityContent;
    }

    public void setSignInActivityContent(String signInActivityContent) {
        this.signInActivityContent = signInActivityContent == null ? null : signInActivityContent.trim();
    }
}
package com.icis.backend.model;

import java.util.Date;
import java.util.List;

public class IcisDynamic {
    private Long dynamicId;

    private String dynamicCreatorNickname;

    private Date dynamicPublishTime;

    private String dynamicUserHeadPhoto;

    private String dynamicPublishContent;

    private String dynamicPosition;

    private String dynamicPublishPhoto;

    private List<IcisDynamicSupport> icisDynamicSupports;

    private List<IcisDynamicComment> icisDynamicComments;

    public String getDynamicCreatorNickname() {
        return dynamicCreatorNickname;
    }

    public void setDynamicCreatorNickname(String dynamicCreatorNickname) {
        this.dynamicCreatorNickname = dynamicCreatorNickname;
    }

    public Date getDynamicPublishTime() {
        return dynamicPublishTime;
    }

    public void setDynamicPublishTime(Date dynamicPublishTime) {
        this.dynamicPublishTime = dynamicPublishTime;
    }

    public String getDynamicPublishContent() {
        return dynamicPublishContent;
    }

    public String getDynamicPosition() {
        return dynamicPosition;
    }

    public void setDynamicPosition(String dynamicPosition) {
        this.dynamicPosition = dynamicPosition;
    }

    public void setDynamicPublishContent(String dynamicPublishContent) {
        this.dynamicPublishContent = dynamicPublishContent;
    }

    public String getDynamicPublishPhoto() {
        return dynamicPublishPhoto;
    }

    public void setDynamicPublishPhoto(String dynamicPublishPhoto) {
        this.dynamicPublishPhoto = dynamicPublishPhoto;
    }

    public List<IcisDynamicSupport> getIcisDynamicSupports() {
        return icisDynamicSupports;
    }

    public void setIcisDynamicSupports(List<IcisDynamicSupport> icisDynamicSupports) {
        this.icisDynamicSupports = icisDynamicSupports;
    }

    public List<IcisDynamicComment> getIcisDynamicComments() {
        return icisDynamicComments;
    }

    public void setIcisDynamicComments(List<IcisDynamicComment> icisDynamicComments) {
        this.icisDynamicComments = icisDynamicComments;
    }

    public String getDynamicUserHeadPhoto() {
        return dynamicUserHeadPhoto;
    }

    public void setDynamicUserHeadPhoto(String dynamicUserHeadPhoto) {
        this.dynamicUserHeadPhoto = dynamicUserHeadPhoto;
    }

    public Long getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Long dynamicId) {
        this.dynamicId = dynamicId;
    }
}

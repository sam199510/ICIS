package com.icis.backend.model;

import com.icis.backend.entity.IcisResident;
import com.icis.backend.entity.IcisWorker;

import java.util.Date;

public class IcisAppointmentRecord {
    private Long id;

    private String servicePhoto;

    private String serviceContent;

    private Long workerId;

    private String company;

    private Integer isApproved;

    private Long residentId;

    private Integer isCompleted;

    private Integer serviceGrade;

    private String serviceComment;

    private Date createTime;

    private Date finalTime;

    private IcisResident icisResident;

    private IcisWorker icisWorker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServicePhoto() {
        return servicePhoto;
    }

    public void setServicePhoto(String servicePhoto) {
        this.servicePhoto = servicePhoto;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Integer isApproved) {
        this.isApproved = isApproved;
    }

    public Long getResidentId() {
        return residentId;
    }

    public void setResidentId(Long residentId) {
        this.residentId = residentId;
    }

    public Integer getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Integer isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Integer getServiceGrade() {
        return serviceGrade;
    }

    public void setServiceGrade(Integer serviceGrade) {
        this.serviceGrade = serviceGrade;
    }

    public String getServiceComment() {
        return serviceComment;
    }

    public void setServiceComment(String serviceComment) {
        this.serviceComment = serviceComment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(Date finalTime) {
        this.finalTime = finalTime;
    }

    public IcisResident getIcisResident() {
        return icisResident;
    }

    public void setIcisResident(IcisResident icisResident) {
        this.icisResident = icisResident;
    }

    public IcisWorker getIcisWorker() {
        return icisWorker;
    }

    public void setIcisWorker(IcisWorker icisWorker) {
        this.icisWorker = icisWorker;
    }
}

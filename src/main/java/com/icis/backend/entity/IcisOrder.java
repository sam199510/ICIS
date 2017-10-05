package com.icis.backend.entity;

import java.util.Date;

/**
 * 与订单类对应的实体类
 */
public class IcisOrder {
    private Long id;

    private String serialNo;

    private Double price;

    private String content;

    private Date createTime;

    private Integer state;

    private String payStyle;

    private String payUnit;

    private Long payorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPayStyle() {
        return payStyle;
    }

    public void setPayStyle(String payStyle) {
        this.payStyle = payStyle == null ? null : payStyle.trim();
    }

    public String getPayUnit() {
        return payUnit;
    }

    public void setPayUnit(String payUnit) {
        this.payUnit = payUnit == null ? null : payUnit.trim();
    }

    public Long getPayorId() {
        return payorId;
    }

    public void setPayorId(Long payorId) {
        this.payorId = payorId;
    }
}
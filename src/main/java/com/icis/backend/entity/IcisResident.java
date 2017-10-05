package com.icis.backend.entity;

/**
 * 与社区居民用户表对应的实体类
 */
public class IcisResident {
    private Long id;

    private String username;

    private String password;

    private Long tel;

    private String sex;

    private String headPhoto;

    private String nickname;

    private String address;

    private Integer liveAge;

    private String signature;

    private String payPasscode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto == null ? null : headPhoto.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getLiveAge() {
        return liveAge;
    }

    public void setLiveAge(Integer liveAge) {
        this.liveAge = liveAge;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public String getPayPasscode() {
        return payPasscode;
    }

    public void setPayPasscode(String payPasscode) {
        this.payPasscode = payPasscode == null ? null : payPasscode.trim();
    }
}
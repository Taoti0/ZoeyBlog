package com.zoey.blog.entity;

/**
 * @author: Zoey He
 * @data: 2022/4/3  13:55
 * CREATE BY IDEA
 * ZoeyBlog: AdminUser.java
 *
 * 博客用户
 */
public class AdminUser {
    private Integer adminUserId;
    private String adminUserName;
    private String adminPassword;
    private String nickName;
    private Byte locked;

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Byte getLocked() {
        return locked;
    }

    public void setLocked(Byte locked) {
        this.locked = locked;
    }
}

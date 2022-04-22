package com.zoey.blog.service;

import com.zoey.blog.entity.AdminUser;

/**
 * @author: Zoey He
 * @data: 2022/4/3  14:43
 * CREATE BY IDEA
 * ZoeyBlog: AdminUserService.java
 */
public interface AdminUserService {
    AdminUser login(String userName, String password);

    AdminUser getUserDetailById(Integer adminUserId);

    boolean updateNickName(Integer userId, String nickName);

    boolean updatePassword(Integer userId, String originalPassword, String newPassword);
}

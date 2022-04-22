package com.zoey.blog.dao;

import com.zoey.blog.entity.AdminUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author: Zoey He
 * @data: 2022/4/3  14:46
 * CREATE BY IDEA
 * ZoeyBlog: AdminUserMapper.java
 */
public interface AdminUserMapper {
    AdminUser login(@Param("userName") String userName, @Param("password") String password);

    AdminUser selectByPrimaryKey(Integer adminUserId);

    int updateByPrimaryKeySelective(AdminUser adminUser);
}

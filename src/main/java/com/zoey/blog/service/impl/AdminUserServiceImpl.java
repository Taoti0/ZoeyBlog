package com.zoey.blog.service.impl;

import com.zoey.blog.dao.AdminUserMapper;
import com.zoey.blog.entity.AdminUser;
import com.zoey.blog.service.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Zoey He
 * @data: 2022/4/3  14:45
 * CREATE BY IDEA
 * ZoeyBlog: AdminUserServiceImpl.java
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String userName, String password) {
        return adminUserMapper.login(userName, password);
    }

    @Override
    public AdminUser getUserDetailById(Integer adminUserId) {
        return adminUserMapper.selectByPrimaryKey(adminUserId);
    }

    @Override
    public boolean updateNickName(Integer adminUserId, String nickName) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(adminUserId);
        if (adminUser != null) {
            //修改信息
            adminUser.setNickName(nickName);
            if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                //修改成功则返回true
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updatePassword(Integer adminUserId, String originalPassword, String newPassword) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(adminUserId);
        String adminPassword = adminUser.getAdminPassword();
        // 匹配输入的旧密码是否与数据库中的一致
        if (adminUser != null && originalPassword.equals(adminPassword)) {
            //修改信息
            adminUser.setAdminPassword(newPassword);
            if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                //修改成功则返回true
                return true;
            }
        }
        return false;
    }
}

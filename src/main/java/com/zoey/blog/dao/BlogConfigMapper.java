package com.zoey.blog.dao;

import com.zoey.blog.entity.BlogConfig;

import java.util.List;

/**
 * @author: Zoey He
 * @data: 2022/4/7  17:37
 * CREATE BY IDEA
 * ZoeyBlog: BlogConfigMapper.java
 */
public interface BlogConfigMapper {
    List<BlogConfig> selectAll();

    BlogConfig selectByPrimaryKey(String configName);

    int updateByPrimaryKeySelective(BlogConfig record);
}

package com.zoey.blog.service;

import java.util.Map;

/**
 * @author: Zoey He
 * @data: 2022/4/7  17:38
 * CREATE BY IDEA
 * ZoeyBlog: ConfigService.java
 */
public interface ConfigService {
    /**
     * 修改配置项
     *
     * @param configName
     * @param configValue
     * @return
     */
    int updateConfig(String configName, String configValue);

    /**
     * 获取所有的配置项
     *
     * @return
     */
    Map<String,String> getAllConfigs();
}

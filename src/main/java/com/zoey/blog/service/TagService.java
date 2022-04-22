package com.zoey.blog.service;

import com.zoey.blog.entity.BlogTagCount;
import com.zoey.blog.util.PageQueryUtil;
import com.zoey.blog.util.PageResult;

import java.util.List;

/**
 * @author: Zoey He
 * @data: 2022/4/4  12:55
 * CREATE BY IDEA
 * ZoeyBlog: TagService.java
 */
public interface TagService {
    int getTotalTags();

    PageResult getBlogTagPage(PageQueryUtil pageUtil);      // 分页查询

    Boolean addTag(String tagName);     // 新增标签

    Boolean deleteTag(Integer[] ids);   // 删除标签

    List<BlogTagCount> getBlogTagCountForIndex();
}

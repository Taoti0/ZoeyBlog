package com.zoey.blog.dao;

import com.zoey.blog.entity.BlogTag;
import com.zoey.blog.entity.BlogTagCount;
import com.zoey.blog.util.PageQueryUtil;

import java.util.List;

/**
 * @author: Zoey He
 * @data: 2022/4/4  13:19
 * CREATE BY IDEA
 * ZoeyBlog: BlogTagMapper.java
 */
public interface BlogTagMapper {
    int getTotalTags(PageQueryUtil pageUtil);

    List<BlogTag> findTagList(PageQueryUtil pageUtil);

    BlogTag selectByTagName(String tagName);

    int insertSelective(BlogTag blogTag);

    int deleteBatch(Integer[] ids);

    int batchInsertBlogTag(List<BlogTag> tagListForInsert);

    List<BlogTagCount> getTagCount();
}

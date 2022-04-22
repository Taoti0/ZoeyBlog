package com.zoey.blog.dao;

import com.zoey.blog.entity.BlogTagRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Zoey He
 * @data: 2022/4/6  17:33
 * CREATE BY IDEA
 * ZoeyBlog: BlogTagRelationMapper.java
 */
public interface BlogTagRelationMapper {
    int deleteByPrimaryKey(Long relationId);

    int insert(BlogTagRelation record);

    int insertSelective(BlogTagRelation record);

    BlogTagRelation selectByPrimaryKey(Long relationId);

    BlogTagRelation selectByBlogIdAndTagId(@Param("blogId") Long blogId, @Param("tagId") Integer tagId);

    List<Long> selectDistinctTagIds(Integer[] tagIds);

    int updateByPrimaryKeySelective(BlogTagRelation record);

    int updateByPrimaryKey(BlogTagRelation record);

    int batchInsert(@Param("relationList") List<BlogTagRelation> blogTagRelationList);

    int deleteByBlogId(Long blogId);
}

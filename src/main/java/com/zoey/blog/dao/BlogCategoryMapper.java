package com.zoey.blog.dao;

import com.zoey.blog.entity.BlogCategory;
import com.zoey.blog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Zoey He
 * @data: 2022/4/4  13:10
 * CREATE BY IDEA
 * ZoeyBlog: BlogCategoryMapper.java
 */
public interface BlogCategoryMapper {
    int getTotalCategories(PageQueryUtil pageUtil);

    List<BlogCategory> findCategoryList(PageQueryUtil pageUtil);

    BlogCategory selectByCategoryName(String categoryName);

    int insertSelective(BlogCategory blogCategory);

    BlogCategory selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(BlogCategory blogCategory);

    int deleteBatch(Integer[] ids);

    List<BlogCategory> selectByCategoryIds(@Param("categoryIds") List<Integer> categoryIds);
}

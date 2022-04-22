package com.zoey.blog.dao;

import com.zoey.blog.entity.Blog;
import com.zoey.blog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Zoey He
 * @data: 2022/4/4  13:01
 * CREATE BY IDEA
 * ZoeyBlog: BlogMapper.java
 */
public interface BlogMapper {
    int getTotalBlogs(PageQueryUtil pageUtil);

    List<Blog> findBlogList(PageQueryUtil pageUtil);

    int updateBlogCategorys(@Param("categoryName") String categoryName, @Param("categoryId") Integer categoryId, @Param("ids")Integer[] ids);

    Blog selectByPrimaryKey(Long blogId);

    int insertSelective(Blog blog);

    int deleteBatch(Integer[] ids);

    int updateByPrimaryKeySelective(Blog blogForUpdate);

    List<Blog> findBlogListByType(int type, int i);

    List<Blog> getBlogsPageByTagId(PageQueryUtil pageUtil);

    int getTotalBlogsByTagId(PageQueryUtil pageUtil);

    Blog selectBySubUrl(String subUrl);

    int updateByPrimaryKey(Blog blog);
}

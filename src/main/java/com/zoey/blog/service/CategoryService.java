package com.zoey.blog.service;

import com.zoey.blog.entity.BlogCategory;
import com.zoey.blog.util.PageQueryUtil;
import com.zoey.blog.util.PageResult;

import java.util.List;

/**
 * @author: Zoey He
 * @data: 2022/4/4  12:55
 * CREATE BY IDEA
 * ZoeyBlog: CategoryService.java
 */
public interface CategoryService {
    int getTotalCategories();   // 获取数据库中，博客分类的总条数

    PageResult getBlogCategoryPage(PageQueryUtil pageUtil);     // 分页查询数据库中的博客分类表

    Boolean addCategory(String categoryName, String categoryIcon);  // 新增分类

    Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon);   // 修改分类

    Boolean deleteCategory(Integer[] ids);      // 删除分类

    List<BlogCategory> getAllCategories();
}

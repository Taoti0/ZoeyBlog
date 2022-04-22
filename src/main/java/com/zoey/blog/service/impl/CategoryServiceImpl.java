package com.zoey.blog.service.impl;

import com.zoey.blog.dao.BlogCategoryMapper;
import com.zoey.blog.dao.BlogMapper;
import com.zoey.blog.entity.BlogCategory;
import com.zoey.blog.service.CategoryService;
import com.zoey.blog.util.PageQueryUtil;
import com.zoey.blog.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Zoey He
 * @data: 2022/4/4  13:12
 * CREATE BY IDEA
 * ZoeyBlog: CategoryServiceImpl.java
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    BlogCategoryMapper blogCategoryMapper;
    @Autowired
    BlogMapper blogMapper;

    @Override
    public int getTotalCategories() {
        return blogCategoryMapper.getTotalCategories(null);
    }

    @Override
    public PageResult getBlogCategoryPage(PageQueryUtil pageUtil) {
        List<BlogCategory> categoryList = blogCategoryMapper.findCategoryList(pageUtil);
        int total = blogCategoryMapper.getTotalCategories(pageUtil);
        PageResult pageResult = new PageResult(categoryList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    @Transactional      // 添加事务
    public Boolean addCategory(String categoryName, String categoryIcon) {
        // 查询是否存在此分类
        BlogCategory temp = blogCategoryMapper.selectByCategoryName(categoryName);
        if (temp == null) {
            BlogCategory blogCategory = new BlogCategory();
            blogCategory.setCategoryName(categoryName);
            blogCategory.setCategoryIcon(categoryIcon);
            return blogCategoryMapper.insertSelective(blogCategory) > 0;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon) {
        BlogCategory blogCategory = blogCategoryMapper.selectByPrimaryKey(categoryId);
        if (blogCategory != null) {
            blogCategory.setCategoryIcon(categoryIcon);
            blogCategory.setCategoryName(categoryName);
            // 查找到所有博客分类属于修改前的博客文章，将这些文章的分类改为修改后的
            blogMapper.updateBlogCategorys(categoryName, blogCategory.getCategoryId(), new Integer[]{categoryId});
            return blogCategoryMapper.updateByPrimaryKeySelective(blogCategory) > 0;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean deleteCategory(Integer[] ids) {
        if (ids.length < 1) return false;
        // 修改受影响的博客文章，将其博客分类为预删除的文章的分类修改为默认
        blogMapper.updateBlogCategorys("默认分类", 0, ids);
        //删除分类数据
        return blogCategoryMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<BlogCategory> getAllCategories() {
        return blogCategoryMapper.findCategoryList(null);
    }
}

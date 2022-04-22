package com.zoey.blog.service;

import com.zoey.blog.vo.BlogDetailVO;
import com.zoey.blog.vo.SimpleBlogListVO;
import com.zoey.blog.entity.Blog;
import com.zoey.blog.util.PageQueryUtil;
import com.zoey.blog.util.PageResult;

import java.util.List;

/**
 * @author: Zoey He
 * @data: 2022/4/4  12:54
 * CREATE BY IDEA
 * ZoeyBlog: BlogService.java
 */
public interface BlogService {
    int getTotalBlogs();

    PageResult getBlogPage(PageQueryUtil pageUtil);

    Blog getBlogById(Long blogId);

    String saveBlog(Blog blog);

    Boolean deleteBatch(Integer[] ids);

    String update(Blog blog);

    PageResult getBlogsForIndexPage(int page);

    List<SimpleBlogListVO> getBlogListForIndexPage(int type);
    BlogDetailVO getBlogDetail(Long blogId);

    PageResult getBlogsPageByTag(String tagName, int page);

    PageResult getBlogsPageByCategory(String categoryId, int page);

    PageResult getBlogsPageBySearch(String keyword, int page);

    BlogDetailVO getBlogDetailBySubUrl(String subUrl);
}

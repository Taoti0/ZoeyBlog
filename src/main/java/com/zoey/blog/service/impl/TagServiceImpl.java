package com.zoey.blog.service.impl;

import com.zoey.blog.dao.BlogMapper;
import com.zoey.blog.dao.BlogTagMapper;
import com.zoey.blog.dao.BlogTagRelationMapper;
import com.zoey.blog.entity.BlogCategory;
import com.zoey.blog.entity.BlogTag;
import com.zoey.blog.entity.BlogTagCount;
import com.zoey.blog.service.TagService;
import com.zoey.blog.util.PageQueryUtil;
import com.zoey.blog.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Zoey He
 * @data: 2022/4/4  13:18
 * CREATE BY IDEA
 * ZoeyBlog: TagServiceImpl.java
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    BlogTagMapper blogTagMapper;
    @Autowired
    private BlogTagRelationMapper relationMapper;

    @Override
    public int getTotalTags() {
        return blogTagMapper.getTotalTags(null);
    }

    @Override
    public PageResult getBlogTagPage(PageQueryUtil pageUtil) {
        List<BlogTag> tagList = blogTagMapper.findTagList(pageUtil);
        int total = blogTagMapper.getTotalTags(pageUtil);
        PageResult pageResult = new PageResult(tagList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    @Transactional
    public Boolean addTag(String tagName) {
        BlogTag temp = blogTagMapper.selectByTagName(tagName);
        if (temp == null) {
            BlogTag blogTag = new BlogTag();
            blogTag.setTagName(tagName);
            return blogTagMapper.insertSelective(blogTag) > 0;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean deleteTag(Integer[] ids) {
        if (ids.length < 1) return false;
        //已存在与博客文章的关联关系不删除
        List<Long> relations = relationMapper.selectDistinctTagIds(ids);
        if (!CollectionUtils.isEmpty(relations)) {
            return false;
        }
        //删除tag
        return blogTagMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<BlogTagCount> getBlogTagCountForIndex() {
        return blogTagMapper.getTagCount();
    }
}

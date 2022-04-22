package com.zoey.blog.service.impl;

import com.zoey.blog.dao.BlogCommentMapper;
import com.zoey.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Zoey He
 * @data: 2022/4/4  13:13
 * CREATE BY IDEA
 * ZoeyBlog: CommentServiceImpl.java
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    BlogCommentMapper commentMapper;
    @Override
    public int getTotalComments() {
        return commentMapper.getTotalBlogComments(null);
    }
}

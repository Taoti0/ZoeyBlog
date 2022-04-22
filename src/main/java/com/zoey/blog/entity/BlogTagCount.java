package com.zoey.blog.entity;

/**
 * @author: Zoey He
 * @data: 2022/4/7  15:39
 * CREATE BY IDEA
 * ZoeyBlog: BlogTagCount.java
 */
public class BlogTagCount {
    private Integer tagId;

    private String tagName;

    private Integer tagCount;


    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getTagCount() {
        return tagCount;
    }

    public void setTagCount(Integer tagCount) {
        this.tagCount = tagCount;
    }
}

package com.hzy.pojo;

/**
 * 收藏表，主要记录用户对文章的收藏
 */
public class Collect {
    private Integer collectId;
    private Integer userId;
    private Integer blogId;

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "collectId=" + collectId +
                ", userId=" + userId +
                ", blogId=" + blogId +
                '}';
    }
}

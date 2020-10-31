package com.hzy.pojo;

import java.util.Date;

public class LikeRecord {
    private Integer likeRecordId;
    private Integer userId;
    private Integer blogId;
    private Date createDate;
    private Integer state;

    public LikeRecord() {
    }

    public LikeRecord(Integer userId, Integer blogId) {
        this.userId = userId;
        this.blogId = blogId;
        this.createDate = new Date();
        this.state = 1;
    }

    public Integer getLikeRecordId() {
        return likeRecordId;
    }

    public void setLikeRecordId(Integer likeRecordId) {
        this.likeRecordId = likeRecordId;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "LikeRecord{" +
                "likeRecordId=" + likeRecordId +
                ", userId=" + userId +
                ", blogId=" + blogId +
                ", createDate=" + createDate +
                ", state=" + state +
                '}';
    }
}

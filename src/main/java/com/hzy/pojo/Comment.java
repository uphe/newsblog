package com.hzy.pojo;

import java.util.Date;

/**
 * 评论表，主要负责博客的评论，parentId主要是指向被回复的人的id
 * 如果不是回复，那就指向0或null
 */
public class Comment {
    private int commentId;
    private String content;
    private Date createDate;
    private int blogId;
    private int userId;
    private int status;
    private int parentId;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", blogId=" + blogId +
                ", userId=" + userId +
                ", status=" + status +
                ", parentId=" + parentId +
                '}';
    }
}

package com.hzy.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;
import java.util.Date;

public class CommentVO {
    private int commentId;
    private String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;
    private int blogId;
    private int status;
    private int parentId;

    private int userId;
    private String username;
    private String headUrl;
    private Integer userType;
    // 子评论
    private List<CommentVO> commentVOS;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public List<CommentVO> getCommentVOS() {
        return commentVOS;
    }

    public void setCommentVOS(List<CommentVO> commentVOS) {
        this.commentVOS = commentVOS;
    }

    @Override
    public String toString() {
        return "CommentVO{" +
                "commentId=" + commentId +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", blogId=" + blogId +
                ", status=" + status +
                ", parentId=" + parentId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", userType=" + userType +
                ", commentVOS=" + commentVOS +
                '}';
    }
}
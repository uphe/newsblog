package com.hzy.pojo;

import java.util.Date;

public class Blog {
    private int blogId;
    private String title;
    private String article;
    private String summary;
    private int likeCount;
    private int hitCount;
    private int commentCount;
    private Date createDate;
    private int userId;
    private String typeString;
    private String labelString;

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTypeString() {
        return typeString;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }

    public String getLabelString() {
        return labelString;
    }

    public void setLabelString(String labelString) {
        this.labelString = labelString;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", title='" + title + '\'' +
                ", article='" + article + '\'' +
                ", summary='" + summary + '\'' +
                ", likeCount=" + likeCount +
                ", hitCount=" + hitCount +
                ", commentCount=" + commentCount +
                ", createDate=" + createDate +
                ", userId=" + userId +
                ", typeString='" + typeString + '\'' +
                ", labelString='" + labelString + '\'' +
                '}';
    }
}

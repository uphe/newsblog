package com.hzy.vo;

import java.util.List;

public class BlogVO {
    private int blogId;
    private String title;
    private String article;
    private String summary;
    private int likeCount;
    private int hitCount;
    private int commentCount;
    private String createDate;
    private List<String> types;
    private List<String> labels;

    private int userId;
    private String username;
    private String headUrl;

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
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

    @Override
    public String toString() {
        return "BlogVO{" +
                "blogId=" + blogId +
                ", title='" + title + '\'' +
                ", article='" + article + '\'' +
                ", summary='" + summary + '\'' +
                ", likeCount=" + likeCount +
                ", hitCount=" + hitCount +
                ", commentCount=" + commentCount +
                ", createDate='" + createDate + '\'' +
                ", types=" + types +
                ", labels=" + labels +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", headUrl='" + headUrl + '\'' +
                '}';
    }
}

package com.hzy.pojo;

/**
 * 这是博客表，主要存储博客的一些信息，这里又博客的id，标题，文章，摘要
 * 点赞数，点击量，评论数，创建时间，创建者id
 */
public class Blog {
    private int blogId;
    private String title;
    private String article;
    private String summary;
    private int likeCount;
    private int hitCount;
    private int commentCount;
    private String createDate;
    private int userId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
                '}';
    }
}

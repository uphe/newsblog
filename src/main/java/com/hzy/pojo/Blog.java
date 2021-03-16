package com.hzy.pojo;

import lombok.Data;

/**
 * 这是博客表，主要存储博客的一些信息，这里又博客的id，标题，文章，摘要
 * 点赞数，点击量，评论数，创建时间，创建者id
 *
 * @Author: hzy
 * @Date: 2021/3/11
 */
@Data
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
}

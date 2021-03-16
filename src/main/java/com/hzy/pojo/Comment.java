package com.hzy.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 评论表，主要负责博客的评论，parentId主要是指向被回复的人的id
 * 如果不是回复，那就指向0或null
 *
 * @Author: hzy
 * @Date: 2020/6/12
 */
@Data
public class Comment {
    private int commentId;
    private String content;
    private Date createDate;
    private int blogId;
    private int userId;
    private int parentId;
}

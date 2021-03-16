package com.hzy.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: hzy
 * @Date: 2020/11/28
 */
@Data
public class CommentVO {
    private Integer commentId;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    private Integer blogId;
    private Integer parentId;

    private Integer userId;
    private String username;
    private String headUrl;
    private Integer userType;

    private List<CommentVO> commentVOS;
}
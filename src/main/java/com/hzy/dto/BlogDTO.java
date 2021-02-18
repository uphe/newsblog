package com.hzy.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: hzy
 * @Date: 2020/12/12
 */
@Data
public class BlogDTO {
    private Integer blogId;
    private String title;
    private String summary;
    private String article;
    private List<String> labels;
    private List<String> types;
}

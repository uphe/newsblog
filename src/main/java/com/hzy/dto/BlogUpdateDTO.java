package com.hzy.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: hzy
 * @Date: 2021/3/11
 */
@Data
public class BlogUpdateDTO {
    @NotNull
    private Integer blogId;
    @NotBlank
    private String title;
    private String summary;
    @NotBlank
    private String article;
    private List<String> labels;
    private List<String> types;
}

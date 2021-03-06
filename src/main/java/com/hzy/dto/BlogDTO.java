package com.hzy.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author: hzy
 * @Date: 2020/12/12
 */
@Data
public class BlogDTO {
    @NotBlank
    private String title;
    private String summary;
    @NotBlank
    private String article;
    private List<String> labels;
    private List<String> types;
}

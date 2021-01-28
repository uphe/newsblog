package com.hzy.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: hzy
 * @Date: 2021/1/28
 */
@Data
public class SearchDTO extends PageDTO{
    @NotBlank
    private String title;
}

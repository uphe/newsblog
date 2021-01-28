package com.hzy.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: hzy
 * @Date: 2021/1/28
 */
@Data
public class PageDTO {
    @NotNull
    private Integer page;
    @NotNull
    private Integer limit;
}

package com.hzy.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: hzy
 * @Date: 2021/1/2
 */
@Data
public class TypeDTO {
    @NotBlank
    private String typeName;
    @NotNull
    private Integer page;
}
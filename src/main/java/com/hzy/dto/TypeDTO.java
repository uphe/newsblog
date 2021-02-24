package com.hzy.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: hzy
 * @Date: 2021/1/2
 */
@Data
public class TypeDTO extends PageDTO {
    @NotBlank
    private String typeName;
}

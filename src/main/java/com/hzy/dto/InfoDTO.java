package com.hzy.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: hzy
 * @Date: 2021/2/18
 */
@Data
public class InfoDTO extends PageDTO {
    @NotNull
    private Integer id;
    private String sortName;
}

package com.hzy.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: hzy
 * @Date: 2021/3/12
 */
@Data
public class CollectDTO {
    @NotNull
    private Integer userId;
    @NotNull
    private Integer blogId;
}

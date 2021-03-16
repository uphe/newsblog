package com.hzy.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: hzy
 * @Date: 2021/3/16
 */
@Data
public class CommentDTO extends PageDTO{
    @NotNull
    private Integer blogId;
}

package com.hzy.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: hzy
 * @Date: 2021/2/25
 */
@Data
public class FollowDTO {
    @NotNull
    private Integer userId;
    @NotNull
    private Integer followUserId;
}

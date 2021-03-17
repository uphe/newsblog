package com.hzy.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: hzy
 * @Date: 2021/3/16
 */
@Data
public class UserUpdateDTO {
    @NotNull
    private Integer userId;

    private String username;
    private String oldPassword;
    private String newPassword;
    private String headUrl;
}

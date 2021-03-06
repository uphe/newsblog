package com.hzy.pojo;

import lombok.Data;

/**
 * @Author: hzy
 * @Date: 2021/3/11
 */
@Data
public class User {
    private Integer userId;
    private String username;
    private String password;
    private String salt;
    private String headUrl;
    private Integer userType;
}

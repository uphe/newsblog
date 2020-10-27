package com.hzy.vo;

import com.hzy.pojo.User;

public class UserVO{
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "user=" + user +
                '}';
    }
}

package com.hzy.pojo;

import lombok.Getter;

import java.util.Date;

public class Token {
    private int tokenId;
    private String token;
    private Date expired;
    private int userId;

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenId=" + tokenId +
                ", token='" + token + '\'' +
                ", expired=" + expired +
                ", userId=" + userId +
                '}';
    }
}

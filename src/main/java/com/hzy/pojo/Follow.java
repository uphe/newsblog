package com.hzy.pojo;

public class Follow {
    private Integer followId;
    private Integer userId;
    private Integer followUserId;

    public Integer getFollowId() {
        return followId;
    }

    public void setFollowId(Integer followId) {
        this.followId = followId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFollowUserId() {
        return followUserId;
    }

    public void setFollowUserId(Integer followUserId) {
        this.followUserId = followUserId;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "followId=" + followId +
                ", userId=" + userId +
                ", followUserId=" + followUserId +
                '}';
    }
}

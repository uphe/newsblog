package com.hzy.pojo;

import java.util.Date;

public class ReadNotice {
    private int readNoticeId;
    private int noticeId;
    private int userId;
    private Date expirationTime;

    public int getReadNoticeId() {
        return readNoticeId;
    }

    public void setReadNoticeId(int readNoticeId) {
        this.readNoticeId = readNoticeId;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    @Override
    public String toString() {
        return "ReadNotice{" +
                "readNoticeId=" + readNoticeId +
                ", noticeId=" + noticeId +
                ", userId=" + userId +
                ", expirationTime=" + expirationTime +
                '}';
    }
}

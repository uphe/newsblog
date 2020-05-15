package com.hzy.pojo;

import java.util.Date;

/**
 * 消息提醒实体类
 */
public class Remind {
    private int remindId;
    private String remindContent;
    private int fromId;
    private int toId;
    private int blogId;
    private Date createDate;
    private int state;

    public int getRemindId() {
        return remindId;
    }

    public void setRemindId(int remindId) {
        this.remindId = remindId;
    }

    public String getRemindContent() {
        return remindContent;
    }

    public void setRemindContent(String remindContent) {
        this.remindContent = remindContent;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Remind{" +
                "remindId=" + remindId +
                ", remindContent='" + remindContent + '\'' +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", blogId=" + blogId +
                ", createDate=" + createDate +
                ", state=" + state +
                '}';
    }
}

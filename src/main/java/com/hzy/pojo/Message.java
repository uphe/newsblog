package com.hzy.pojo;

import java.util.Date;

public class Message {
    private int messageId;
    private int messageFromId;
    private int messageToId;
    private String messageContent;
    private Date messageCreateDate;
    private int messageHasRead;
    private String messageConversationId;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getMessageFromId() {
        return messageFromId;
    }

    public void setMessageFromId(int messageFromId) {
        this.messageFromId = messageFromId;
    }

    public int getMessageToId() {
        return messageToId;
    }

    public void setMessageToId(int messageToId) {
        this.messageToId = messageToId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Date getMessageCreateDate() {
        return messageCreateDate;
    }

    public void setMessageCreateDate(Date messageCreateDate) {
        this.messageCreateDate = messageCreateDate;
    }

    public int getMessageHasRead() {
        return messageHasRead;
    }

    public void setMessageHasRead(int messageHasRead) {
        this.messageHasRead = messageHasRead;
    }

    public String getMessageConversationId() {
        return messageConversationId;
    }

    public void setMessageConversationId(String messageConversationId) {
        this.messageConversationId = messageConversationId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", messageFromId=" + messageFromId +
                ", messageToId=" + messageToId +
                ", messageContent='" + messageContent + '\'' +
                ", messageCreateDate=" + messageCreateDate +
                ", messageHasRead=" + messageHasRead +
                ", messageConversationId='" + messageConversationId + '\'' +
                '}';
    }
}

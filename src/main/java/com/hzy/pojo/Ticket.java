package com.hzy.pojo;

import java.util.Date;

public class Ticket {
    private int ticketId;
    private String randomTicket;
    private Date expired;
    private int userId;

    public Ticket() {
    }

    public Ticket(String randomTicket, Date expired) {
        this.randomTicket = randomTicket;
        this.expired = expired;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getRandomTicket() {
        return randomTicket;
    }

    public void setRandomTicket(String randomTicket) {
        this.randomTicket = randomTicket;
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
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", randomTicket='" + randomTicket + '\'' +
                ", expired=" + expired +
                ", userId=" + userId +
                '}';
    }
}

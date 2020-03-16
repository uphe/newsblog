package com.hzy.service;

import com.hzy.mapper.TicketMapper;
import com.hzy.pojo.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TicketService {
    @Autowired
    private TicketMapper ticketMapper;

    public int addTicket(Ticket ticket) {
        return ticketMapper.addTicket(ticket);
    }
    public int updateTicket(String randomTicket, Date expired) {

        return 0;
    }
    public Ticket selectTicketByRandomTicket(String randomTicket) {
        return ticketMapper.selectTicketByRandomTicket(randomTicket);
    }
}

package com.hzy.mapper;

import com.hzy.pojo.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Mapper
@Repository
public interface TicketMapper {
    int addTicket(Ticket ticket);
    int updateTicket(String randomTicket,Date expired);
    Ticket selectTicketByRandomTicket(String randomTicket);
}

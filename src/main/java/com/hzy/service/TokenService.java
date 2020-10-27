package com.hzy.service;

import com.hzy.mapper.TokenMapper;
import com.hzy.pojo.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Autowired
    private TokenMapper tokenMapper;

    public int addTicket(Token token) {
        return tokenMapper.addToken(token);
    }

    public int updateToken(String randomTicket, Date expired) {
        return tokenMapper.updateToken(randomTicket, expired);
    }

    public Token selectTicketByRandomTicket(String randomTicket) {
        return tokenMapper.selectTokenByToken(randomTicket);
    }
}
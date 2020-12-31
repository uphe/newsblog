package com.hzy.service.impl;


import com.hzy.mapper.TokenMapper;
import com.hzy.pojo.Token;
import com.hzy.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenMapper tokenMapper;

    public int addToken(Token token) {
        return tokenMapper.addToken(token);
    }

    public int updateToken(String randomTicket, Date expired) {
        return tokenMapper.updateToken(randomTicket, expired);
    }

    public Token selectTokenByToken(String token) {
        return tokenMapper.selectTokenByToken(token);
    }
}

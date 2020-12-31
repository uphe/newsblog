package com.hzy.service;

import com.hzy.pojo.Token;

import java.util.Date;

public interface TokenService {


    int addToken(Token token);

    int updateToken(String randomTicket, Date expired);

    Token selectTokenByToken(String token);
}

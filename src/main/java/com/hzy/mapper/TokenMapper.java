package com.hzy.mapper;

import com.hzy.pojo.Token;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Mapper
@Repository
public interface TokenMapper {
    int addToken(Token token);
    int updateToken(String token, Date expired);
    Token selectTokenByToken(String token);
    Token selectTokenByUserId(int userId);
}

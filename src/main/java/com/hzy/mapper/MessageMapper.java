package com.hzy.mapper;

import com.hzy.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MessageMapper {
    int addMessage(Message message);
}

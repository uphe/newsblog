package com.hzy.mapper;

import com.hzy.pojo.ReadNotice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReadNoticeMapper {
    int addReadNotice(ReadNotice readNotice);
}

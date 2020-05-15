package com.hzy.mapper;

import com.hzy.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface NoticeMapper {
    int addNotice(Notice notice);
    List<Notice> selectNoticeByUserId(int userId);
    Notice selectNoticeByNoticeId(int noticeId);
}

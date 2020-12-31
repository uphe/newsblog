package com.hzy.service;

import com.hzy.pojo.Notice;

import java.util.List;

public interface NoticeService {
    int addNotice(Notice notice);

    int selectNoticeCountByUserId(int userId);

    List<Notice> selectNoticeByUserId(int userId);

    Notice selectNoticeByNoticeId(int noticeId);

}

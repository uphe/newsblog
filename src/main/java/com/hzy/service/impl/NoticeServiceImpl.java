package com.hzy.service.impl;

import com.hzy.mapper.NoticeMapper;
import com.hzy.pojo.Notice;
import com.hzy.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    public int addNotice(Notice notice) {
        return noticeMapper.addNotice(notice);
    }
    public int selectNoticeCountByUserId(int userId) {
        return noticeMapper.selectNoticeCountByUserId(userId);
    }
    public List<Notice> selectNoticeByUserId(int userId) {
        return noticeMapper.selectNoticeByUserId(userId);
    }
    public Notice selectNoticeByNoticeId(int noticeId) {
        return noticeMapper.selectNoticeByNoticeId(noticeId);
    }

}

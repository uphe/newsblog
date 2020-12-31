package com.hzy.service.impl;

import com.hzy.mapper.ReadNoticeMapper;
import com.hzy.pojo.ReadNotice;
import com.hzy.pojo.Remind;
import com.hzy.service.ReadNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ReadNoticeServiceImpl implements ReadNoticeService {
    @Autowired
    private ReadNoticeMapper readNoticeMapper;
    public int addReadNotice(ReadNotice readNotice) {
        return readNoticeMapper.addReadNotice(readNotice);
    }
}

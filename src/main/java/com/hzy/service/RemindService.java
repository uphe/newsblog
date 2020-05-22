package com.hzy.service;

import com.hzy.mapper.RemindMapper;
import com.hzy.pojo.Remind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemindService {
    @Autowired
    private RemindMapper remindMapper;

    public int addRemind(Remind remind) {
        return remindMapper.addRemind(remind);
    }
    public List<Remind> selectRemindByToId(int toId) {
        return remindMapper.selectRemindByToId(toId);
    }
    public int updateRemindByRemindId(int remindId) {
        return remindMapper.updateRemindByRemindId(remindId);
    }
    public int updateRemindByFromIdAndBlogIdAndRemindType(int fromId, int blogId, int remindType) {
        return remindMapper.updateRemindByFromIdAndBlogIdAndRemindType(fromId, blogId, remindType);
    }
    /**
     * 全部已读
     * @param toId
     * @return
     */
    public int updateRemindByToId(int toId) {
        return remindMapper.updateRemindByToId(toId);
    }
}

package com.hzy.service.impl;


import com.hzy.mapper.RemindMapper;
import com.hzy.pojo.Remind;
import com.hzy.service.RemindService;
import com.hzy.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RemindServiceImpl implements RemindService {
    @Autowired
    private RemindMapper remindMapper;

    public int addRemind(Remind remind) {
        return remindMapper.addRemind(remind);
    }
    /**
     * 查询用户所有未读的点赞通知
     * @param toId
     * @return
     */
    public ResponseVO<List<Remind>> selectLikeRemindByToId(int toId) {
        List<Remind> remindList = remindMapper.selectLikeRemindByToId(toId);
        ResponseVO<List<Remind>> responseVO = new ResponseVO<>(0,"提醒成功");
        responseVO.setData(remindList);
        return responseVO;
    }
    /**
     * 查询用户所有未读的评论通知
     * @param toId
     * @return
     */
    public ResponseVO<List<Remind>> selectCommentRemindByToId(int toId) {
        List<Remind> remindList = remindMapper.selectCommentRemindByToId(toId);
        ResponseVO<List<Remind>> responseVO = new ResponseVO<>(0, "提醒成功");
        responseVO.setData(remindList);
        return responseVO;
    }

    /**
     * 查询用户所有未读的评论通知数量
     * @param toId
     * @return
     */
    public ResponseVO<Integer> selectCommentRemindCountByToId(int toId) {
        int count = remindMapper.selectCommentRemindCountByToId(toId);
        ResponseVO<Integer> responseVO = new ResponseVO<>(0, "提醒成功");
        responseVO.setData(count);
        return responseVO;
    }
    /**
     * 查询用户所有未读的点赞通知数量
     * @param toId
     * @return
     */
    public ResponseVO<Integer> selectLikeRemindCountByToId(int toId) {
        int count = remindMapper.selectLikeRemindCountByToId(toId);
        ResponseVO<Integer> responseVO = new ResponseVO<>(0, "查询未读点赞数量成功");
        responseVO.setData(count);
        return responseVO;
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
    public ResponseVO updateRemindByToId(int toId) {
        remindMapper.updateRemindByToId(toId);
        return new ResponseVO(0,"全部已读");
    }
    /**
     * 点赞通知全部已读
     * @param toId
     * @return
     */
    public ResponseVO updateLikeRemindByToId(int toId) {
        remindMapper.updateLikeRemindByToId(toId);
        return new ResponseVO(0,"点赞通知全部已读");
    }
    /**
     * 评论通知全部已读
     * @param toId
     * @return
     */
    public ResponseVO updateCommentRemindByToId(int toId) {
        remindMapper.updateCommentRemindByToId(toId);
        return new ResponseVO(0,"评论通知全部已读");
    }
}

package com.hzy.service;

import com.hzy.mapper.RemindMapper;
import com.hzy.pojo.Remind;
import com.hzy.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RemindService {
    @Autowired
    private RemindMapper remindMapper;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public int addRemind(Remind remind) {
        return remindMapper.addRemind(remind);
    }
    /**
     * 查询用户所有未读的点赞通知
     * @param toId
     * @return
     */
    public ResponseVO<List<Remind>> selectLikeRemindByToId(int toId) {
        logger.info("执行了点赞通知查询（查询用户所有未读的点赞通知）");
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
        logger.info("执行了评论通知查询（查询用户所有未读的评论通知）");
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
        logger.info("执行了评论通知查询（查询用户所有未读的评论通知数量）");
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
        logger.info("执行了用户所有未读的点赞通知数量");
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
        logger.info("执行全部已读");
        remindMapper.updateRemindByToId(toId);
        return new ResponseVO(0,"全部已读");
    }
    /**
     * 点赞通知全部已读
     * @param toId
     * @return
     */
    public ResponseVO updateLikeRemindByToId(int toId) {
        logger.info("执行点赞通知全部已读");
        remindMapper.updateLikeRemindByToId(toId);
        return new ResponseVO(0,"点赞通知全部已读");
    }
    /**
     * 评论通知全部已读
     * @param toId
     * @return
     */
    public ResponseVO updateCommentRemindByToId(int toId) {
        logger.info("执行评论通知全部已读");
        remindMapper.updateCommentRemindByToId(toId);
        return new ResponseVO(0,"评论通知全部已读");
    }
}

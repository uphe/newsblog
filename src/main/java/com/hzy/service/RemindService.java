package com.hzy.service;

import com.hzy.pojo.Remind;
import com.hzy.vo.ResponseVO;

import java.util.List;

public interface RemindService {
    int addRemind(Remind remind);

    /**
     * 查询用户所有未读的点赞通知
     *
     * @param toId
     * @return
     */
    ResponseVO<List<Remind>> selectLikeRemindByToId(int toId);

    /**
     * 查询用户所有未读的评论通知
     *
     * @param toId
     * @return
     */
    ResponseVO<List<Remind>> selectCommentRemindByToId(int toId);

    /**
     * 查询用户所有未读的评论通知数量
     *
     * @param toId
     * @return
     */
    ResponseVO<Integer> selectCommentRemindCountByToId(int toId);

    /**
     * 查询用户所有未读的点赞通知数量
     *
     * @param toId
     * @return
     */
    ResponseVO<Integer> selectLikeRemindCountByToId(int toId);

    int updateRemindByRemindId(int remindId);

    int updateRemindByFromIdAndBlogIdAndRemindType(int fromId, int blogId, int remindType);

    /**
     * 全部已读
     *
     * @param toId
     * @return
     */
    ResponseVO updateRemindByToId(int toId);

    /**
     * 点赞通知全部已读
     *
     * @param toId
     * @return
     */
    ResponseVO updateLikeRemindByToId(int toId);

    /**
     * 评论通知全部已读
     *
     * @param toId
     * @return
     */
    ResponseVO updateCommentRemindByToId(int toId);
}

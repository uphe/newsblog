package com.hzy.service;

import com.hzy.vo.ResponseVO;

public interface LikeService {

    /**
     * 通过redis实现点赞和取消点赞功能，每篇文章，都有一个key（用到时创建），用来存放点赞的用户
     * 当用户点赞时，会把该用户放到该key中，即记录每个用户只能点赞一次，同时可以用于取消点赞功能
     * 还有一个集合是changeKey，用来存放改变的文章，用于定时任务持久化点赞数量（定时将Redis中点赞总数持久化到MySQL）
     *
     * @param userId
     * @param blogId
     * @return
     */
    ResponseVO like(int userId, int blogId);
}

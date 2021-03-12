package com.hzy.service;

import com.hzy.pojo.Follow;
import com.hzy.pojo.User;
import com.hzy.vo.BaseResult;

import java.util.List;

public interface FollowService {

    /**
     * 通过用户id查询他所关注的用户
     *
     * @param userId
     * @return
     */
    BaseResult selectFollowUserByUserId(int userId);

    /**
     * 通过用户id查询粉丝
     *
     * @param userId
     * @return
     */
    BaseResult selectFansByUserId(int userId);

    Follow selectFollowUserByUserIdAndFollowUserId(int userId, int followUserId);

    BaseResult followUser(int userId, int followUserId);

    int deleteFollowUserByFollowId(int followId);

    int selectFollowCountByUserId(int userId);

    int selectFansCountByUserId(int userId);
}

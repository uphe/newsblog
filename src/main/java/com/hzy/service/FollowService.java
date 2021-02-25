package com.hzy.service;

import com.hzy.pojo.Follow;
import com.hzy.pojo.User;
import com.hzy.vo.BaseResult;

import java.util.List;

public interface FollowService {

    List<User> selectFollowUserByUserId(int userId);

    List<User> selectUserByFollowUserId(int followUserId);

    Follow selectFollowUserByUserIdAndFollowUserId(int userId, int followUserId);

    BaseResult followUser(int userId, int followUserId);

    int deleteFollowUserByFollowId(int followId);

    int selectFollowCountByUserId(int userId);

    int selectFansCountByUserId(int userId);
}

package com.hzy.service;

import com.hzy.pojo.User;

import java.util.List;

public interface FollowService {

    List<User> selectFollowUserByUserId(int userId);

    List<User> selectUserByFollowUserId(int followUserId);

    int addFollowUser(int userId, int followUserId);

    int deleteFollowUserByFollowId(int followId);

    int selectFollowCountByUserId(int userId);

    int selectFansCountByUserId(int userId);
}

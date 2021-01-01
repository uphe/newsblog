package com.hzy.service.impl;


import com.hzy.mapper.FollowMapper;
import com.hzy.pojo.User;
import com.hzy.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowMapper followMapper;

    public List<User> selectFollowUserByUserId(int userId) {
        return followMapper.selectFollowUserByUserId(userId);
    }

    public List<User> selectUserByFollowUserId(int followUserId) {
        return followMapper.selectUserByFollowUserId(followUserId);
    }

    public int addFollowUser(int userId, int followUserId) {
        return followMapper.addFollowUser(userId, followUserId);
    }

    public int deleteFollowUserByFollowId(int followId) {
        return followMapper.deleteFollowUserByFollowId(followId);
    }

    public int selectFollowCountByUserId(int userId) {
        return followMapper.selectFollowCountByUserId(userId);
    }

    public int selectFansCountByUserId(int userId) {
        return followMapper.selectFansCountByUserId(userId);
    }
}
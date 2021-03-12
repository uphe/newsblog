package com.hzy.service.impl;


import com.hzy.mapper.FollowMapper;
import com.hzy.pojo.Follow;
import com.hzy.pojo.User;
import com.hzy.service.FollowService;
import com.hzy.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowMapper followMapper;

    @Override
    public BaseResult selectFollowUserByUserId(int userId) {
        return BaseResult.ok(followMapper.selectFollowUserByUserId(userId));
    }

    @Override
    public BaseResult selectFansByUserId(int userId) {
        return BaseResult.ok(followMapper.selectFansByUserId(userId));
    }

    @Override
    public BaseResult followUser(int userId, int followUserId) {
        Follow follow = followMapper.selectFollowUserByUserIdAndFollowUserId(userId, followUserId);
        if (follow == null) {
            // 添加关注
            return BaseResult.ok(followMapper.addFollowUser(userId, followUserId));
        } else {
            // 取消关注
            return BaseResult.ok(followMapper.deleteFollowUserByFollowId(follow.getFollowId()));
        }
    }

    @Override
    public int deleteFollowUserByFollowId(int followId) {
        return followMapper.deleteFollowUserByFollowId(followId);
    }

    @Override
    public int selectFollowCountByUserId(int userId) {
        return followMapper.selectFollowCountByUserId(userId);
    }

    @Override
    public int selectFansCountByUserId(int userId) {
        return followMapper.selectFansCountByUserId(userId);
    }

    @Override
    public Follow selectFollowUserByUserIdAndFollowUserId(int userId, int followUserId) {
        return followMapper.selectFollowUserByUserIdAndFollowUserId(userId, followUserId);
    }
}

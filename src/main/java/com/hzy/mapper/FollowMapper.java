package com.hzy.mapper;

import com.hzy.pojo.Follow;
import com.hzy.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FollowMapper {
    List<User> selectFollowUserByUserId(int userId);

    List<User> selectUserByFollowUserId(int followUserId);

    Follow selectFollowUserByUserIdAndFollowUserId(@Param("userId") int userId, @Param("followUserId") int followUserId);

    int addFollowUser(@Param("userId") int userId, @Param("followUserId") int followUserId);

    int deleteFollowUserByFollowId(int followId);

    int selectFollowCountByUserId(int userId);

    int selectFansCountByUserId(int userId);

}

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
    /**
     * 通过用户id查询他所关注的用户
     *
     * @param userId
     * @return
     */
    List<User> selectFollowUserByUserId(int userId);

    /**
     * 通过用户id查询粉丝
     *
     * @param followUserId
     * @return
     */
    List<User> selectFansByUserId(@Param("userId") int userId);

    Follow selectFollowUserByUserIdAndFollowUserId(@Param("userId") int userId, @Param("followUserId") int followUserId);

    int addFollowUser(@Param("userId") int userId, @Param("followUserId") int followUserId);

    int deleteFollowUserByFollowId(int followId);

    int selectFollowCountByUserId(int userId);

    int selectFansCountByUserId(int userId);

}

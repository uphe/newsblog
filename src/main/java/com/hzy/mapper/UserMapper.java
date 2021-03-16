package com.hzy.mapper;

import com.hzy.dto.UserUpdateDTO;
import com.hzy.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: hzy
 * @Date: 2020/6/12
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 修改用户信息
     *
     * @param userUpdateDTO
     * @return
     */
    int updateUserByUserId(UserUpdateDTO userUpdateDTO);

    /**
     * 通过id查询用户
     *
     * @param userId
     * @return
     */
    User selectUserById(int userId);

    /**
     * 通过用户名查询用户
     *
     * @param username
     * @return
     */
    User selectUserByName(String username);
}
package com.hzy.mapper;

import com.hzy.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    int addUser(User user);
    int deleteUserById(int userId);
    int updateUser(User user);
    List<User> selectAllUser();
    User selectUserById(int userId);
    User selectUserByName(String username);
}
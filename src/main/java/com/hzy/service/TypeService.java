package com.hzy.service;

import com.hzy.pojo.Type;

import java.util.List;

public interface TypeService {

    int addType(Type type);

    int addBatchType(List<Type> types);

    List<Type> selectTypeByUserId(int userId);

    List<String> selectTypeNameByUserId(int userId);

    List<String> selectTypeNameByBlogId(int blogId);
}

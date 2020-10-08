package com.hzy.service;

import com.hzy.mapper.TypeMapper;
import com.hzy.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    @Autowired
    private TypeMapper typeMapper;
    public int addType(Type type) {
        return typeMapper.addType(type);
    }
    public int addBatchType(List<Type> types) {
        return typeMapper.addBatchType(types);
    }
    public List<Type> selectTypeByUserId(int userId) {
        return typeMapper.selectTypeByUserId(userId);
    }

    public List<String> selectTypeNameByUserId(int userId) {
        return typeMapper.selectTypeNameByUserId(userId);
    }

    public List<String> selectTypeNameByBlogId(int blogId) {
        return typeMapper.selectTypeNameByBlogId(blogId);
    }
}

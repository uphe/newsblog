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
    public List<Type> selectTypeByUserId(int userId) {
        return typeMapper.selectTypeByUserId(userId);
    }
}

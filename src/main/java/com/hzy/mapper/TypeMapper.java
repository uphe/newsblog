package com.hzy.mapper;

import com.hzy.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface TypeMapper {
    int addType(Type type);
    List<Type> selectTypeByUserId(int userId);
}

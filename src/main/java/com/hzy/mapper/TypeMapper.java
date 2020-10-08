package com.hzy.mapper;

import com.hzy.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface TypeMapper {
    int addType(Type type);
    int addBatchType(List<Type> types);
    List<Type> selectTypeByUserId(int userId);
    List<String> selectTypeNameByUserId(int userId);
    List<String> selectTypeNameByBlogId(int blogId);
}

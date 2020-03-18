package com.hzy.mapper;

import com.hzy.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BlogMapper {
    int addBlog(Blog blog);
}

package com.hzy.mapper;

import com.hzy.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface CollectMapper {
    List<Blog> selectCollectBlogByUserId(int userId);
    int selectCollectCountByBlogId(int blogId);
    int addCollectBlog(@Param("userId") int userId,@Param("blogId") int blogId);
    int deleteCollectBlogByCollectId(int collectId);
}

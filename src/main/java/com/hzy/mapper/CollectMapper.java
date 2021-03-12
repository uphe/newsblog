package com.hzy.mapper;

import com.hzy.pojo.Blog;
import com.hzy.pojo.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: hzy
 * @Date: 2020/6/12
 */
@Mapper
@Repository
public interface CollectMapper {
    List<Blog> selectCollectBlogByUserId(int userId);

    int selectCollectCountByBlogId(int blogId);

    /**
     * 通过用户id和文章id查询收藏
     *
     * @param userId
     * @param blogId
     * @return
     */
    Collect selectCollectByUserIdAndBlogId(@Param("userId") int userId, @Param("blogId") int blogId);

    int addCollectBlog(@Param("userId") int userId, @Param("blogId") int blogId);

    /**
     * 取消收藏
     *
     * @param userId
     * @param blogId
     * @return
     */
    int deleteCollectBlogByCollectId(@Param("userId") int userId, @Param("blogId") int blogId);
}

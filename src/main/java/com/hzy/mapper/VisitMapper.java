package com.hzy.mapper;

import com.hzy.pojo.Visit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: hzy
 * @Date: 2021/3/11
 */
@Mapper
@Repository
public interface VisitMapper {
    /**
     * 添加一条访问记录
     *
     * @param visit
     * @return
     */
    int addVisit(Visit visit);

    /**
     * 通过用户id和文章id查询浏览记录
     *
     * @param userId
     * @param blogId
     * @return
     */
    Visit selectVisitByUserIdAndBlogId(@Param("userId") int userId, @Param("blogId") int blogId);
}

package com.hzy.service;

import com.hzy.pojo.Blog;

import java.util.List;

public interface CollectService {

    /**
     * 查询某个用户所收藏的文章
     *
     * @param userId
     * @return
     */
    List<Blog> selectCollectBlogByUserId(int userId);

    /**
     * 查询某篇博客被收藏了多少次
     *
     * @param blogId
     * @return
     */
    int selectCollectCountByBlogId(int blogId);

    /**
     * 添加收藏博客
     *
     * @param userId
     * @param blogId
     * @return
     */
    int addCollectBlog(int userId, int blogId);

    /**
     * 取消收藏博客
     *
     * @param collectId
     * @return
     */
    int deleteCollectBlogByCollectId(int collectId);
}

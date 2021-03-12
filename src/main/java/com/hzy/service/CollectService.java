package com.hzy.service;

import com.hzy.dto.CollectDTO;
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
     * @param collectDTO
     * @return
     */
    int addCollectBlog(CollectDTO collectDTO);

}

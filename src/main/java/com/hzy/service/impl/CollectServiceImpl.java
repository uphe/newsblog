package com.hzy.service.impl;

import com.hzy.mapper.CollectMapper;
import com.hzy.pojo.Blog;
import com.hzy.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    /**
     * 查询某个用户所收藏的文章
     * @param userId
     * @return
     */
    public List<Blog> selectCollectBlogByUserId(int userId) {
        return collectMapper.selectCollectBlogByUserId(userId);
    }

    /**
     * 查询某篇博客被收藏了多少次
     * @param blogId
     * @return
     */
    public int selectCollectCountByBlogId(int blogId) {
        return collectMapper.selectCollectCountByBlogId(blogId);
    }

    /**
     * 添加收藏博客
     * @param userId
     * @param blogId
     * @return
     */
    public int addCollectBlog(int userId, int blogId) {
        return collectMapper.addCollectBlog(userId, blogId);
    }

    /**
     * 取消收藏博客
     * @param collectId
     * @return
     */
    public int deleteCollectBlogByCollectId(int collectId) {
        return collectMapper.deleteCollectBlogByCollectId(collectId);
    }
}

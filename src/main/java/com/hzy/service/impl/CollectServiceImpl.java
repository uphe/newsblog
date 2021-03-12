package com.hzy.service.impl;

import com.hzy.dto.CollectDTO;
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
     *
     * @param userId
     * @return
     */
    @Override
    public List<Blog> selectCollectBlogByUserId(int userId) {
        return collectMapper.selectCollectBlogByUserId(userId);
    }

    /**
     * 查询某篇博客被收藏了多少次
     *
     * @param blogId
     * @return
     */
    @Override
    public int selectCollectCountByBlogId(int blogId) {
        return collectMapper.selectCollectCountByBlogId(blogId);
    }

    /**
     * 添加收藏博客
     *
     * @param collectDTO
     * @return
     */
    @Override
    public int addCollectBlog(CollectDTO collectDTO) {
        int userId = collectDTO.getUserId();
        int blogId = collectDTO.getBlogId();
        // 如果是第奇数次次点，那就是关注
        if (collectMapper.selectCollectByUserIdAndBlogId(userId, blogId) != null) {
            return collectMapper.deleteCollectBlogByCollectId(userId, blogId);
        } else {
            return collectMapper.addCollectBlog(userId, blogId);
        }

    }
}

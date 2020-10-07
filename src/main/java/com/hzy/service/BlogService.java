package com.hzy.service;

import com.hzy.mapper.BlogMapper;
import com.hzy.mapper.LabelMapper;
import com.hzy.mapper.UserMapper;
import com.hzy.pojo.Blog;
import com.hzy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LabelMapper labelMapper;

    /**
     * 发布博客
     * @param blog
     * @return
     */
    public int addBlog(Blog blog) {
        return blogMapper.addBlog(blog);
    }

    /**
     * 分页查询博客
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    public List<Blog> selectBlogByUserIdAndOffset (int userId,int offset,int limit) {
        return blogMapper.selectBlogByUserIdAndOffset(userId,offset,limit);
    }

    /**
     * 通过id查询某一篇博客
     * @param blogId
     * @return
     */
    public Blog selectBlogById(int blogId) {
        return blogMapper.selectBlogById(blogId);
    }

    /**
     * 通过博客id查询评论数
     * @param blogId
     * @return
     */
    public int selectCommentCountByBlogId(int blogId) {
        return blogMapper.selectCommentCountByBlogId(blogId);
    }

    /**
     * 通过博客id更新评论数
     * @param commentCount
     * @param blogId
     * @return
     */
    public int updateCommentCountByBlogId(int commentCount, int blogId) {
        return blogMapper.updateCommentCountByBlogId(commentCount,blogId);
    }

    /**
     * 通过博客id更新点击量
     * @param blogId
     * @return
     */
    public int updateHitCountByBlogId(int blogId) {
        return blogMapper.updateHitCountByBlogId(blogId);
    }

    /**
     * 通过博客id更新点赞数
     * @param likeCount
     * @param blogId
     * @return
     */
    public int updateLikeCountByBlogId(int likeCount,int blogId) {
        return blogMapper.updateLikeCountByBlogId(likeCount,blogId);
    }

    /**
     * 通过用户id查询用户的总访问量
     * @param userId
     * @return
     */
    public int selectHitCountSumByUserId(int userId) {
        return blogMapper.selectHitCountSumByUserId(userId);
    }

    /**
     * 通过用户id查询用户的总文章数
     * @param userId
     * @return
     */
    public int selectBlogCountSumByUserId(int userId) {
        return blogMapper.selectBlogCountSumByUserId(userId);
    }

    /**
     * 通过用户id查询用户的获赞总数
     * @param userId
     * @return
     */
    public int selectLikeCountSumByUserId(int userId) {
        return blogMapper.selectLikeCountSumByUserId(userId);
    }

    /**
     * 根据用户id获取博客，如果id为0，则获取全部博客
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    public Map<String, Map<String,Object>> getBlog(int userId, int offset, int limit) {
        List<Blog> blogList = blogMapper.selectBlogByUserIdAndOffset(userId, offset, limit);
        Map<String,Map<String,Object>> mapMap = new LinkedHashMap<>();
        int i = 0;
        for (Blog blog : blogList) {
            User user = userMapper.selectUserById(blog.getUserId());
            //HashMap是有无序的
            //LinkedHashMap 和 TreeMap 是有序的,存取顺序
            Map<String,Object> map = new HashMap<>();
            map.put("blog",blog);
            map.put("user",user);
            mapMap.put("map" + i++,map);
        }
        return mapMap;
    }

    /**
     * 内容推荐算法，根据用户个人喜欢，进行个性化的推荐
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    public Map<String, Map<String, Object>> getPersonalizationBlog(int userId, int offset, int limit) {
        Map<String,Map<String,Object>> mapMap = new LinkedHashMap<>();

        List<Map<String, Object>> maps = labelMapper.selectLabelByUserId(userId);
        for (Map<String, Object> map : maps) {

        }


        return mapMap;
    }
}

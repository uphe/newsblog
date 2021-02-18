package com.hzy.service;

import com.hzy.dto.BlogDTO;
import com.hzy.vo.BaseResult;

import javax.servlet.http.HttpSession;

public interface BlogService {

    /**
     * 首页，暂时是根据一年以内的优文推荐，根据点赞*100+访问排序
     *
     * @param page
     * @param session
     * @return
     */
    BaseResult getIndexBlogVO(int page, HttpSession session);

    /**
     * 内容推荐算法，根据用户个人喜欢，进行个性化的推荐
     * 具体实现，首先查出用户浏览的标签进行排序，选出前5个
     * 然后对这5个标签进行查询，每个标签查出40个最近优文
     * 然后通过权重对查出的所有文章进行筛选，最后展示
     *
     * @param page
     * @param session
     * @return
     */
    BaseResult getRecommendBlogVO(int page, HttpSession session);


    /**
     * 最新的文章，根据创建时间进行的排序
     *
     * @param page
     * @param session
     * @return
     */
    BaseResult getNewestBlogVO(int page, HttpSession session);

    /**
     * 首页中用户关注的榜单
     *
     * @param page
     * @param session
     * @return
     */
    BaseResult getFollowBlogVO(int page, HttpSession session);

    /**
     * 今日推荐你文章，即是昨天的好文，根据点赞*100+访问进行排序
     *
     * @return
     */
    BaseResult getTodayBlogVO();

    /**
     * 发布博客
     *
     * @param blogDTO
     * @param session
     * @return
     */
    BaseResult publishBlog(BlogDTO blogDTO, HttpSession session);


    /**
     * 通过id查询某一篇博客，即是获取文章详情
     *
     * @param blogId
     * @param session
     * @return
     */
    BaseResult getBlogVOByUserId(int blogId, HttpSession session);

    /**
     * 查询某个用户的文章，通过时间排序
     *
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    BaseResult getBlogVOByUserIdAndOffset(int userId, int offset, int limit);

    /**
     * 查询某个用户的文章，通过访问量排序
     *
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    BaseResult getBlogVOByUserIdSortHitCount(int userId, int offset, int limit);

    /**
     * 通过类别名获取文章
     *
     * @param typeName
     * @param offset
     * @param limit
     * @return
     */
    BaseResult getBlogVoByTypeNameAndOffset(String typeName, int page);

    /**
     * 通过博客id查询评论数
     *
     * @param blogId
     * @return
     */
    int selectCommentCountByBlogId(int blogId);

    /**
     * 通过博客id更新评论数
     *
     * @param commentCount
     * @param blogId
     * @return
     */
    int updateCommentCountByBlogId(int commentCount, int blogId);

    /**
     * 通过博客id更新点击量
     *
     * @param blogId
     * @return
     */
    int updateHitCountByBlogId(int blogId);

    /**
     * 通过博客id更新点赞数
     *
     * @param likeCount
     * @param blogId
     * @return
     */
    int updateLikeCountByBlogId(int likeCount, int blogId);

    /**
     * 通过用户id查询用户的总访问量
     *
     * @param userId
     * @return
     */
    int selectHitCountSumByUserId(int userId);

    /**
     * 通过用户id查询用户的总文章数
     *
     * @param userId
     * @return
     */
    int selectBlogCountSumByUserId(int userId);

    /**
     * 通过用户id查询用户的获赞总数
     *
     * @param userId
     * @return
     */
    int selectLikeCountSumByUserId(int userId);
}

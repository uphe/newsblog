package com.hzy.mapper;

import com.hzy.pojo.Blog;
import com.hzy.vo.BlogVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
/**
 * @Author: hzy
 * @Date: 2020/6/12
 */
public interface BlogMapper {
    /**
     * 发布文章
     *
     * @param blog
     * @return
     */
    int addBlog(Blog blog);

    /**
     * 修改文章
     *
     * @param blog
     * @return
     */
    int updateBlog(Blog blog);

    /**
     * 删除文章
     *
     * @param blogId
     * @return
     */
    int deleteBlogById(int blogId);

    List<Blog> selectBlogAll();

    Blog selectBlogById(int blogId);

    BlogVO selectBlogVOByBlogId(int blogId);

    List<Blog> selectBlogByUserIdAndOffset(int userId, int offset, int limit);

    int selectCommentCountByBlogId(int blogId);

    int updateCommentCountByBlogId(int commentCount, int blogId);

    int updateHitCountByBlogId(int blogId);

    int updateLikeCountByBlogId(int likeCount, int blogId);

    int selectHitCountSumByUserId(int userId);

    int selectBlogCountSumByUserId(int userId);

    int selectLikeCountSumByUserId(int userId);

    List<BlogVO> selectBlogVOByLabelName(int userId, String labelName, int offset, int limit);

    List<BlogVO> selectBlogVOByUserIdAndOffset(int userId, int offset, int limit);

    List<BlogVO> selectBlogVOByUserIdSortHitCount(int userId, int offset, int limit);

    List<BlogVO> selectNewestBlogVOByUserIdAndOffset(int userId, int offset, int limit);

    List<BlogVO> selectTodayBlogVOByUserIdAndOffset(int offset, int limit);

    List<BlogVO> selectIndexBlogVOByUserIdAndOffset(int userId, int offset, int limit);

    List<BlogVO> selectFollowBlogVOByUserIdAndOffset(int userId, int offset, int limit);

    List<BlogVO> selectBlogVoByTypeNameAndOffset(String typeName, int offset, int limit);

}

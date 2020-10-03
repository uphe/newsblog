package com.hzy.mapper;

import com.hzy.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface BlogMapper {
    int addBlog(Blog blog);
    Blog selectBlogById(int blogId);
    List<Blog> selectBlogByUserIdAndOffset(int userId,int offset,int limit);
    int selectCommentCountByBlogId(int blogId);
    int updateCommentCountByBlogId(int commentCount, int blogId);
    int updateHitCountByBlogId(int blogId);
    int updateLikeCountByBlogId(int likeCount,int blogId);
    int selectHitCountSumByUserId(int userId);
    int selectBlogCountSumByUserId(int userId);
    int selectLikeCountSumByUserId(int userId);
}

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
    // 这里的offset是开始下标
    List<Blog> selectBlogByUserIdAndOffset(int userId,int offset,int limit);
    int selectCommentCountByBlogId(int blogId);
    int updateCommentCountByBlogId(int commentCount, int blogId);
}

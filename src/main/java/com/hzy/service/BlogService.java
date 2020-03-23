package com.hzy.service;

import com.hzy.mapper.BlogMapper;
import com.hzy.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogMapper blogMapper;

    public int addBlog(Blog blog) {
        return blogMapper.addBlog(blog);
    }
    public List<Blog> selectBlogByUserIdAndOffset (int userId,int offset,int limit) {
        return blogMapper.selectBlogByUserIdAndOffset(userId,offset,limit);
    }
    public Blog selectBlogById(int blogId) {
        return blogMapper.selectBlogById(blogId);
    }

}

package com.hzy.service;

import com.hzy.mapper.BlogMapper;
import com.hzy.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    @Autowired
    private BlogMapper blogMapper;
    public int addBlog(Blog blog) {
        return blogMapper.addBlog(blog);
    }
}

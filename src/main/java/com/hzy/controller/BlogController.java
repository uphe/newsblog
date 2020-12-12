package com.hzy.controller;

import com.hzy.dto.BlogDTO;
import com.hzy.service.BlogService;
import com.hzy.service.ElasticSearchService;
import com.hzy.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private ElasticSearchService elasticSearchService;

    @PostMapping("/user/editor")
    public BaseResult publishBlog(@RequestBody BlogDTO blogDTO, HttpSession session) {
        return blogService.publishBlog(blogDTO, session);
    }

    @GetMapping("/all/detail/{blogId}")
    public BaseResult detail(@PathVariable("blogId") int blogId, HttpSession session) {
        return blogService.getBlogVOByUserId(blogId, session);
    }

    @GetMapping("/search/{msg}")
    public BaseResult search(@PathVariable("msg") String msg) {
        return elasticSearchService.search(msg);
    }

    @GetMapping(path = {"/user/{userId}"})
    public BaseResult userIndex(@PathVariable("userId") int userId) {
        return blogService.getBlogVOByUserIdAndOffset(userId, 0, 40);
    }

}

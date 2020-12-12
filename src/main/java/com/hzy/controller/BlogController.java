package com.hzy.controller;

import com.hzy.dto.BlogDTO;
import com.hzy.service.*;
import com.hzy.vo.BaseResult;
import com.hzy.vo.BlogVO;
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
        return BaseResult.ok(blogService.publishBlog(blogDTO, session));
    }

    @GetMapping("/all/detail/{blogId}")
    public BaseResult detail(@PathVariable("blogId") int blogId,HttpSession session) {
        return BaseResult.ok(blogService.getBlogVOByUserId(blogId,session));
    }

    @GetMapping("/search/{msg}")
    public BaseResult search(@PathVariable("msg") String msg) {
        return BaseResult.ok(elasticSearchService.search(msg));
    }

}

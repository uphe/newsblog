package com.hzy.controller;

import com.hzy.dto.BlogDTO;
import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.service.ElasticSearchService;
import com.hzy.vo.BaseResult;
import com.hzy.vo.BlogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping({"/all/hot/{page}"})
    public BaseResult index(@PathVariable("page") int page, HttpSession session) {
        return blogService.getIndexBlogVO(page, session);
    }

    @GetMapping("/user/recommend/{page}")
    public BaseResult recommend(@PathVariable("page") int page, HttpSession session) {
        return blogService.getRecommendBlogVO(page, session);
    }

    @GetMapping("/all/newest/{page}")
    public BaseResult newest(@PathVariable("page") int page, HttpSession session) {
        return blogService.getNewestBlogVO(page, session);
    }

    @GetMapping("/user/follow/{page}")
    public BaseResult follow(@PathVariable("page") int page, HttpSession session) {
        return blogService.getFollowBlogVO(page, session);
    }

    @GetMapping("/all/todayrecommend")
    public BaseResult todayRecommend() {
        return blogService.getTodayBlogVO();
    }

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

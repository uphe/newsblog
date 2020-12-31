package com.hzy.controller;

import com.hzy.dto.BlogDTO;
import com.hzy.service.BlogService;
import com.hzy.service.ElasticSearchService;
import com.hzy.vo.BaseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@Tag(name = "文章", description = "文章相关的controller")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping({"/all/hot/{page}"})
    @Operation(summary = "首页热榜，通过传入一个page，返回该page的数据（例如传入3，返回第三页的数据）")
    public BaseResult index(@PathVariable("page") int page, HttpSession session) {
        return blogService.getIndexBlogVO(page, session);
    }

    @GetMapping("/all/newest/{page}")
    @Operation(summary = "最新发布榜单，传入一个page，返回该page的数据")
    public BaseResult newest(@PathVariable("page") int page, HttpSession session) {
        return blogService.getNewestBlogVO(page, session);
    }

    @GetMapping("/all/todayRecommend")
    @Operation(summary = "今日推荐榜")
    public BaseResult todayRecommend() {
        return blogService.getTodayBlogVO();
    }

    @GetMapping("/all/detail/{blogId}")
    @Operation(summary = "文章详情，传入一个文章id，返回该文章的详情内容")
    public BaseResult detail(@PathVariable("blogId") int blogId, HttpSession session) {
        return blogService.getBlogVOByUserId(blogId, session);
    }

    @GetMapping("/user/recommend/{page}")
    @Operation(summary = "推荐榜，传入一个page，返回该页面的数据")
    public BaseResult recommend(@PathVariable("page") int page, HttpSession session) {
        return blogService.getRecommendBlogVO(page, session);
    }

    @GetMapping("/user/follow/{page}")
    @Operation(summary = "关注，根据被关注者的动态，来进行展示")
    public BaseResult follow(@PathVariable("page") int page, HttpSession session) {
        return blogService.getFollowBlogVO(page, session);
    }

    @PostMapping("/user/editor")
    @Operation(summary = "发布文章")
    public BaseResult publishBlog(@RequestBody BlogDTO blogDTO, HttpSession session) {
        return blogService.publishBlog(blogDTO, session);
    }

    @GetMapping(path = {"/user/{userId}"})
    @Operation(summary = "通过用户id，返回该用户的文章列表")
    public BaseResult userIndex(@PathVariable("userId") int userId) {
        return blogService.getBlogVOByUserIdAndOffset(userId, 0, 40);
    }

    @GetMapping("/search/{msg}")
    @Operation(summary = "通过输入文章标题，进行分词模糊匹配")
    public BaseResult search(@PathVariable("msg") String msg) {
        return elasticSearchService.search(msg);
    }

}

package com.hzy.controller;

import com.hzy.dto.*;
import com.hzy.service.BlogService;
import com.hzy.service.ElasticSearchService;
import com.hzy.vo.BaseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @Author: hzy
 * @Date: 2020/6/12
 */
@RestController
@Tag(name = "文章", description = "文章相关的controller")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private ElasticSearchService elasticSearchService;

    @PostMapping({"/all/hot"})
    @Operation(summary = "首页热榜，返回最热文章")
    public BaseResult index(@RequestBody @Valid PageDTO pageDTO, HttpSession session) {
        return blogService.getIndexBlogVO(pageDTO.getPage(), pageDTO.getLimit(), session);
    }

    @GetMapping("/all/newest/{page}")
    @Operation(summary = "最新发布榜单，返回最新发布的文章")
    public BaseResult newest(@PathVariable("page") int page, HttpSession session) {
        return blogService.getNewestBlogVO(page, session);
    }

    @GetMapping("/all/todayRecommend")
    @Operation(summary = "今日推荐榜，返回今日最热文章")
    public BaseResult todayRecommend() {
        return blogService.getTodayBlogVO();
    }

    @GetMapping("/all/detail/{blogId}")
    @Operation(summary = "文章详情，返回文章的详情内容")
    public BaseResult detail(@PathVariable("blogId") int blogId, HttpSession session) {
        return blogService.getBlogVOByBlogId(blogId, session);
    }

    @PostMapping("/all/getBlogByTypeName")
    @Operation(summary = "通过类别名获取文章列表")
    public BaseResult getBlogByTypeName(@RequestBody @Valid TypeDTO typeDTO) {
        return blogService.getBlogVoByTypeNameAndOffset(typeDTO.getTypeName(), typeDTO.getPage(), typeDTO.getLimit());
    }

    @PostMapping(path = {"/all/user"})
    @Operation(summary = "通过用户id，返回该用户的文章列表，并通过传入的字段排序（createDate、hitCount）,默认排序是createDate")
    public BaseResult userIndex(@RequestBody @Valid InfoDTO infoDTO) {
        return blogService.getBlogVOByUserIdAndOffset(infoDTO.getId(), infoDTO.getPage(), infoDTO.getLimit(), infoDTO.getSortName());
    }

    @PostMapping("/all/search")
    @Operation(summary = "通过输入文章标题，进行分词模糊匹配")
    public BaseResult search(@RequestBody @Valid SearchDTO searchDTO) {
        return elasticSearchService.search(searchDTO.getTitle(), searchDTO.getPage(), searchDTO.getLimit());
    }

    @PostMapping("/user/recommend")
    @Operation(summary = "推荐榜，根据用户个性进行推荐")
    public BaseResult recommend(@RequestBody @Valid PageDTO pageDTO, HttpSession session) {
        return blogService.getRecommendBlogVO(pageDTO.getPage(), pageDTO.getLimit(), session);
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

    @PostMapping("/user/update")
    @Operation(summary = "编辑文章")
    public BaseResult updateBlog(@RequestBody BlogDTO blogDTO, HttpSession session) {
        return blogService.updateBlog(blogDTO, session);
    }

}

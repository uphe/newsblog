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

@RestController
@Tag(name = "文章", description = "文章相关的controller")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private ElasticSearchService elasticSearchService;

    private final String CREATE_DATE = "createDate";
    private final String HIT_COUNT = "hitCount";

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
        return blogService.getBlogVOByBlogId(blogId, session);
    }

    @PostMapping("/all/getBlogByTypeName")
    @Operation(summary = "通过类别名获取文章列表")
    public BaseResult getBlogByTypeName(@RequestBody @Valid TypeDTO typeDTO) {
        return blogService.getBlogVoByTypeNameAndOffset(typeDTO.getTypeName(), typeDTO.getPage());
    }

    @PostMapping(path = {"/all/user"})
    @Operation(summary = "通过用户id，返回该用户的文章列表，并通过传入的字段排序（createDate、hitCount）,默认排序是createDate")
    public BaseResult userIndex(@RequestBody @Valid InfoDTO infoDTO) {
        int userId = infoDTO.getId();
        int limit = infoDTO.getLimit();
        int offset = limit * (infoDTO.getPage() - 1);

        if (HIT_COUNT.equals(infoDTO.getSortName())) {
            return blogService.getBlogVOByUserIdSortHitCount(userId, offset, limit);
        } else {
            // 默认是时间排序
            return blogService.getBlogVOByUserIdAndOffset(userId, offset, limit);
        }
    }

    @PostMapping("/all/search")
    @Operation(summary = "通过输入文章标题，进行分词模糊匹配")
    public BaseResult search(@RequestBody @Valid SearchDTO searchDTO) {
        return elasticSearchService.search(searchDTO.getTitle(), searchDTO.getPage(), searchDTO.getLimit());
    }

    @GetMapping("/user/recommend")
    @Operation(summary = "推荐榜，传入一个Page，返回该页面的数据")
    public BaseResult recommend(@RequestBody @Valid PageDTO pageDTO, HttpSession session) {
        return blogService.getRecommendBlogVO(pageDTO.getPage(),pageDTO.getLimit(), session);
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

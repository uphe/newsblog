package com.hzy.controller;

import com.hzy.pojo.Blog;
import com.hzy.service.CollectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collect")
@Tag(name = "收藏", description = "收藏相关的controller")
public class CollectController {
    @Autowired
    private CollectService collectService;

    @GetMapping("/select/{userId}")
    @Operation(summary = "通过用户id查询该用户所收藏的文章")
    public List<Blog> selectCollect(@PathVariable("userId") int userId) {
        return collectService.selectCollectBlogByUserId(userId);
    }

    @GetMapping("/add/{userId}/{blogId}")
    @Operation(summary = "用户添加文章到自己的收藏夹")
    public int addCollect(@PathVariable("userId") int userId, @PathVariable("blogId") int blogId) {
        return collectService.addCollectBlog(userId, blogId);
    }

    @GetMapping("/delete/{collectId}")
    @Operation(summary = "取消收藏文章")
    public int deleteCollect(@PathVariable("collectId") int collectId) {
        return collectService.deleteCollectBlogByCollectId(collectId);
    }
}

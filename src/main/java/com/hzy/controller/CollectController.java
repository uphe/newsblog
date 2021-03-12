package com.hzy.controller;

import com.hzy.dto.CollectDTO;
import com.hzy.pojo.Blog;
import com.hzy.service.CollectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: hzy
 * @Date: 2020/6/12
 */
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

    @GetMapping("/addCollect")
    @Operation(summary = "用户添加文章到自己的收藏夹，或取消收藏")
    public int addCollect(@RequestBody @Valid CollectDTO collectDTO) {
        return collectService.addCollectBlog(collectDTO);
    }
}

package com.hzy.controller;

import com.hzy.pojo.Blog;
import com.hzy.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    @RequestMapping("/select/{userId}")
    public List<Blog> selectCollect(@PathVariable("userId") int userId) {
        return collectService.selectCollectBlogByUserId(userId);
    }

    @RequestMapping("/add/{userId}/{blogId}")
    public int addCollect(@PathVariable("userId") int userId, @PathVariable("blogId") int blogId) {
        return collectService.addCollectBlog(userId, blogId);
    }

    @RequestMapping("/delete/{collectId}")
    public int deleteCollect(@PathVariable("collectId") int collectId) {
        return collectService.deleteCollectBlogByCollectId(collectId);
    }
}

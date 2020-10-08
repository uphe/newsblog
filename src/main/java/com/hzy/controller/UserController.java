package com.hzy.controller;

import com.hzy.pojo.Blog;
import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.service.UserService;
import com.hzy.vo.BlogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController{
    @Autowired
    private BlogService blogService;
    /*
     * 这是查看某个具体用户的文章，并展现出来
     * 默认userId为0，展示是所有用户的文章
     * */
    @RequestMapping(path = {"/user/{userId}"})
    public List<BlogVO> userIndex(@PathVariable("userId") int userId) {
        List<BlogVO> userBlogs = blogService.getBlog(userId,0,40);
        return userBlogs;
    }
}

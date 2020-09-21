package com.hzy.controller;

import com.hzy.pojo.Blog;
import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CommonMethod {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;


    /**
     * 根据用户id获取博客，如果id为0，则获取全部博客
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    public Map<String, Map<String,Object>> getBlog(int userId, int offset, int limit) {
        List<Blog> blogList = blogService.selectBlogByUserIdAndOffset(userId, offset, limit);
        Map<String,Map<String,Object>> mapMap = new LinkedHashMap<>();
        int i = 0;
        for (Blog blog : blogList) {
            User user = userService.selectUserById(blog.getUserId());
            //HashMap是有无序的
            //LinkedHashMap 和 TreeMap 是有序的,存取顺序
            Map<String,Object> map = new HashMap<>();
            map.put("blog",blog);
            map.put("user",user);
            mapMap.put("map" + i++,map);
        }
        return mapMap;
    }
}

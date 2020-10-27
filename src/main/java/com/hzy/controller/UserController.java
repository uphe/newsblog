package com.hzy.controller;

import com.hzy.pojo.Blog;
import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.service.FollowService;
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
    @Autowired
    private UserService userService;
    @Autowired
    private FollowService followService;

    /*
     * 这是查看某个具体用户的文章
     * */
    @RequestMapping(path = {"/user/{userId}"})
    public List<BlogVO> userIndex(@PathVariable("userId") int userId) {
        List<BlogVO> userBlogs = blogService.getBlogVOByUserIdAndOffset(userId,0,40);
        return userBlogs;
    }

    @RequestMapping("/userinfo/{userId}")
    public Map<String,Object> selectHitCountSumByUserId(@PathVariable("userId") int userId) {
        Map<String,Object> map = new HashMap<>();
        User user = userService.selectUserById(userId);
        int blogCountSum = blogService.selectBlogCountSumByUserId(userId);
        int hitCountSum = blogService.selectHitCountSumByUserId(userId);
        int followCount = followService.selectFollowCountByUserId(userId);
        int fansCount = followService.selectFansCountByUserId(userId);
        int likeCount = blogService.selectLikeCountSumByUserId(userId);
        map.put("user",user);
        map.put("blogCountSum",blogCountSum);
        map.put("hitCountSum",hitCountSum);
        map.put("followCount",followCount);
        map.put("fansCount",fansCount);
        map.put("likeCount",likeCount);

        return map;
    }

}

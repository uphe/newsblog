package com.hzy.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hzy.pojo.*;
import com.hzy.service.*;
import com.hzy.utils.*;
import com.hzy.vo.BlogVO;
import com.hzy.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private FollowService followService;
    @Autowired
    private ElasticSearchService elasticSearchService;

    @PostMapping("/editor")
    public String Editor(@RequestBody BlogVO blogVO, HttpServletRequest request) {

        String token = request.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.getToken(token);
        int userId = Integer.valueOf(decodedJWT.getClaim("userId").asString());

        return blogService.publishBlog(userId, blogVO);
    }

    @RequestMapping("/detail/{blogId}")
    public BlogVO detail(@PathVariable("blogId") int blogId) {

        blogService.updateHitCountByBlogId(blogId);// 每点击一次，点击量加1
        BlogVO blogVO = blogService.getBlogVOByUserId(blogId);

        Map<String, Object> map = new HashMap<>();
        // 下面是从数据库中获取到博客，然后转化为html传到前端
        String markdownString = blogVO.getArticle();
        String html = MarkDownUtil.mdToHtml(markdownString);
        blogVO.setArticle(html);

        return blogVO;
    }

    @RequestMapping("/search/{msg}")
    public List<BlogVO> search(@PathVariable("msg") String msg) {
        return elasticSearchService.search(msg);
    }

}

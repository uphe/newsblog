package com.hzy.controller;

import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.service.UserService;
import com.hzy.utils.JSONUtils;
import com.hzy.vo.BlogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class IndexController{
    @Autowired
    private  UserService userService;
    @Autowired
    private BlogService blogService;

    @GetMapping({"/all/hot/{page}"})
    public List<BlogVO> index(@PathVariable("page") int page, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = -1;
        if (user != null) {
            userId = user.getUserId();
        }
        List<BlogVO> blogVOS =  blogService.getIndexBlogVO(userId, 20 * (page - 1),20);
        return blogVOS;
    }

    @GetMapping("/user/recommend/{page}")
    public List<BlogVO> recommend( @PathVariable("page") int page, HttpSession session) {

        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        List<BlogVO> recommendBlogVO = blogService.getRecommendBlogVO(userId, 40 * (page - 1), 40);

        return recommendBlogVO;
    }

    @GetMapping("/all/newest/{page}")
    public List<BlogVO> newest(@PathVariable("page") int page, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = -1;
        if (user != null) {
            userId = user.getUserId();
        }
        List<BlogVO> blogVOS =  blogService.getNewestBlogVO(userId, 40 * (page - 1),40);

        return  blogVOS;
    }

    @GetMapping("/user/follow/{page}")
    public List<BlogVO> follow(@PathVariable("page") int page, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        List<BlogVO> blogVOS = blogService.getFollowBlogVO(userId, 40 * (page - 1), 40);
        return blogVOS;
    }

    @GetMapping("/all/todayrecommend")
    public List<BlogVO> todayRecommend() {
        List<BlogVO> blogVOS = blogService.getTodayBlogVO(0, 10);
        return blogVOS;
    }

    @PostMapping("/uploadimage")
    public String uploadImage(@RequestParam("file") MultipartFile file, HttpSession session) {
        String fileUrl = userService.saveImage(file);
        if (fileUrl != null) {
            userService.updateUserByHeadUrl(fileUrl, session);
            return JSONUtils.getJSONString(0,fileUrl);
        }
        return JSONUtils.getJSONString(-1,"error");
    }

    @PostMapping("/uploadeditorimage")
    public String uploadEditorImage(@RequestParam("file") MultipartFile file) {
        String fileUrl = userService.saveImage(file);
        if (fileUrl != null) {
            return JSONUtils.getJSONString(0,fileUrl);
        }
        return JSONUtils.getJSONString(-1,"error");
    }

    @GetMapping("/getImage")
    public void getImage(String fileName, HttpServletResponse response) {
        userService.getImage(fileName,response);
    }
}

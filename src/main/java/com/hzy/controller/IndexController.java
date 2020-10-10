package com.hzy.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hzy.pojo.Blog;
import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.service.UserService;
import com.hzy.utils.JSONUtils;
import com.hzy.utils.JWTUtils;
import com.hzy.vo.BlogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;


@RestController
public class IndexController{
    @Autowired
    private  UserService userService;
    @Autowired
    private BlogService blogService;

    @RequestMapping({"/","/index","/index.html"})
    public List<BlogVO> index() {
        List<BlogVO> blogVOS =  blogService.getIndexBlogVO(0,40);
        return  blogVOS;
    }

    @RequestMapping("/personalization")
    public List<BlogVO> personalization(HttpServletRequest request) {
        String token = request.getHeader("token");

        DecodedJWT decodedJWT = JWTUtils.getToken(token);
        int userId = Integer.valueOf(decodedJWT.getClaim("userId").asString());
        List<BlogVO> personalizationBlog = blogService.getPersonalizationBlog(userId, 0, 40);

        return personalizationBlog;
    }

    @RequestMapping("/newest")
    public List<BlogVO> newest() {

        List<BlogVO> userBlogs =  blogService.getNewestBlogVO(0,40);

        return  userBlogs;
    }

    @RequestMapping("/dynamic")
    public List<BlogVO> dynamic(HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.getToken(token);
        int userId = Integer.valueOf(decodedJWT.getClaim("userId").asString());
        List<BlogVO> blogVOS = blogService.getDynamicBlogVO(userId, 0, 40);
        return blogVOS;
    }

    @RequestMapping("/todayrecommend")
    public List<BlogVO> todayRecommend() {
        List<BlogVO> blogVOS = blogService.getTodayBlogVO(0, 10);
        return blogVOS;
    }

    @PostMapping("/uploadimage")
    public String uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        String fileUrl = userService.saveImage(file);
        if (fileUrl != null) {
            userService.updateUserByHeadUrl(fileUrl,request,response);
            return JSONUtils.getJSONString(0,fileUrl);
        }
        return JSONUtils.getJSONString(-1,"error");
    }

    @PostMapping("/uploadeditorimage")
    public String uploadEditorImage(@RequestParam("editormd-image-file") MultipartFile file) {
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

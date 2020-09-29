package com.hzy.controller;

import com.alibaba.fastjson.JSONObject;
import com.hzy.pojo.Blog;
import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.service.UserService;
import com.hzy.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Map<String,Map<String,Object>> index() {

        Map<String,Map<String,Object>> userBlogs =  blogService.getBlog(0,0,40);

        return  userBlogs;
    }

    @RequestMapping("/toEditor")
    public String toEditor(Model model,HttpSession session) {
        model.addAttribute("user",session.getAttribute("user"));
        return "editor";
    }

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file, HttpSession session) {
        String fileUrl = userService.saveImage(file,session);

        //return "index";
        return "redirect:/";
    }

    @PostMapping("/uploadEditorImage")
    @ResponseBody
    public JSONObject uploadEditorImage(@RequestParam("editormd-image-file") MultipartFile file, HttpSession session) {
        String fileUrl = userService.saveImage(file,session);
        //给editormd进行回调
        JSONObject res = new JSONObject();
        res.put("url",fileUrl);
        res.put("success", 1);
        res.put("message", "upload success!");
        return res;
    }

    @GetMapping("/getImage")
    @ResponseBody
    public void getImage(String fileName, HttpServletResponse response) {
        userService.getImage(fileName,response);
    }
}

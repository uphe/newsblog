package com.hzy.controller;

import com.alibaba.fastjson.JSONObject;
import com.hzy.pojo.Blog;
import com.hzy.pojo.User;
import com.hzy.service.BlogService;
import com.hzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;


@Controller
public class IndexController {
    @Autowired
    private  UserService userService;
    @Autowired
    private BlogService blogService;

    @RequestMapping({"/","/index","/index.html"})
    public String index(HttpSession session, Model model) {
        if (session != null && session.getAttribute("user") != null) {
            model.addAttribute("user",session.getAttribute("user"));
        }
        model.addAttribute("mapMap",getBlog(0,0,40));
        return "index";
    }
    /*
     * 这是查看某个具体用户的文章，并展现出来
     * 默认userId为0，展示是所有用户的文章
     * */
    @RequestMapping(path = {"/user/{userId}"})
    public String userIndex(Model model, @PathVariable("userId") int userId) {
        model.addAttribute("mapMap",getBlog(userId,0,40));
        return "index";
    }
    private Map<String, Map<String,Object>> getBlog(int userId, int offset, int limit) {
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

    @RequestMapping("/toEditor")
    public String toEditor() {
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

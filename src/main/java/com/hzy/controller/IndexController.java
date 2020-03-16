package com.hzy.controller;

import com.hzy.pojo.User;
import com.hzy.service.UserService;
import com.hzy.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class IndexController {
    @Autowired
    private  UserService userService;

    @RequestMapping({"/","/index","/index.html"})
    public String index(HttpSession session, Model model) {
        if (session != null) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("user",user);
        }
        return "index";
    }

    @PostMapping("/uploadImage")
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        System.out.println(file);
        String fileUrl = userService.saveImage(file);
        return fileUrl;
    }

    @GetMapping("/getImage")
    @ResponseBody
    public void getImage(String fileName, HttpServletResponse response) {
        userService.getImage(fileName,response);
    }
}

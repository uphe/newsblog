package com.hzy.controller;

import com.hzy.service.UserService;
import com.hzy.utils.JSONUtils;
import com.hzy.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@RestController
public class FileController {
    @Autowired
    private UserService userService;

    @PostMapping("/uploadimage")
    public BaseResult uploadImage(@RequestParam("file") MultipartFile file, HttpSession session) {
        String fileUrl = userService.saveImage(file);
        if (fileUrl != null) {
            userService.updateUserByHeadUrl(fileUrl, session);
            return BaseResult.ok();
        }
        return BaseResult.error();
    }

    @PostMapping("/uploadeditorimage")
    public BaseResult uploadEditorImage(@RequestParam("file") MultipartFile file) {
        String fileUrl = userService.saveImage(file);
        if (fileUrl != null) {
            return BaseResult.ok();
        }
        return BaseResult.error();
    }

    @GetMapping("/getImage")
    public void getImage(String fileName, HttpServletResponse response) {
        userService.getImage(fileName, response);
    }
}

package com.hzy.controller;

import com.hzy.dto.UserUpdateDTO;
import com.hzy.pojo.User;
import com.hzy.service.UserService;
import com.hzy.utils.JSONUtils;
import com.hzy.vo.BaseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@RestController
@Tag(name = "文件", description = "文件相关的controller")
public class FileController {
    @Autowired
    private UserService userService;

    @PostMapping("/uploadimage")
    @Operation(summary = "上传头像")
    public BaseResult uploadImage(@RequestParam("file") MultipartFile file, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return BaseResult.error("请先登录");
        }
        String fileUrl = userService.saveImage(file);
        if (fileUrl != null) {
            UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
            userUpdateDTO.setUserId(user.getUserId());
            userUpdateDTO.setHeadUrl(fileUrl);
            userService.updateUserByUserId(userUpdateDTO);
            return BaseResult.ok();
        }
        return BaseResult.error();
    }

    @PostMapping("/uploadeditorimage")
    @Operation(summary = "上传文章内图片")
    public BaseResult uploadEditorImage(@RequestParam("file") MultipartFile file) {
        String fileUrl = userService.saveImage(file);
        if (fileUrl != null) {
            return BaseResult.ok();
        }
        return BaseResult.error();
    }

    @GetMapping("/getImage")
    @Operation(summary = "获取图片")
    public void getImage(String fileName, HttpServletResponse response) {
        userService.getImage(fileName, response);
    }
}

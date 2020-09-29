package com.hzy.service;

import com.hzy.controller.LoginController;
import com.hzy.mapper.UserMapper;
import com.hzy.pojo.User;
import com.hzy.utils.FileUtils;
import com.hzy.utils.JSONUtils;
import com.hzy.utils.JWTUtils;
import com.hzy.utils.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserMapper userMapper;

    /**
     * 登录业务，并实现保存密码7天
     * @param response
     * @param username
     * @param password
     * @return
     */
    public String login(HttpServletResponse response, String username, String password) {

        User user = userMapper.selectUserByName(username);
        if (user == null) {
            return JSONUtils.getJSONString(-1,"该用户不存在");
        }
        if (!MD5Utils.MD5(password + user.getSalt()).equals(user.getPassword())) {
            return JSONUtils.getJSONString(-1, "密码错误");
        }

        // 将用户的信息封装到token中
        Map<String, String> payload = new HashMap<>();
        payload.put("userId",String.valueOf(user.getUserId()));
        payload.put("username",user.getUsername());
        payload.put("headUrl",user.getHeadUrl());
        payload.put("userType",String.valueOf(user.getUserType()));

        String token = JWTUtils.getToken(payload);
        response.addHeader("token",token);

        return JSONUtils.getJSONString(0, "登录成功");
    }

    /**
     * 注册功能，默认用本地的头像，密码进行salt加密
     * @param username
     * @param password
     * @return
     */
    public String register(String username, String password) {
        if (userMapper.selectUserByName(username) != null) {
            return JSONUtils.getJSONString(-1, "用户名已存在");
        }
        User user = new User();
        String salt = UUID.randomUUID().toString().substring(0,6);

        user.setUsername(username);
        user.setSalt(salt);
        user.setPassword(MD5Utils.MD5(password + salt));
        // 设置默认头像
        user.setHeadUrl(FileUtils.HOST_PORT+"img/default.png");
        userMapper.addUser(user);

        return JSONUtils.getJSONString(0,"注册成功");
    }

    /**
     * 保存到文件中的图片只有图片名，保存到数据库中的文件是文件的全URL路径
     * @param file
     * @param session
     * @return
     */
    public String saveImage (MultipartFile file, HttpSession session) {
        // System.out.println(file.getOriginalFilename());// java.jpg
        int doPos = file.getOriginalFilename().lastIndexOf(".");
        if (doPos < 0) {
            return null;
        }
        String fileExt= file.getOriginalFilename().substring(doPos + 1).toLowerCase();
        // System.out.println(fileExt);// jpg
        if (!FileUtils.isImage(fileExt)) {
            return null;
        }
        String fileName = UUID.randomUUID().toString().replaceAll("-","") + "." + fileExt;
        try {
            File file1 = new File(FileUtils.IMAGE_DIR);
            if (!file1.exists()) {
                file1.mkdir();
            }
            Files.copy(file.getInputStream(),new File(FileUtils.IMAGE_DIR + fileName).toPath());
        } catch (IOException e) {
            logger.info("上传图片失败" + e.getMessage());
            e.printStackTrace();
        }
        String url = FileUtils.HOST_PORT + "getImage?fileName=" + fileName;
        User user = (User) session.getAttribute("user");
        user.setHeadUrl(url);
        userMapper.updateUser(user);
        return url;
    }

    public void getImage(String fileName, HttpServletResponse response) {
        try {
            Files.copy(Paths.get(FileUtils.IMAGE_DIR + fileName),response.getOutputStream());
        } catch (IOException e) {
            logger.error("图片读取错误" + e.getMessage());
        }
    }
}

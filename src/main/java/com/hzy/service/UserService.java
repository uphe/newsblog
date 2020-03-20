package com.hzy.service;

import com.hzy.controller.LoginController;
import com.hzy.mapper.UserMapper;
import com.hzy.pojo.User;
import com.hzy.utils.FileUtils;
import com.hzy.utils.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserMapper userMapper;

    public String register(String username, String password) {
        if (userMapper.selectUserByName(username) != null) {
            return "用户名已存在";
        }
        User user = new User();
        String salt = UUID.randomUUID().toString().substring(0,6);

        user.setUsername(username);
        user.setSalt(salt);
        user.setPassword(MD5Utils.MD5(password + salt));
        // user.setHeadUrl("http://127.0.0.1:8080//getImage?fileName=0ad32e8b0b414a959707f57686b566a8.jpg");
        // 设置默认头像
        user.setHeadUrl("https://raw.githubusercontent.com/uphe/myimage/master/default.png");
        userMapper.addUser(user);

        return null;
    }

    public String login(String username, String password) {

        User user = userMapper.selectUserByName(username);
        if (user == null) {
            return "该用户不存在";
        }
        if (!MD5Utils.MD5(password + user.getSalt()).equals(user.getPassword())) {
            return "密码错误";
        }
        return null;
    }

    public User selectUserByName(String username) {
        return userMapper.selectUserByName(username);
    }

    public User selectUserById(int userId) {
        return userMapper.selectUserById(userId);
    }

    /*
    * 保存到文件中的图片只有图片名，保存到数据库中的文件是文件的全URL路径
    * */
    public String saveImage (MultipartFile file) {
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
            Files.copy(file.getInputStream(),new File(FileUtils.IMAGE_DIR + fileName).toPath());
        } catch (IOException e) {
            logger.info("上传图片失败" + e.getMessage());
            e.printStackTrace();
        }
        return FileUtils.HOST_PORT + "getImage?fileName=" + fileName;
    }

    public void getImage(String fileName, HttpServletResponse response) {
        try {
            Files.copy(Paths.get(FileUtils.IMAGE_DIR + fileName),response.getOutputStream());
        } catch (IOException e) {
            logger.error("图片读取错误" + e.getMessage());
        }
    }
}

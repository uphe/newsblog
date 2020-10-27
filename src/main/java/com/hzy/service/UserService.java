package com.hzy.service;

import com.hzy.mapper.TokenMapper;
import com.hzy.mapper.UserMapper;
import com.hzy.pojo.Token;
import com.hzy.pojo.User;
import com.hzy.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Slf4j
public class UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenMapper tokenMapper;

    /**
     * 登录业务，并实现保存密码7天
     * @param response
     * @param username
     * @param password
     * @return
     */
    public String login(HttpSession session, String username, String password) {
        logger.info("执行登录功能");
        User user = userMapper.selectUserByName(username);
        if (user == null) {
            return JSONUtils.getJSONString(-1,"该用户不存在");
        }
        if (!MD5Utils.MD5(password + user.getSalt()).equals(user.getPassword())) {
            return JSONUtils.getJSONString(-1, "密码错误");
        }
        Token token = new Token();
        token.setUserId(user.getUserId());
        token.setToken(UUID.randomUUID().toString().replaceAll("-",""));

        Date date = new Date();
        date.setTime(new Date().getTime() + 1000 * 60 * 60 * 12 * 7);
        token.setExpired(date);

        tokenMapper.addToken(token);
        session.setAttribute("user",user);

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
        user.setHeadUrl(FileUtils.HOST_PORT + "getImage?fileName=default.png");
        userMapper.addUser(user);

        return JSONUtils.getJSONString(0,"注册成功");
    }


    public User selectUserById(int userId) {
        return userMapper.selectUserById(userId);
    }

    /**
     * 保存到文件中的图片只有图片名，保存到数据库中的文件是文件的全URL路径
     * @param file
     * @return
     */
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
            File file1 = new File(FileUtils.IMAGE_DIR);
            if (!file1.exists()) {
                file1.mkdir();
            }
            Files.copy(file.getInputStream(),new File(FileUtils.IMAGE_DIR + fileName).toPath());
        } catch (IOException e) {
            logger.info("上传图片失败" + e.getMessage());
            e.printStackTrace();
        }
        String fileUrl = FileUtils.HOST_PORT + "getImage?fileName=" + fileName;

        return fileUrl;
    }

    public void getImage(String fileName, HttpServletResponse response) {
        try {
            Files.copy(Paths.get(FileUtils.IMAGE_DIR + fileName),response.getOutputStream());
        } catch (IOException e) {
            logger.error("图片读取错误" + e.getMessage());
        }
    }

    public void updateUserByHeadUrl(String headUrl, HttpSession session) {

        User user = (User) session.getAttribute("user");
        user.setHeadUrl(headUrl);
        userMapper.updateUser(user);
    }
}

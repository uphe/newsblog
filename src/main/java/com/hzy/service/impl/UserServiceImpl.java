package com.hzy.service.impl;

import com.hzy.dto.UserDTO;
import com.hzy.dto.UserUpdateDTO;
import com.hzy.mapper.BlogMapper;
import com.hzy.mapper.FollowMapper;
import com.hzy.mapper.TokenMapper;
import com.hzy.mapper.UserMapper;
import com.hzy.pojo.Token;
import com.hzy.pojo.User;
import com.hzy.service.UserService;
import com.hzy.utils.*;
import com.hzy.vo.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private FollowMapper followMapper;

    @Override
    public BaseResult login(UserDTO userDTO, HttpServletResponse response, HttpSession session) {
        log.info("执行登录功能");

        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        User user = userMapper.selectUserByName(username);
        if (user == null) {
            return BaseResult.error("用户名不存在");
        }
        if (!MD5Utils.MD5(password + user.getSalt()).equals(user.getPassword())) {
            return BaseResult.error("密码错误");
        }
        Token token = tokenMapper.selectTokenByUserId(user.getUserId());
        if (token != null) {
            tokenMapper.updateToken(token.getToken(), DateUtil.afterSevenDay());
        } else {
            token = new Token();
            token.setUserId(user.getUserId());
            token.setToken(UUID.randomUUID().toString().replaceAll("-", ""));
            token.setExpired(DateUtil.afterSevenDay());
            tokenMapper.addToken(token);
        }
        response.setHeader("token", token.getToken());
        session.setAttribute("user", user);

        return BaseResult.ok(user);
    }


    @Override
    public String register(UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        if (userMapper.selectUserByName(username) != null) {
            return JSONUtils.getJSONString(-1, "用户名已存在");
        }

        User user = new User();
        String salt = UUID.randomUUID().toString().substring(0, 6);

        user.setUsername(username);
        user.setSalt(salt);
        user.setPassword(MD5Utils.MD5(password + salt));
        // 设置默认头像
        user.setHeadUrl(FileUtils.HOST_PORT + "getImage?fileName=default.png");
        userMapper.addUser(user);

        return JSONUtils.getJSONString(0, "注册成功");
    }

    public BaseResult isLogin(HttpServletRequest request) {
        log.info("执行了islogin");
        try {
            String token = request.getHeader("token");
            if (token == null) {
                return BaseResult.error("未登录");
            }
            if (tokenMapper.selectTokenByToken(token).getExpired().after(new Date())) {
                tokenMapper.updateToken(token, DateUtil.afterSevenDay());
                return BaseResult.ok();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return BaseResult.error("未登录");
    }

    @Override
    public BaseResult logout(HttpServletRequest request) {
        log.info("执行退出登录操作");
        String token = request.getHeader("token");
        tokenMapper.updateToken(token, new Date());
        return BaseResult.ok();

    }

    public User selectUserById(int userId) {
        return userMapper.selectUserById(userId);
    }

    @Override
    public BaseResult updateUserByUserId(UserUpdateDTO userUpdateDTO) {
        if (StringUtils.isEmpty(userUpdateDTO.getUsername()) &&
                StringUtils.isEmpty(userUpdateDTO.getOldPassword()) &&
                StringUtils.isEmpty(userUpdateDTO.getNewPassword()) &&
                StringUtils.isEmpty(userUpdateDTO.getHeadUrl())) {
            return BaseResult.error("参数不能全为空");
        }

        User user = userMapper.selectUserById(userUpdateDTO.getUserId());
        if (user == null) {
            return BaseResult.error("该用户不存在");
        }

        if (StringUtils.isNotEmpty(userUpdateDTO.getOldPassword()) &&
                StringUtils.isNotEmpty(userUpdateDTO.getNewPassword())) {
            if (!user.getPassword().equals(MD5Utils.MD5(userUpdateDTO.getOldPassword() + user.getSalt()))) {
                return BaseResult.error("原密码错误");
            } else {
                userUpdateDTO.setNewPassword(MD5Utils.MD5(userUpdateDTO.getNewPassword() + user.getSalt()));
            }
        } else if (StringUtils.isNotEmpty(userUpdateDTO.getOldPassword()) ||
                StringUtils.isNotEmpty(userUpdateDTO.getNewPassword())) {
            return BaseResult.error("密码不能为空");
        }
        userMapper.updateUserByUserId(userUpdateDTO);
        return BaseResult.ok("修改成功");
    }

    @Override
    public BaseResult selectUserInfoByUserId(int userId) {
        Map<String, Object> map = new HashMap<>();
        User user = userMapper.selectUserById(userId);
        int blogCountSum = blogMapper.selectBlogCountSumByUserId(userId);
        int hitCountSum = blogMapper.selectHitCountSumByUserId(userId);
        int followCount = followMapper.selectFollowCountByUserId(userId);
        int fansCount = followMapper.selectFansCountByUserId(userId);
        int likeCount = blogMapper.selectLikeCountSumByUserId(userId);
        map.put("user", user);
        map.put("blogCountSum", blogCountSum);
        map.put("hitCountSum", hitCountSum);
        map.put("followCount", followCount);
        map.put("fansCount", fansCount);
        map.put("likeCount", likeCount);

        return BaseResult.ok(map);
    }

    @Override
    public String saveImage(MultipartFile file) {
        // System.out.println(file.getOriginalFilename());// java.jpg
        int doPos = file.getOriginalFilename().lastIndexOf(".");
        if (doPos < 0) {
            return null;
        }
        String fileExt = file.getOriginalFilename().substring(doPos + 1).toLowerCase();
        // System.out.println(fileExt);// jpg
        if (!FileUtils.isImage(fileExt)) {
            return null;
        }
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
        try {
            File file1 = new File(FileUtils.IMAGE_DIR);
            if (!file1.exists()) {
                file1.mkdir();
            }
            Files.copy(file.getInputStream(), new File(FileUtils.IMAGE_DIR + fileName).toPath());
        } catch (IOException e) {
            log.info("上传图片失败" + e.getMessage());
            e.printStackTrace();
        }
        String fileUrl = FileUtils.HOST_PORT + "getImage?fileName=" + fileName;

        return fileUrl;
    }

    public void getImage(String fileName, HttpServletResponse response) {
        try {
            Files.copy(Paths.get(FileUtils.IMAGE_DIR + fileName), response.getOutputStream());
        } catch (IOException e) {
            log.error("图片读取错误" + e.getMessage());
        }
    }
}

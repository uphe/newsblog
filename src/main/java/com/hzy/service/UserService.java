package com.hzy.service;

import com.hzy.dto.UserDTO;
import com.hzy.pojo.User;
import com.hzy.vo.BaseResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface UserService {

    /**
     * 登录业务，并实现保存密码7天
     *
     * @param userDTO
     * @param response
     * @param session
     * @return
     */
    BaseResult login(UserDTO userDTO, HttpServletResponse response, HttpSession session);


    /**
     * 注册功能，默认用本地的头像，密码进行salt加密
     *
     * @param userDTO
     * @return
     */
    String register(UserDTO userDTO);

    BaseResult isLogin(HttpServletRequest request);

    BaseResult logout(HttpServletRequest request);

    User selectUserById(int userId);

    /**
     * 查询用户信息
     *
     * @param userId
     * @return
     */
    BaseResult selectUserInfoByUserId(int userId);

    /**
     * 保存到文件中的图片只有图片名，保存到数据库中的文件是文件的全URL路径
     *
     * @param file
     * @return
     */
    String saveImage(MultipartFile file);

    void getImage(String fileName, HttpServletResponse response);

    void updateUserByHeadUrl(String headUrl, HttpSession session);
}

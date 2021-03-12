package com.hzy.controller;

import com.hzy.dto.FollowDTO;
import com.hzy.service.FollowService;
import com.hzy.vo.BaseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Author: hzy
 * @Date: 2021/2/25
 */
@RestController
@Tag(name = "关注", description = "关注controller")
public class FollowController {
    @Autowired
    private FollowService followService;

    @PostMapping("/user/followUser")
    @Operation(summary = "关注用户，如果未关注，访问该接口即可关注，如果已关注，访问该接口取消关注")
    public BaseResult followUser(@RequestBody @Valid FollowDTO followDTO) {
        return followService.followUser(followDTO.getUserId(), followDTO.getFollowUserId());
    }

    @GetMapping("/user/selectFollowUserByUserId")
    @Operation(summary = "获取某个用户所关注的人")
    public BaseResult selectFollowUserByUserId(@NotNull int userId) {
        return followService.selectFollowUserByUserId(userId);
    }

    @GetMapping("/user/selectFansByUserId")
    @Operation(summary = "获取某个用户的粉丝")
    public BaseResult selectFansByUserId(@NotNull int userId) {
        return followService.selectFansByUserId(userId);
    }
}

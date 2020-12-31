package com.hzy.controller;

import com.hzy.pojo.Remind;
import com.hzy.pojo.User;
import com.hzy.service.RemindService;
import com.hzy.vo.ResponseVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/msg")
@Tag(name = "通知", description = "通知controller")
public class RemindController {
    @Autowired
    private RemindService remindService;

    @GetMapping("/like")
    @Operation(summary = "点赞通知")
    public ResponseVO<Integer> like(HttpSession session) {
        User user = (User) session.getAttribute("user");
        ResponseVO<Integer> responseVO = remindService.selectLikeRemindCountByToId(user.getUserId());
        return responseVO;
    }
    @GetMapping("/likedetail")
    @Operation(summary = "点赞通知详情")
    public ResponseVO likeDetail(HttpSession session) {

        User user = (User) session.getAttribute("user");
        remindService.updateLikeRemindByToId(user.getUserId());
        ResponseVO<List<Remind>> responseVO = remindService.selectLikeRemindByToId(user.getUserId());

        return responseVO;
    }

    @GetMapping("/comment")
    @Operation(summary = "评论通知")
    public ResponseVO<Integer> comment(HttpSession session) {
        User user = (User) session.getAttribute("user");
        ResponseVO<Integer> responseVO = remindService.selectCommentRemindCountByToId(user.getUserId());
        return responseVO;
    }

    @GetMapping("/commentdetail")
    @Operation(summary = "评论详情")
    public ResponseVO commentDetail(HttpSession session) {

        User user = (User) session.getAttribute("user");
        remindService.updateCommentRemindByToId(user.getUserId());
        ResponseVO<List<Remind>> responseVO = remindService.selectCommentRemindByToId(user.getUserId());

        return responseVO;
    }
}

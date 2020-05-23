package com.hzy.controller;

import com.hzy.pojo.Remind;
import com.hzy.pojo.User;
import com.hzy.service.RemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/msg")
public class RemindController {
    @Autowired
    private RemindService remindService;

    /**
     * 这里的to意思是通过控制器到达like.html页面
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/toLike")
    public String toLike(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Remind> likeRemindList = remindService.selectLikeRemindByToId(user.getUserId());
        model.addAttribute("user",user);
        model.addAttribute("likeRemindList",likeRemindList);
        return "msg/like";
    }
    @RequestMapping("/toLikeDetail")
    public String toNoticeDetail(int remindId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        remindService.updateRemindByRemindId(remindId);

        return "redirect:/msg/toLike";
    }

    @RequestMapping("/toComment")
    public String toComment(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Remind> commentRemindList = remindService.selectCommentRemindByToId(user.getUserId());
        model.addAttribute("user",user);
        model.addAttribute("commentRemindList", commentRemindList);
        return "msg/comment";
    }
    @RequestMapping("/toCommentDetail")
    public String toCommentDetail(int remindId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        remindService.updateRemindByRemindId(remindId);

        return "redirect:/msg/toComment";
    }

}

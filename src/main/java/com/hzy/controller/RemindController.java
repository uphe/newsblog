package com.hzy.controller;

import com.hzy.pojo.Notice;
import com.hzy.pojo.ReadNotice;
import com.hzy.pojo.Remind;
import com.hzy.pojo.User;
import com.hzy.service.RemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class RemindController {
    @Autowired
    private RemindService remindService;

    @RequestMapping("/toLike")
    public String toLike(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Remind> remindList = remindService.selectRemindByToId(user.getUserId());
        model.addAttribute("user",user);
        model.addAttribute("remindList",remindList);
        return "like";
    }
    @RequestMapping("/toLikeDetail")
    public String toNoticeDetail(int remindId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        remindService.updateRemindByRemindId(remindId);
//        ReadNotice readNotice = new ReadNotice();
//
//        readNotice.setNoticeId(noticeId);
//        readNotice.setUserId(user.getUserId());
//        Date date = new Date();
//        date.setTime(date.getTime() + 24*60*60*1000*7);
//        readNotice.setExpirationTime(date);
//
//        readNoticeService.addReadNotice(readNotice);
        return "redirect:/toLike";
    }

}

package com.hzy.controller;

import com.hzy.pojo.Blog;
import com.hzy.pojo.Notice;
import com.hzy.pojo.ReadNotice;
import com.hzy.pojo.User;
import com.hzy.service.NoticeService;
import com.hzy.service.ReadNoticeService;
import com.hzy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private ReadNoticeService readNoticeService;

    @RequestMapping("/toEditorNotice")
    public String toEditorNotice() {
        return "editorNotice";
    }

    @PostMapping(path = "/noticeEditormd")
    @ResponseBody
    public String noticeEditor(String title, String editormd,
                               Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(editormd) && user != null) {
            Notice notice = new Notice();
            notice.setNoticeTitle(title);
            notice.setNoticeContent(editormd);
            notice.setPublishTime(new Date());
            notice.setOperatorId(user.getUserId());
            Date date = new Date();
            date.setTime(date.getTime() + 1000*24*60*60*7);
            notice.setExpirationTime(date);
            noticeService.addNotice(notice);
            List<Notice> noticeList = noticeService.selectNoticeByUserId(user.getUserId());
            model.addAttribute("noticeList",noticeList);
        }
        return "redirect:/";
    }
    @RequestMapping("/toNotice")
    public String toNotice(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Notice> noticeList = noticeService.selectNoticeByUserId(user.getUserId());
        model.addAttribute("user",user);
        model.addAttribute("noticeList",noticeList);
        return "notice";
    }
    @RequestMapping("/toNoticeDetail")
    public String toNoticeDetail(int noticeId, HttpSession session) {
        User user = (User) session.getAttribute("user");

        ReadNotice readNotice = new ReadNotice();

        readNotice.setNoticeId(noticeId);
        readNotice.setUserId(user.getUserId());
        Date date = new Date();
        date.setTime(date.getTime() + 24*60*60*1000*7);
        readNotice.setExpirationTime(date);

        readNoticeService.addReadNotice(readNotice);
        return "redirect:/toNotice";
    }
}

package com.hzy.controller;

import com.hzy.pojo.Blog;
import com.hzy.pojo.Notice;
import com.hzy.pojo.ReadNotice;
import com.hzy.pojo.User;
import com.hzy.service.NoticeService;
import com.hzy.service.ReadNoticeService;
import com.hzy.utils.StringUtils;
import com.hzy.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private ReadNoticeService readNoticeService;

    @RequestMapping("/admin/notice")
    public ResponseVO<List<Notice>> notice(@RequestBody Blog blog, HttpSession session) {
        User user = (User) session.getAttribute("user");
        ResponseVO<List<Notice>> responseVO = new ResponseVO<>(-1,"发布公告失败");
        if (StringUtils.isNotEmpty(blog.getTitle()) && StringUtils.isNotEmpty(blog.getArticle())) {
            responseVO.setCode(0);
            responseVO.setMsg("发布公告成功");
            Notice notice = new Notice();
            notice.setNoticeTitle(blog.getTitle());
            notice.setNoticeContent(blog.getArticle());
            notice.setPublishTime(new Date());
            notice.setOperatorId(user.getUserId());
            Date date = new Date();
            date.setTime(date.getTime() + 1000*24*60*60*7);
            notice.setExpirationTime(date);
            noticeService.addNotice(notice);
        }
        return responseVO;
    }
    @RequestMapping("/msg/notice")
    public ResponseVO<Integer> toNotice(HttpSession session) {
        User user = (User) session.getAttribute("user");
        ResponseVO<Integer> responseVO = new ResponseVO<>(0,"查看成功");
        int count = noticeService.selectNoticeCountByUserId(user.getUserId());
        responseVO.setData(count);
        return responseVO;
    }
    @RequestMapping("/msg/noticedetail")
    public ResponseVO<List<Notice>> toNoticeDetail(HttpSession session) {
        User user = (User) session.getAttribute("user");
        ResponseVO<List<Notice>> responseVO = new ResponseVO<>(0,"查看公告列表");
        List<Notice> noticeList = noticeService.selectNoticeByUserId(user.getUserId());
        responseVO.setData(noticeList);
        return responseVO;
    }
    @RequestMapping("/msg/noticedetail/{noticeId}")
    public ResponseVO<Notice> toNoticeDetailLook(@PathVariable("noticeId") int noticeId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        ResponseVO<Notice> responseVO = new ResponseVO<>(0,"查看公告");
        ReadNotice readNotice = new ReadNotice();

        readNotice.setNoticeId(noticeId);
        readNotice.setUserId(user.getUserId());
        Date date = new Date();
        date.setTime(date.getTime() + 24*60*60*1000*7);
        readNotice.setExpirationTime(date);

        readNoticeService.addReadNotice(readNotice);
        Notice notice = noticeService.selectNoticeByNoticeId(noticeId);
        responseVO.setData(notice);
        return responseVO;
    }
}

package com.hzy.service;

import com.hzy.mapper.BlogMapper;
import com.hzy.mapper.CommentMapper;
import com.hzy.mapper.UserMapper;
import com.hzy.pojo.Blog;
import com.hzy.pojo.Comment;
import com.hzy.pojo.Remind;
import com.hzy.pojo.User;
import org.apache.ibatis.annotations.Arg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RemindService remindService;

    public int addComment(Comment comment) {
        int fromId = comment.getUserId();
        Blog blog = blogMapper.selectBlogById(comment.getBlogId());
        int toId = blog.getUserId();
        int blogId = blog.getBlogId();
        Remind remind = new Remind();
        remind.setRemindType(1);// 代表是评论通知
        remind.setRemindContent(userMapper.selectUserById(fromId).getUsername() + "评论了" +
                blog.getTitle() + ":" + comment.getContent());
        remind.setFromId(fromId);
        remind.setToId(toId);
        remind.setState(0);// 0表示未读
        remind.setBlogId(blogId);
        remind.setCreateDate(new Date());
        remindService.addRemind(remind);
        return commentMapper.addComment(comment);
    }
    public List<Comment> selectCommentByBlogId(int blogId){
        return commentMapper.selectCommentByBlogId(blogId);
    }
    public List<Comment> selectChildCommentByCommentId(int commentId) {

        // 遍历回复评论，并封装到list中
        List<Comment> list = commentMapper.selectChildCommentByCommentId(commentId);
        List<Comment> allList = new ArrayList<>();
        allList.addAll(list);

        // 这里用队列实现
        Queue<List<Comment>> listQueue = new LinkedBlockingQueue<>();
        listQueue.add(list);
        while (!listQueue.isEmpty()) {
            List<Comment> list1 = listQueue.remove();
            if (!list1.isEmpty()) {
                for (Comment comment1 : list1) {
                    List<Comment> list2 = commentMapper.selectChildCommentByCommentId(comment1.getCommentId());
                    if (!list2.isEmpty()) {
                        listQueue.add(list2);
                        allList.addAll(list2);
                    }
                }
            }
        }

//                // 感觉是遍历中的集合不能被合并
//                for (Comment comment1 : allList) {
//                    List<Comment> list1 = commentService.selectChildCommentByCommentId(comment1.getCommentId());
//                    if (!list1.isEmpty()) {
//                            allList.addAll(list1);
//
//                        }
//                    }
        return allList;

    }

}

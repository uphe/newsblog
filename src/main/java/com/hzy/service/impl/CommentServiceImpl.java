package com.hzy.service.impl;

import com.hzy.mapper.BlogMapper;
import com.hzy.mapper.CommentMapper;
import com.hzy.mapper.RemindMapper;
import com.hzy.mapper.UserMapper;
import com.hzy.pojo.Blog;
import com.hzy.pojo.Comment;
import com.hzy.pojo.Remind;
import com.hzy.pojo.User;
import com.hzy.service.CommentService;
import com.hzy.vo.CommentVO;
import com.hzy.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Arg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RemindMapper remindMapper;

    /**
     * 添加评论
     * @param comment
     * @return
     */
    public ResponseVO addComment(Comment comment) {
        log.info("执行评论功能");
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
        remindMapper.addRemind(remind);

        int result = commentMapper.addComment(comment);
        if (result > 0) {
            blogMapper.updateCommentCountByBlogId(commentMapper.selectCommentCountByBlogId(comment.getBlogId()), comment.getBlogId());
            return new ResponseVO(0,"评论成功");
        }
        return new ResponseVO(0,"评论失败");
    }

    public int selectCommentCountByBlogId(int blogId) {
        return commentMapper.selectCommentCountByBlogId(blogId);
    }
    public List<Comment> selectCommentByBlogId(int blogId){
        return commentMapper.selectCommentByBlogId(blogId);
    }

    public List<CommentVO> selectCommentVOByBlogId(int blogId,int offset,int limit) {
        List<CommentVO> commentVOS = commentMapper.selectParentCommentVOByBlogId(blogId, offset, limit);
        // 通过父评论找到子评论
        if (commentVOS != null) {
            // 此处遍历的评论集合属于一级评论，即评论博客的评论
            Map<String,Object> map = new LinkedHashMap<>();
            for (CommentVO commentVO : commentVOS) {
                // selectChildCommentVOByCommentId通过队列的形式把所有的回复封装到了allList里
                List<CommentVO> childCommentVOS = selectChildCommentVOByCommentId(commentVO.getCommentId());
                commentVO.setCommentVOS(childCommentVOS);
            }
        }
        return commentVOS;
    }


    /**
     * 通过父评论的id找到其所有子评论
     * @param commentId
     * @return
     */
    public List<CommentVO> selectChildCommentVOByCommentId(int commentId) {

        // 遍历回复评论，并封装到list中
        List<CommentVO> list = commentMapper.selectChildCommentVOByCommentId(commentId);

        List<CommentVO> allList = new ArrayList<>();
        allList.addAll(list);

        // 这里用队列实现
        Queue<List<CommentVO>> listQueue = new LinkedBlockingQueue<>();
        listQueue.add(list);
        while (!listQueue.isEmpty()) {
            List<CommentVO> list1 = listQueue.remove();
            if (!list1.isEmpty()) {
                for (CommentVO commentVO : list1) {
                    List<CommentVO> list2 = commentMapper.selectChildCommentVOByCommentId(commentVO.getCommentId());

                    if (!list2.isEmpty()) {
                        listQueue.add(list2);
                        allList.addAll(list2);
                    }
                }
            }
        }
        return allList;

    }
    public Comment selectCommentByCommentId(int commentId) {
        return commentMapper.selectCommentByCommentId(commentId);
    }

}

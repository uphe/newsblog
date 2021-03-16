package com.hzy.service.impl;

import com.hzy.dto.CommentDTO;
import com.hzy.mapper.BlogMapper;
import com.hzy.mapper.CommentMapper;
import com.hzy.mapper.RemindMapper;
import com.hzy.mapper.UserMapper;
import com.hzy.pojo.Blog;
import com.hzy.pojo.Comment;
import com.hzy.pojo.Remind;
import com.hzy.pojo.User;
import com.hzy.service.CommentService;
import com.hzy.vo.BaseResult;
import com.hzy.vo.CommentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: hzy
 * @Date: 2020/6/12
 */
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

    @Override
    public BaseResult addComment(Comment comment, HttpSession session) {

        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();

        comment.setCreateDate(new Date());
        comment.setUserId(userId);

        int fromId = comment.getUserId();
        Blog blog = blogMapper.selectBlogById(comment.getBlogId());
        int toId = blog.getUserId();
        int blogId = blog.getBlogId();
        Remind remind = new Remind();
        // 代表是评论通知
        remind.setRemindType(1);
        remind.setRemindContent(userMapper.selectUserById(fromId).getUsername() + "评论了" +
                blog.getTitle() + ":" + comment.getContent());
        remind.setFromId(fromId);
        remind.setToId(toId);
        // 0表示未读
        remind.setState(0);
        remind.setBlogId(blogId);
        remind.setCreateDate(new Date());
        remindMapper.addRemind(remind);

        int result = commentMapper.addComment(comment);
        if (result > 0) {
            blogMapper.updateCommentCountByBlogId(commentMapper.selectCommentCountByBlogId(comment.getBlogId()), comment.getBlogId());
            return BaseResult.ok("评论成功");
        }
        return BaseResult.error("评论失败");
    }

    @Override
    public BaseResult deleteCommentByCommentId(int commentId) {
        // 删除评论的时候，首先判断该评论是否有子评论，如果有子评论，就删除
        List<CommentVO> commentVOS = selectChildCommentVOByCommentId(commentId);
        Comment comment = commentMapper.selectCommentByCommentId(commentId);
        Blog blog = blogMapper.selectBlogById(comment.getBlogId());

        if (!CollectionUtils.isEmpty(commentVOS)) {
            commentVOS.forEach(commentVO -> {
                commentMapper.deleteCommentByCommentId(commentVO.getCommentId());
            });
            blog.setCommentCount(blog.getCommentCount() - commentVOS.size());
        }
        // 删除过子评论后，需要更新文章的评论数，即文章评论数减少了删除的条数
        blogMapper.updateCommentCountByBlogId(blog.getCommentCount() - 1, blog.getBlogId());

        // 最后删除评论
        commentMapper.deleteCommentByCommentId(commentId);
        return BaseResult.ok();
    }

    public int selectCommentCountByBlogId(int blogId) {
        return commentMapper.selectCommentCountByBlogId(blogId);
    }

    public List<Comment> selectCommentByBlogId(int blogId) {
        return commentMapper.selectCommentByBlogId(blogId);
    }

    @Override
    public BaseResult selectCommentVOByBlogId(CommentDTO commentDTO) {
        int blogId = commentDTO.getBlogId();
        int limit = commentDTO.getLimit();
        int offset = (commentDTO.getPage() - 1) * limit;
        List<CommentVO> commentVOS = commentMapper.selectParentCommentVOByBlogId(blogId, offset, limit);

        // 通过父评论找到子评论
        if (commentVOS != null) {
            // 此处遍历的评论集合属于一级评论，即评论博客的评论
            for (CommentVO commentVO : commentVOS) {
                // selectChildCommentVOByCommentId通过队列的形式把所有的回复封装到了allList里
                List<CommentVO> childCommentVOS = selectChildCommentVOByCommentId(commentVO.getCommentId());
                commentVO.setCommentVOS(childCommentVOS);
            }
        }
        return BaseResult.ok(commentVOS);
    }


    @Override
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

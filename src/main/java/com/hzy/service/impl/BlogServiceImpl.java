package com.hzy.service.impl;

import com.hzy.dto.BlogDTO;
import com.hzy.dto.BlogUpdateDTO;
import com.hzy.mapper.BlogMapper;
import com.hzy.mapper.LabelMapper;
import com.hzy.mapper.TypeMapper;
import com.hzy.mapper.VisitMapper;
import com.hzy.pojo.*;
import com.hzy.service.BlogService;
import com.hzy.utils.DateUtil;
import com.hzy.utils.FileUtils;
import com.hzy.utils.MarkDownUtil;
import com.hzy.utils.StringUtils;
import com.hzy.vo.BaseResult;
import com.hzy.vo.BlogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private VisitMapper visitMapper;

    private final String HIT_COUNT = "hitCount";
    private final int SUMMARY = 50;

    @Override
    public BaseResult getIndexBlogVO(int page, int limit, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = -1;
        if (user != null) {
            userId = user.getUserId();
        }
        List<BlogVO> blogVOS = blogMapper.selectIndexBlogVOByUserIdAndOffset(userId, limit * (page - 1), limit);


        setBlogVOSLikeCount(blogVOS);

        return BaseResult.ok(blogVOS);
    }

    @Override
    public BaseResult getRecommendBlogVO(int page, int limit, HttpSession session) {
        List<BlogVO> blogVOS = new ArrayList<>();

        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();

        int offset = limit * (page - 1);
        List<Map<String, Object>> maps = labelMapper.selectLabelByUserId(userId);
        int sum = 0;
        for (Map<String, Object> map : maps) {
            sum += Integer.valueOf(map.get("labelCount").toString());
        }
        for (Map<String, Object> map : maps) {
            String labelName = map.get("labelName").toString();
            List<BlogVO> blogVOList = blogMapper.selectBlogVOByLabelName(userId, labelName, offset, limit);
            int t = Integer.valueOf(map.get("labelCount").toString());
            blogVOS.addAll(blogVOList.subList(0, blogVOList.size() > (t * limit / sum) ? (t * limit / sum) : blogVOList.size()));
        }
        setBlogVOSLikeCount(blogVOS);
        return BaseResult.ok(blogVOS);
    }

    @Override
    public BaseResult getNewestBlogVO(int page, int limit, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = -1;
        if (user != null) {
            userId = user.getUserId();
        }
        List<BlogVO> blogVOS = blogMapper.selectNewestBlogVOByUserIdAndOffset(userId, 40 * (page - 1), 40);
        setBlogVOSLikeCount(blogVOS);
        return BaseResult.ok(blogVOS);
    }

    @Override
    public BaseResult getFollowBlogVO(int page, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();

        List<BlogVO> blogVOS = blogMapper.selectFollowBlogVOByUserIdAndOffset(userId, 40 * (page - 1), 40);
        setBlogVOSLikeCount(blogVOS);
        return BaseResult.ok(blogVOS);
    }

    @Override
    public BaseResult getTodayBlogVO() {
        // 先判断redis中是否存在
        SetOperations setOperations = redisTemplate.opsForSet();
        if (redisTemplate.hasKey(StringUtils.getTodayCommend())) {
            Set<BlogVO> members = setOperations.members(StringUtils.getTodayCommend());
            if (!members.isEmpty()) {
                log.info("执行了Redis今日推荐榜查询");
                List<BlogVO> blogVOS = new ArrayList<>();
                for (BlogVO member : members) {
                    blogVOS.add(member);
                }
                return BaseResult.ok(blogVOS);
            }
        }
        log.info("执行了MySQL今日推荐榜查询");
        List<BlogVO> blogVOS = blogMapper.selectTodayBlogVOByUserIdAndOffset(0, 10);

        for (int i = blogVOS.size() - 1; i >= 0; i--) {
            int result = blogVOS.get(i).getArticle().indexOf(FileUtils.GET_IMAGE_DIR);
            BlogVO blogVO = blogVOS.get(i);
            if (result >= 0) {
                String substring = blogVOS.get(i).getArticle().substring(result, result + FileUtils.GET_IMAGE_DIR.length() + FileUtils.FILENAME_LENGTH);
                blogVO.setBlogUrl(substring);
            } else {
                blogVO.setBlogUrl(blogVO.getHeadUrl());
            }
            setOperations.add(StringUtils.getTodayCommend(), blogVOS.get(i));

        }
        redisTemplate.expire(StringUtils.getTodayCommend(), 1, TimeUnit.DAYS);
        setBlogVOSLikeCount(blogVOS);
        return BaseResult.ok(blogVOS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult publishBlog(BlogDTO blogDTO, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();

        // 获取文章信息
        String title = blogDTO.getTitle();
        String summary = blogDTO.getSummary();
        String article = blogDTO.getArticle();
        List<String> labels = blogDTO.getLabels();
        List<String> types = blogDTO.getTypes();


        if (StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(article)) {
            Blog blog = new Blog();
            BeanUtils.copyProperties(blogDTO, blog);
            blog.setCreateDate(DateUtil.formatDate(new Date()));

            if (StringUtils.isEmpty(summary)) {
                if (article.length() > SUMMARY) {
                    blog.setSummary(article.substring(0, SUMMARY));
                } else {
                    blog.setSummary(article);
                }
            }
            blogMapper.addBlog(blog);

            // 如果分类非空，那么就插入分类
            if (!CollectionUtils.isEmpty(types)) {
                List<Type> typeList = new ArrayList<>();
                for (String s : types) {
                    Type type = new Type();
                    type.setBlogId(blog.getBlogId());
                    type.setUserId(userId);
                    type.setTypeName(s);
                    typeList.add(type);
                }
                typeMapper.addBatchType(typeList);
            }

            // 如果标签非空，那么就插入标签
            if (!CollectionUtils.isEmpty(labels)) {
                List<Label> labelList = new ArrayList<>();
                for (String s : labels) {
                    Label label = new Label();
                    label.setBlogId(blog.getBlogId());
                    label.setUserId(userId);
                    label.setLabelName(s);
                    labelList.add(label);
                }
                labelMapper.addBatchLabel(labelList);
            }
            return BaseResult.ok("发布成功");
        }
        log.info("发布文章失败");
        return BaseResult.error("发布文章失败");
    }

    @Override
    public BaseResult updateBlog(BlogUpdateDTO blogUpdateDTO, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        int blogId = blogUpdateDTO.getBlogId();

        Blog blog = new Blog();
        BeanUtils.copyProperties(blogUpdateDTO, blog);

        blogMapper.updateBlog(blog);

        // 修改文章的时候，会先删除标签，然后再新增标签
        labelMapper.deleteLabelByBlogId(blogId);
        List<Label> labels = new ArrayList<>();
        blogUpdateDTO.getLabels().forEach(labelName -> {
            Label label = new Label();

            label.setUserId(userId);
            label.setLabelName(labelName);
            label.setBlogId(blogId);

            labels.add(label);
        });
        labelMapper.addBatchLabel(labels);

        // 修改文章的时候，先删除分类，再新增分类
        typeMapper.deleteTypeByBlogId(blogId);
        List<Type> types = new ArrayList<>();
        blogUpdateDTO.getTypes().forEach(typeName -> {
            Type type = new Type();

            type.setTypeName(typeName);
            type.setUserId(userId);
            type.setBlogId(blogId);

            types.add(type);
        });
        typeMapper.addBatchType(types);

        return BaseResult.ok("修改成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult deleteBlogById(int blogId) {
        // 删除文章的时候，需要同时删除文章所属分类和标签
        typeMapper.deleteTypeByBlogId(blogId);
        labelMapper.deleteLabelByBlogId(blogId);

        blogMapper.deleteBlogById(blogId);

        return BaseResult.ok("删除成功");
    }

    @Override
    public BaseResult getBlogVOByBlogId(int blogId, HttpSession session) {

        User user = (User) session.getAttribute("user");
        // 用户第一次点击该文章，那么该文章的点击量加1
        if (user != null && visitMapper.selectVisitByUserIdAndBlogId(user.getUserId(), blogId) == null) {
            Visit visit = new Visit();
            visit.setUserId(user.getUserId());
            visit.setBlogId(blogId);

            visitMapper.addVisit(visit);

            blogMapper.updateHitCountByBlogId(blogId);
        }
        BlogVO blogVO = blogMapper.selectBlogVOByBlogId(blogId);
        List<String> types = typeMapper.selectTypeNameByBlogId(blogId);
        List<String> labels = labelMapper.selectLabelNameByBlogId(blogId);
        blogVO.setTypes(types);
        blogVO.setLabels(labels);
        // 下面是从数据库中获取到博客，然后转化为html传到前端
        String markdownString = blogVO.getArticle();
        String html = MarkDownUtil.mdToHtml(markdownString);
        blogVO.setArticle(html);

        return BaseResult.ok(blogVO);
    }

    @Override
    public BaseResult getBlogVOByUserIdAndOffset(int userId, int offset, int limit, String sortName) {
        List<BlogVO> blogVOS;
        if (HIT_COUNT.equals(sortName)) {
            blogVOS = blogMapper.selectBlogVOByUserIdSortHitCount(userId, offset, limit);
        } else {
            // 默认是时间排序
            blogVOS = blogMapper.selectBlogVOByUserIdAndOffset(userId, offset, limit);
        }

        return BaseResult.ok(blogVOS);
    }

    @Override
    public BaseResult getBlogVoByTypeNameAndOffset(String typeName, int page, int limit) {
        List<BlogVO> blogVOS = blogMapper.selectBlogVoByTypeNameAndOffset(typeName, limit * (page - 1), limit);
        return BaseResult.ok(blogVOS);
    }

    @Override
    public int selectCommentCountByBlogId(int blogId) {
        return blogMapper.selectCommentCountByBlogId(blogId);
    }

    @Override
    public int updateCommentCountByBlogId(int commentCount, int blogId) {
        return blogMapper.updateCommentCountByBlogId(commentCount, blogId);
    }

    @Override
    public int updateHitCountByBlogId(int blogId) {
        return blogMapper.updateHitCountByBlogId(blogId);
    }

    @Override
    public int updateLikeCountByBlogId(int likeCount, int blogId) {
        return blogMapper.updateLikeCountByBlogId(likeCount, blogId);
    }

    @Override
    public int selectHitCountSumByUserId(int userId) {
        return blogMapper.selectHitCountSumByUserId(userId);
    }

    @Override
    public int selectBlogCountSumByUserId(int userId) {
        return blogMapper.selectBlogCountSumByUserId(userId);
    }

    @Override
    public int selectLikeCountSumByUserId(int userId) {
        return blogMapper.selectLikeCountSumByUserId(userId);
    }

    /**
     * 在查询出来的博客中，对其点赞量进行更新，更新为缓存中的最新值
     *
     * @param blogVOS
     */
    private void setBlogVOSLikeCount(List<BlogVO> blogVOS) {
        for (BlogVO blogVO : blogVOS) {
            SetOperations setOperations = redisTemplate.opsForSet();
            String likeKey = StringUtils.getLikeKey(blogVO.getBlogId());
            if (likeKey != null) {
                Set members = setOperations.members(likeKey);
                if (members != null) {
                    blogVO.setLikeCount(members.size());
                }
            }
        }
    }
}

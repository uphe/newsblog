package com.hzy.service;

import com.hzy.dto.BlogDTO;
import com.hzy.mapper.BlogMapper;
import com.hzy.mapper.LabelMapper;
import com.hzy.mapper.TypeMapper;
import com.hzy.pojo.Blog;
import com.hzy.pojo.Label;
import com.hzy.pojo.Type;
import com.hzy.pojo.User;
import com.hzy.utils.DateUtil;
import com.hzy.utils.FileUtils;
import com.hzy.utils.MarkDownUtil;
import com.hzy.utils.StringUtils;
import com.hzy.vo.BaseResult;
import com.hzy.vo.BlogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private TypeMapper typeMapper;

    /**
     * 首页，暂时是根据一年以内的优文推荐，根据点赞*100+访问排序
     *
     * @param page
     * @param session
     * @return
     */
    public BaseResult getIndexBlogVO(int page, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = -1;
        if (user != null) {
            userId = user.getUserId();
        }
        List<BlogVO> blogVOS = blogMapper.selectIndexBlogVOByUserIdAndOffset(userId, 20 * (page - 1), 20);
        setBlogVOSLikeCount(blogVOS);

        return BaseResult.ok(blogVOS);
    }

    /**
     * 内容推荐算法，根据用户个人喜欢，进行个性化的推荐
     * 具体实现，首先查出用户浏览的标签进行排序，选出前5个
     * 然后对这5个标签进行查询，每个标签查出40个最近优文
     * 然后通过权重对查出的所有文章进行筛选，最后展示
     *
     * @param page
     * @param session
     * @return
     */
    public BaseResult getRecommendBlogVO(int page, HttpSession session) {
        List<BlogVO> blogVOS = new ArrayList<>();

        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();

        int offset = 40 * (page - 1);
        int limit = 40;
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


    /**
     * 最新的文章，根据创建时间进行的排序
     *
     * @param page
     * @param session
     * @return
     */
    public BaseResult getNewestBlogVO(int page, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = -1;
        if (user != null) {
            userId = user.getUserId();
        }
        List<BlogVO> blogVOS = blogMapper.selectNewestBlogVOByUserIdAndOffset(userId, 40 * (page - 1), 40);
        setBlogVOSLikeCount(blogVOS);
        return BaseResult.ok(blogVOS);
    }

    /**
     * 首页中用户关注的榜单
     *
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    public BaseResult getFollowBlogVO(int page, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();

        List<BlogVO> blogVOS = blogMapper.selectFollowBlogVOByUserIdAndOffset(userId, 40 * (page - 1), 40);
        setBlogVOSLikeCount(blogVOS);
        return BaseResult.ok(blogVOS);
    }

    /**
     * 今日推荐你文章，即是昨天的好文，根据点赞*100+访问进行排序
     *
     * @param offset
     * @param limit
     * @return
     */
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
        List<BlogVO> blogVOS = blogMapper.selectTodayBlogVOByUserIdAndOffset(0, 40);

        int t = 0;
        // 反向for循环，把不符合的删除
        for (int i = blogVOS.size() - 1; i >= 0; i--) {
            int result = blogVOS.get(i).getArticle().indexOf(FileUtils.GET_IMAGE_DIR);
            if (result >= 0) {
                String substring = blogVOS.get(i).getArticle().substring(result, result + FileUtils.GET_IMAGE_DIR.length() + FileUtils.FILENAME_LENGTH);
                blogVOS.get(i).setHeadUrl(substring);
                t++;
                setOperations.add(StringUtils.getTodayCommend(), blogVOS.get(i));
                if (t == 10) {
                    break;
                }
            } else {
                blogVOS.remove(i);
            }
        }
        redisTemplate.expire(StringUtils.getTodayCommend(), 1, TimeUnit.DAYS);
        setBlogVOSLikeCount(blogVOS);
        return BaseResult.ok(blogVOS);
    }


    /**
     * 发布博客，同时一份放入到es，并以标题进行分词存储
     *
     * @param blogDTO
     * @param session
     * @return
     */
    public BaseResult publishBlog(BlogDTO blogDTO, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();

        String title = blogDTO.getTitle();
        String summary = blogDTO.getSummary();
        String article = blogDTO.getArticle();
        List<String> labels = blogDTO.getLabels();
        List<String> types = blogDTO.getTypes();

        if (StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(article)) {
            Blog blog = new Blog();
            blog.setArticle(article);
            blog.setCreateDate(DateUtil.formatDate(new Date()));
            if (StringUtils.isNotEmpty(summary)) {
                blog.setSummary(summary);
            } else {
                if (article.length() > 50) {
                    blog.setSummary(article.substring(0, 50));
                } else {
                    blog.setSummary(article);
                }
            }
            blog.setTitle(title);
            blog.setUserId(userId);
            blogMapper.addBlog(blog);

            if (types != null && types.size() > 0) {
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
            if (labels != null && labels.size() > 0) {
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
            return BaseResult.ok();
        }
        log.info("发布文章失败");
        return BaseResult.error("发布文章失败");
    }

    /**
     * 通过id查询某一篇博客，即是获取文章详情
     *
     * @param blogId
     * @return
     */
    public BaseResult getBlogVOByUserId(int blogId, HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user != null) {
            blogMapper.updateHitCountByBlogId(blogId);// 点击量加1
        }
        BlogVO blogVO = blogMapper.selectBlogVOByBlogId(blogId);

        Map<String, Object> map = new HashMap<>();
        // 下面是从数据库中获取到博客，然后转化为html传到前端
        String markdownString = blogVO.getArticle();
        String html = MarkDownUtil.mdToHtml(markdownString);
        blogVO.setArticle(html);

        return BaseResult.ok(blogVO);
    }

    /**
     * 查询某个用户的文章
     *
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    public BaseResult getBlogVOByUserIdAndOffset(int userId, int offset, int limit) {
        List<BlogVO> blogVOS = blogMapper.selectBlogVOByUserIdAndOffset(userId, offset, limit);
        return BaseResult.ok(blogVOS);
    }


    /**
     * 通过博客id查询评论数
     *
     * @param blogId
     * @return
     */
    public int selectCommentCountByBlogId(int blogId) {
        return blogMapper.selectCommentCountByBlogId(blogId);
    }

    /**
     * 通过博客id更新评论数
     *
     * @param commentCount
     * @param blogId
     * @return
     */
    public int updateCommentCountByBlogId(int commentCount, int blogId) {
        return blogMapper.updateCommentCountByBlogId(commentCount, blogId);
    }

    /**
     * 通过博客id更新点击量
     *
     * @param blogId
     * @return
     */
    public int updateHitCountByBlogId(int blogId) {
        return blogMapper.updateHitCountByBlogId(blogId);
    }

    /**
     * 通过博客id更新点赞数
     *
     * @param likeCount
     * @param blogId
     * @return
     */
    public int updateLikeCountByBlogId(int likeCount, int blogId) {
        return blogMapper.updateLikeCountByBlogId(likeCount, blogId);
    }

    /**
     * 通过用户id查询用户的总访问量
     *
     * @param userId
     * @return
     */
    public int selectHitCountSumByUserId(int userId) {
        return blogMapper.selectHitCountSumByUserId(userId);
    }

    /**
     * 通过用户id查询用户的总文章数
     *
     * @param userId
     * @return
     */
    public int selectBlogCountSumByUserId(int userId) {
        return blogMapper.selectBlogCountSumByUserId(userId);
    }

    /**
     * 通过用户id查询用户的获赞总数
     *
     * @param userId
     * @return
     */
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

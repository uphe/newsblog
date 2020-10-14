package com.hzy.service;

import com.alibaba.fastjson.JSON;
import com.hzy.mapper.BlogMapper;
import com.hzy.mapper.LabelMapper;
import com.hzy.mapper.TypeMapper;
import com.hzy.mapper.UserMapper;
import com.hzy.pojo.Blog;
import com.hzy.pojo.Label;
import com.hzy.pojo.Type;
import com.hzy.pojo.User;
import com.hzy.utils.DateUtil;
import com.hzy.utils.FileUtils;
import com.hzy.utils.JSONUtils;
import com.hzy.utils.StringUtils;
import com.hzy.vo.BlogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private ElasticSearchService elasticSearchService;

    /**
     * 首页，暂时是根据一年以内的优文推荐，根据点赞*100+访问排序
     * @param offset
     * @param limit
     * @return
     */
    public List<BlogVO> getIndexBlogVO(int offset, int limit) {
        List<BlogVO> blogVOS = blogMapper.selectIndexBlogVOByUserIdAndOffset(offset, limit);
        return blogVOS;
    }

    /**
     * 最新的文章，根据创建时间进行的排序
     * @param offset
     * @param limit
     * @return
     */
    public List<BlogVO> getNewestBlogVO(int offset, int limit) {
        List<BlogVO> blogVOS = blogMapper.selectNewestBlogVOByUserIdAndOffset(offset, limit);
        return blogVOS;
    }

    /**
     * 今日推荐你文章，即是昨天的好文，根据点赞*100+访问进行排序
     * @param offset
     * @param limit
     * @return
     */
    public List<BlogVO> getTodayBlogVO(int offset, int limit) {
        limit += 30;
        List<BlogVO> blogVOS = blogMapper.selectTodayBlogVOByUserIdAndOffset(offset, limit);

        int t = 0;
        // 反向for循环，把不符合的删除
        for (int i = blogVOS.size() - 1; i >= 0; i--) {
            int result = blogVOS.get(i).getArticle().indexOf(FileUtils.GET_IMAGE_DIR);
            if (result >= 0) {
                String substring = blogVOS.get(i).getArticle().substring(result, result + FileUtils.GET_IMAGE_DIR.length() + FileUtils.FILENAME_LENGTH);
                blogVOS.get(i).setHeadUrl(substring);
                t ++;
                if (t == 10) {
                    break;
                }
            } else {
                blogVOS.remove(i);
            }
        }
        return blogVOS;
    }


    public List<BlogVO> getFollowBlogVO(int userId, int offset, int limit) {
        List<BlogVO> blogVOS = blogMapper.selectFollowBlogVOByUserIdAndOffset(userId, offset, limit);
        return blogVOS;
    }
    /**
     * 内容推荐算法，根据用户个人喜欢，进行个性化的推荐
     * 具体实现，首先查出用户浏览的标签进行排序，选出前5个
     * 然后对这5个标签进行查询，每个标签查出40个最近优文
     * 然后通过权重对查出的所有文章进行筛选，最后展示
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    public List<BlogVO> getRecommendBlogVO(int userId, int offset, int limit) {
        List<BlogVO> blogVOS = new ArrayList<>();

        List<Map<String, Object>> maps = labelMapper.selectLabelByUserId(userId);
        int sum = 0;
        for (Map<String, Object> map : maps) {
            sum += Integer.valueOf(map.get("labelCount").toString());
        }
        for (Map<String, Object> map : maps) {
            String labelName = map.get("labelName").toString();
            List<BlogVO> blogVOList = blogMapper.selectBlogVOByLabelName(labelName, 0, 40);
            int t = Integer.valueOf(map.get("labelCount").toString());
            blogVOS.addAll(blogVOList.subList(0,blogVOList.size() > (t * limit / sum) ? (t * limit / sum) : blogVOList.size()));
        }
        return blogVOS;
    }



    /**
     * 发布博客，同时一份放入到es，并以标题进行分词存储
     * @param userId
     * @param blogVO
     * @return
     */
    public String publishBlog(int userId, BlogVO blogVO) {

        String title = blogVO.getTitle();
        String summary = blogVO.getSummary();
        String article = blogVO.getArticle();
        List<String> labels = blogVO.getLabels();
        List<String> types = blogVO.getTypes();

        if (StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(article)) {
            Blog blog = new Blog();
            blog.setArticle(article);
            blog.setCreateDate(DateUtil.formatDate(new Date()));
            if (StringUtils.isNotEmpty(summary)) {
                blog.setSummary(summary);
            } else {
                if (article.length() > 50) {
                    blog.setSummary(article.substring(0,50));
                } else {
                    blog.setSummary(article);
                }
            }
            blog.setTitle(title);
            blog.setUserId(userId);
            blogMapper.addBlog(blog);
            elasticSearchService.save(blog);

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
            return JSONUtils.getJSONString(0,"success");
        }
        return JSONUtils.getJSONString(-1,"error");
    }

    /**
     * 通过id查询某一篇博客，即是获取文章详情
     * @param blogId
     * @return
     */
    public BlogVO getBlogVOByUserId(int blogId) {
        return blogMapper.selectBlogVOByBlogId(blogId);
    }

    /**
     * 查询某个用户的文章
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    public List<BlogVO> selectBlogByUserIdAndOffset (int userId,int offset,int limit) {
        return blogMapper.selectBlogVOByUserIdAndOffset(userId,offset,limit);
    }



    /**
     * 通过博客id查询评论数
     * @param blogId
     * @return
     */
    public int selectCommentCountByBlogId(int blogId) {
        return blogMapper.selectCommentCountByBlogId(blogId);
    }

    /**
     * 通过博客id更新评论数
     * @param commentCount
     * @param blogId
     * @return
     */
    public int updateCommentCountByBlogId(int commentCount, int blogId) {
        return blogMapper.updateCommentCountByBlogId(commentCount,blogId);
    }

    /**
     * 通过博客id更新点击量
     * @param blogId
     * @return
     */
    public int updateHitCountByBlogId(int blogId) {
        return blogMapper.updateHitCountByBlogId(blogId);
    }

    /**
     * 通过博客id更新点赞数
     * @param likeCount
     * @param blogId
     * @return
     */
    public int updateLikeCountByBlogId(int likeCount,int blogId) {
        return blogMapper.updateLikeCountByBlogId(likeCount,blogId);
    }

    /**
     * 通过用户id查询用户的总访问量
     * @param userId
     * @return
     */
    public int selectHitCountSumByUserId(int userId) {
        return blogMapper.selectHitCountSumByUserId(userId);
    }

    /**
     * 通过用户id查询用户的总文章数
     * @param userId
     * @return
     */
    public int selectBlogCountSumByUserId(int userId) {
        return blogMapper.selectBlogCountSumByUserId(userId);
    }

    /**
     * 通过用户id查询用户的获赞总数
     * @param userId
     * @return
     */
    public int selectLikeCountSumByUserId(int userId) {
        return blogMapper.selectLikeCountSumByUserId(userId);
    }

}

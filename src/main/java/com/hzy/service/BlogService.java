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
import com.hzy.utils.JSONUtils;
import com.hzy.utils.StringUtils;
import com.hzy.vo.BlogVO;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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
     * 发布博客
     * @param blog
     * @return
     */
    public int addBlog(Blog blog) {
        return blogMapper.addBlog(blog);
    }

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

            if (types != null) {
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
            if (labels != null) {
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
     * 分页查询博客
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    public List<Blog> selectBlogByUserIdAndOffset (int userId,int offset,int limit) {
        return blogMapper.selectBlogByUserIdAndOffset(userId,offset,limit);
    }

    /**
     * 通过id查询某一篇博客
     * @param blogId
     * @return
     */
    public Blog selectBlogById(int blogId) {
        return blogMapper.selectBlogById(blogId);
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

    /**
     * 根据用户id获取博客，如果id为0，则获取全部博客
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    public List<BlogVO> getBlog(int userId, int offset, int limit) {
        List<Blog> blogs = blogMapper.selectBlogByUserIdAndOffset(userId, offset, limit);
        List<BlogVO> blogVOS = new ArrayList<>();
        for (Blog blog : blogs) {
            User user = userMapper.selectUserById(blog.getUserId());
            BlogVO blogVO = new BlogVO();

            blogVO.setUsername((user.getUsername()));
            blogVO.setHeadUrl(user.getHeadUrl());
            blogVO.setBlogId(blog.getBlogId());
            blogVO.setTitle(blog.getTitle());
            blogVO.setSummary(blog.getSummary());
            blogVO.setArticle(blog.getArticle());
            blogVO.setLikeCount(blog.getLikeCount());
            blogVO.setHitCount(blog.getHitCount());
            blogVO.setCommentCount(blog.getCommentCount());

            blogVOS.add(blogVO);
        }


        return blogVOS;
    }

    /**
     * 内容推荐算法，根据用户个人喜欢，进行个性化的推荐
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    public Map<String, Map<String, Object>> getPersonalizationBlog(int userId, int offset, int limit) {
        Map<String,Map<String,Object>> mapMap = new LinkedHashMap<>();

        List<Map<String, Object>> maps = labelMapper.selectLabelByUserId(userId);
        for (Map<String, Object> map : maps) {

        }
        return mapMap;
    }


}

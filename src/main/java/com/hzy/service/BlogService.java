package com.hzy.service;

import com.alibaba.fastjson.JSON;
import com.hzy.mapper.BlogMapper;
import com.hzy.mapper.LabelMapper;
import com.hzy.mapper.TypeMapper;
import com.hzy.mapper.UserMapper;
import com.hzy.pojo.Blog;
import com.hzy.pojo.Type;
import com.hzy.pojo.User;
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
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;
    private static final String INDEX_NAME = "blog_index";

    /**
     * 发布博客
     * @param blog
     * @return
     */
    public int addBlog(Blog blog) {
        return blogMapper.addBlog(blog);
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
    public Map<String, Map<String,Object>> getBlog(int userId, int offset, int limit) {
        List<Blog> blogList = blogMapper.selectBlogByUserIdAndOffset(userId, offset, limit);
        Map<String,Map<String,Object>> mapMap = new LinkedHashMap<>();
        int i = 0;
        for (Blog blog : blogList) {
            User user = userMapper.selectUserById(blog.getUserId());
            //HashMap是有无序的
            //LinkedHashMap 和 TreeMap 是有序的,存取顺序
            Map<String,Object> map = new HashMap<>();
            map.put("blog",blog);
            map.put("user",user);
            mapMap.put("map" + i++,map);
        }
        return mapMap;
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

    /**
     * 将博客保存到es
     * @param blog
     */
    public void save(Blog blog) {
        IndexRequest request = new IndexRequest(INDEX_NAME);
//        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));

        // 将数据放入请求json
        request.source(JSON.toJSONString(blog), XContentType.JSON);
        // 客户端发送请求
        try {
            client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<BlogVO> search(String msg) {
        SearchRequest request = new SearchRequest(INDEX_NAME);

        // 构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        // 精确查询
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "欢迎来");

        // 先进行分词，然后进行搜索
        MatchQueryBuilder termQueryBuilder = QueryBuilders.matchQuery("title", msg);

        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(10, TimeUnit.SECONDS));

        request.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<BlogVO> blogVOS = new LinkedList<>();

        for (SearchHit hit : searchResponse.getHits().getHits()) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            int userId =Integer.valueOf(sourceAsMap.get("userId").toString());
            User user = userMapper.selectUserById(userId);

            BlogVO blogVO = new BlogVO();
            blogVO.setBlogId(Integer.valueOf(sourceAsMap.get("blogId").toString()));
            blogVO.setArticle(sourceAsMap.get("article").toString());
            blogVO.setCommentCount(Integer.valueOf(sourceAsMap.get("commentCount").toString()));
            blogVO.setHitCount(Integer.valueOf(sourceAsMap.get("hitCount").toString()));
            blogVO.setLikeCount(Integer.valueOf(sourceAsMap.get("likeCount").toString()));
            blogVO.setTitle(sourceAsMap.get("title").toString());
            blogVO.setSummary(sourceAsMap.get("summary").toString());
            blogVO.setUsername(user.getUsername());
            blogVO.setHeadUrl(user.getHeadUrl());

            List<String> labelList = labelMapper.selectLabelNameByBlogId(blogVO.getBlogId());
            blogVO.setLabels(labelList);

            List<String> typeList = typeMapper.selectTypeNameByBlogId(blogVO.getBlogId());
            blogVO.setTypes(typeList);

            blogVOS.add(blogVO);
        }
        return blogVOS;
    }
}

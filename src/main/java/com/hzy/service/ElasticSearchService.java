package com.hzy.service;

import com.alibaba.fastjson.JSON;
import com.hzy.mapper.LabelMapper;
import com.hzy.mapper.TypeMapper;
import com.hzy.mapper.UserMapper;
import com.hzy.pojo.Blog;
import com.hzy.pojo.Type;
import com.hzy.pojo.User;
import com.hzy.vo.BaseResult;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ElasticSearchService {
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
     * 将博客保存到es
     * @param blog
     */
    public void save(Blog blog) {
        IndexRequest request = new IndexRequest(INDEX_NAME);
        // request.id("1");
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

    /**
     * 分词搜索博客，暂时是根据文章标题进行分词
     * @param msg
     * @return
     */
    public BaseResult search(String msg) {
        SearchRequest request = new SearchRequest(INDEX_NAME);

        // 构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 精确查询
        // TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "欢迎来");

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
        return BaseResult.ok(blogVOS);
    }
}

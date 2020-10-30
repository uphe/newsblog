package com.hzy;

import com.alibaba.fastjson.JSON;
import com.hzy.mapper.BlogMapper;
import com.hzy.mapper.LabelMapper;
import com.hzy.mapper.TokenMapper;
import com.hzy.pojo.Blog;
import com.hzy.pojo.User;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;

@SpringBootTest
class NewsblogApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;
    private static final String INDEX_NAME = "blog_index";

    @Test
    void getBlog() {
        Blog blog = blogMapper.selectBlogById(1);
        System.out.println(blog);
    }

    /**
     * 创建博客索引
     * @throws IOException
     */
    @Test
    void creatIndex() throws IOException {
        XContentBuilder mappings = JsonXContent.contentBuilder()
                .startObject()
                    .startObject("properties")
                        .startObject("blogId")
                            .field("type","integer")
                        .endObject()

                        .startObject("title")
                            .field("type","text")
                            .field("analyzer", "ik_max_word")
                        .endObject()

                        .startObject("article")
                            .field("type","text")
//                            .field("analyzer", "ik_max_word")
                        .endObject()

                        .startObject("summary")
                            .field("type","text")
//                            .field("analyzer", "ik_max_word")
                        .endObject()

                        .startObject("likeCount")
                            .field("type","integer")
                        .endObject()

                        .startObject("hitCount")
                            .field("type","integer")
                        .endObject()

                        .startObject("commentCount")
                            .field("type","integer")
                        .endObject()

                        .startObject("createDate")
                            .field("type","date")
                            .field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
                        .endObject()

                        .startObject("userId")
                            .field("type","integer")
                        .endObject()

                    .endObject()
                .endObject();
        // 创建请求
        CreateIndexRequest request = new CreateIndexRequest(INDEX_NAME)
                .mapping(mappings);
        // 执行请求
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);

        System.out.println(createIndexResponse);
    }



    /**
     * 创建用户索引
     * @throws IOException
     */
    @Test
    void creatUserIndex() throws IOException {
        XContentBuilder mappings = JsonXContent.contentBuilder()
                .startObject()
                .startObject("properties")

                .startObject("userId")
                .field("type","integer")
                .endObject()

                .startObject("username")
                .field("type","text")
                .field("analyzer", "ik_max_word")
                .endObject()

                .startObject("password")
                .field("type","text")
                .endObject()

                .startObject("salt")
                .field("type","text")
                .endObject()

                .startObject("headUrl")
                .field("type","text")
                .endObject()


                .startObject("userType")
                .field("type","integer")
                .endObject()

                .endObject()
                .endObject();
        // 创建请求
        CreateIndexRequest request = new CreateIndexRequest("user_index")
                .mapping(mappings);
        // 执行请求
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);

        System.out.println(createIndexResponse);
    }
}

package com.hzy;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hzy.mapper.BlogMapper;
import com.hzy.mapper.LabelMapper;
import com.hzy.pojo.Blog;
import com.hzy.pojo.User;
import com.hzy.utils.JWTUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.ElasticsearchClient;
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
import java.util.*;

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
    void contextLoads() {
        Map<String,String> map = new HashMap<>();
        map.put("userId","1");
        map.put("username","root");
        String token = JWTUtils.getToken(map);
        System.out.println(token);
    }

    @Test
    void myTest() {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("FFIEUHIH")).build();

        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MDI5MDA5MzAsInVzZXJJZCI6IjEiLCJ1c2VybmFtZSI6InJvb3QifQ.8dGTiJE0zqqZ7ARHCrqbNGcQURuGdNNilcsF6Dj7vyw");
        System.out.println(verify.getClaim("userId").asInt());
        System.out.println(verify.getClaim("username").asString());
    }

    @Test
    void blogTest() {
        System.out.println(labelMapper.selectLabelByUserId(1));
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


    /**
     * 将博客保存到es
     */
    @Test
    public void save() {
        Blog blog = new Blog();
        blog.setTitle("欢迎您");
        IndexRequest request = new IndexRequest(INDEX_NAME);
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
     * 将用户保存到es
     */
    @Test
    public void saveUser() {
        User user = new User();
        user.setUsername("欢迎您");
        user.setPassword("fdsf");
        user.setHeadUrl("gfdsg");
        user.setSalt("fdsaf");
        user.setUserId(2);
        user.setUserType(1);
        IndexRequest request = new IndexRequest("user_index");
        request.timeout(TimeValue.timeValueSeconds(1));

        // 将数据放入请求json
        request.source(JSON.toJSONString(user), XContentType.JSON);
        // 客户端发送请求
        try {
            client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

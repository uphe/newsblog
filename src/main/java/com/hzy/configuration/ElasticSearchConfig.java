package com.hzy.configuration;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置elasticsearch客户端，可以通过自动装配使用
 */
@Configuration
public class ElasticSearchConfig {
    @Value("${hzy.elasticsearch.hostname}")
    private String hostname;
    @Value("${hzy.elasticsearch.port}")
    private Integer port;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(hostname,port)));
        return client;
    }
}


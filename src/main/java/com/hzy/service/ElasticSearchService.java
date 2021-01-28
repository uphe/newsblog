package com.hzy.service;

import com.hzy.pojo.Blog;
import com.hzy.vo.BaseResult;

public interface ElasticSearchService {

    /**
     * 将博客保存到es
     *
     * @param blog
     */
    void save(Blog blog);

    /**
     * 分词搜索博客，暂时是根据文章标题进行分词
     *
     * @param msg
     * @return
     */
    BaseResult search(String msg, Integer page, Integer limit);
}

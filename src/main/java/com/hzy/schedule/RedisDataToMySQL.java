package com.hzy.schedule;

import com.hzy.service.BlogService;
import com.hzy.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class RedisDataToMySQL {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BlogService blogService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 定时任务，每两小时持久化一次
     */
    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void redisDataToMySQL() {

        logger.info("开始执行Redis数据持久化到MySQL任务");
        SetOperations setOperations = redisTemplate.opsForSet();
        Set blogIds = setOperations.members(StringUtils.getChangeKey());
        if (!blogIds.isEmpty()) {
            for (Object blogId : blogIds) {
                logger.info("执行中。。。");
                String likeKey = StringUtils.getLikeKey((Integer) blogId);
                blogService.updateLikeCountByBlogId(Math.toIntExact(setOperations.size(likeKey)),(Integer)blogId);
            }
            setOperations.pop(StringUtils.getChangeKey());
        }
        logger.info("结束执行Redis数据持久化到MySQL任务");
    }
}

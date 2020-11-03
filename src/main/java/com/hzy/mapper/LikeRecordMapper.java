package com.hzy.mapper;

import com.hzy.pojo.LikeRecord;
import com.hzy.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LikeRecordMapper {
    int addLikeRecord(LikeRecord likeRecord);
    int updateLikeRecord(@Param("likeRecordId") int likeRecordId,@Param("isLike") int isLike);
    int deleteLikeRecordById(int likeRecordId);
    LikeRecord isLike(@Param("userId") int userId, @Param("blogId") int blogId);
}

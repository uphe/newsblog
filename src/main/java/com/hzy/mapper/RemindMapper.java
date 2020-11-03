package com.hzy.mapper;

import com.hzy.pojo.Remind;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface RemindMapper {
    int addRemind(Remind remind);
    List<Remind> selectCommentRemindByToId(int toId);
    List<Remind> selectLikeRemindByToId(int toId);
    int selectLikeRemindCountByToId(int toId);
    int selectCommentRemindCountByToId(int toId);
    int updateRemindByRemindId(int remindId);
    int updateRemindByFromIdAndBlogIdAndRemindType(int fromId, int blogId, int remindType);
    int updateRemindByToId(int toId);
    int updateLikeRemindByToId(int toId);
    int updateCommentRemindByToId(int toId);
}

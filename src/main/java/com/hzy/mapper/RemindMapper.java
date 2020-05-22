package com.hzy.mapper;

import com.hzy.pojo.Remind;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface RemindMapper {
    int addRemind(Remind remind);
    List<Remind> selectRemindByToId(int toId);
    int updateRemindByRemindId(int remindId);
    int updateRemindByFromIdAndBlogIdAndRemindType(int fromId, int blogId, int remindType);
    /**
     * 全部已读
     * @param toId
     * @return
     */
    int updateRemindByToId(int toId);
}

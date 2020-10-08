package com.hzy.mapper;

import com.hzy.pojo.Label;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface LabelMapper {
    int addLabel(Label label);
    int addBatchLabel(List<Label> labels);
    List<Map<String, Object>> selectLabelByUserId(int userId);
    List<String> selectLabelNameByBlogId(int blogId);
}

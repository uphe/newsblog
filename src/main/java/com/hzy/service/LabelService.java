package com.hzy.service;

import com.hzy.mapper.LabelMapper;
import com.hzy.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LabelService {
    @Autowired
    private LabelMapper labelMapper;

    public int addLabel(Label label) {
        return labelMapper.addLabel(label);
    }
    public int addBatchLabel(List<Label> labels) {
        return labelMapper.addBatchLabel(labels);
    }
    public List<Map<String, Object>> selectLabelByUserId(int userId) {
        return labelMapper.selectLabelByUserId(userId);
    }
}

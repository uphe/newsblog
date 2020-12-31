package com.hzy.service;

import com.hzy.pojo.Label;

import java.util.List;
import java.util.Map;

public interface LabelService {

    int addLabel(Label label);

    int addBatchLabel(List<Label> labels);

    List<Map<String, Object>> selectLabelByUserId(int userId);
}

package com.hzy.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class JSONUtils {

    public static String getJSONString(int code,String msg) {
        // code为0正常
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("msg",msg);
        return json.toJSONString();
    }

    public static String getJSONString(int code, Map<String,Object> map) {
        // code为0正常
        JSONObject json = new JSONObject();
        json.put("code",code);
        // for (String key : map.keySet()) {
        //    json.put(key,map.get(key));
        // }
        // 该遍历推荐使用
        for (Map.Entry<String,Object> entry : map.entrySet()) {
            json.put(entry.getKey(),entry.getValue());
        }
        return json.toJSONString();
    }
}

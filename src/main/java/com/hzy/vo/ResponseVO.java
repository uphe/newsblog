package com.hzy.vo;

public class ResponseVO<T> {
    //状态码
    private Integer code;
    //描述信息
    private String msg;
    //响应数据-采用泛型表示可以接受通用的数据类型
    private T data;

    public ResponseVO() {

    }
    public ResponseVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

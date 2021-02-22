package com.hzy.vo;

/**
 * @Author: hzy
 * @Date: 2020/11/28
 */
public class BaseResult<T> {
    private static final Integer SUCCESS_CODE = 0;
    private static final Integer ERROR_CODE = -1;

    private Integer code;

    private String errorMessage;

    private Boolean success;

    private T data;

    private Integer total;

    public BaseResult() {
    }

    public BaseResult(Integer code, boolean success) {
        this.code = code;
        this.success = success;
    }

    public BaseResult(Integer code, String msg, boolean success) {
        this.code = code;
        this.errorMessage = msg;
        this.success = success;
    }

    public BaseResult(Integer code, T data, boolean success) {
        this.code = code;
        this.data = data;
        this.success = success;
    }

    public BaseResult(Integer code, String msg, T data, boolean success) {
        this.code = code;
        this.errorMessage = msg;
        this.data = data;
        this.success = success;
    }

    public static BaseResult ok(Object result) {
        return new BaseResult(SUCCESS_CODE, result, true);
    }

    public static BaseResult ok() {
        return new BaseResult(SUCCESS_CODE, true);
    }

    public static BaseResult error(Object result) {
        return new BaseResult(ERROR_CODE, result, false);
    }

    public static BaseResult error() {
        return new BaseResult(ERROR_CODE, false);
    }

    public static BaseResult error(String msg) {
        return new BaseResult(ERROR_CODE,msg, false);
    }

    public static Integer getSuccessCode() {
        return SUCCESS_CODE;
    }

    public static Integer getErrorCode() {
        return ERROR_CODE;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code=" + code +
                ", errorMessage='" + errorMessage + '\'' +
                ", success=" + success +
                ", data=" + data +
                ", total=" + total +
                '}';
    }
}

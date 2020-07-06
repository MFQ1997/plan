package ict.plan.core.bean;

import ict.plan.core.util.JsonUtil;

/*
* @Description: 这是返回的数据库反馈
* */
public class RestResponse<T> {

    private String message = "";
    private String code = "00";
    private T result;

    /**
     * 返回一个新的实例
     */
    public static <T> RestResponse<T> newInstance() {
        return new RestResponse<T>();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return JsonUtil.objToJson(this);
    }
}

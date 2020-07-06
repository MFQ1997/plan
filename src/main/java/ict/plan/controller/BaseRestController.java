package ict.plan.controller;

import ict.plan.core.bean.RestResponse;

/*
* @Name:BaseRestController
* @Description:这个是基础的Controller
* @Date:2019
* */

public class BaseRestController {

    /**
     * @Name:success()
     * @Description:这个是返回成功的信息
     * @param :T(泛型)
     */
    protected <T> RestResponse<T> success(T result) {
        //实例化封装好的信息类
        RestResponse<T> restResponse = RestResponse.newInstance();
        restResponse.setResult(result);
        return restResponse;
    }

    /*
    * @Name:fail()
    * @Description:这个是返回失败的信息
    * @Param:T(泛型)
    * */
    protected <T> RestResponse<T> fail(String message,String code,T result){
        //实例化封装好的信息类
        RestResponse<T> restResponse = RestResponse.newInstance();
        //设置提示信息
        restResponse.setMessage(message);
        //设置状态码
        restResponse.setCode(code);
        //设置返回结果
        restResponse.setResult(result);
        return restResponse;
    }

}

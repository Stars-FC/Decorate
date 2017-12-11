package com.ygc.estatedecoration.bean;

/**
 * Created by FC on 2017/12/11.
 * 基础返回两个参数
 */

public class BaseBean {


    /**
     * responseState : 1
     * msg : 发送成功
     * data : null
     */

    private String responseState;
    private String msg;
    private Object data;

    public String getResponseState() {
        return responseState;
    }

    public void setResponseState(String responseState) {
        this.responseState = responseState;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

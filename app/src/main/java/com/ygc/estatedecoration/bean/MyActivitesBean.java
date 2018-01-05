package com.ygc.estatedecoration.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by FC on 2017/12/18.
 * 我的活动
 */

public class MyActivitesBean {

    /**
     * responseState : 1
     * msg : 查询成功
     * data : [{"activity_id":2,"au_id":"9","title":"元旦快乐","start_time":"2017-12-18","end_time":"2017-12-30","place":"锦州市","introduce":"元旦活动，元旦活动，元旦活动","picture_url":"/pictures/2d0bab7a1369498ca563a3187b719681.jpg","money":0,"status":0,"top_flag":0,"create_time":"2017-12-18 14:42:17.0","userInfo":null}]
     */

    private String responseState;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * activity_id : 2
         * au_id : 9
         * title : 元旦快乐
         * start_time : 2017-12-18
         * end_time : 2017-12-30
         * place : 锦州市
         * introduce : 元旦活动，元旦活动，元旦活动
         * picture_url : /pictures/2d0bab7a1369498ca563a3187b719681.jpg
         * money : 0
         * status : 0
         * top_flag : 0
         * create_time : 2017-12-18 14:42:17.0
         * userInfo : null
         */

        private int activity_id;
        private String au_id;
        private String title;
        private String start_time;
        private String end_time;
        private String place;
        private String introduce;
        private String picture_url;
        private int money;
        private int status;
        private int top_flag;
        private String create_time;
        private Object userInfo;

        public int getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(int activity_id) {
            this.activity_id = activity_id;
        }

        public String getAu_id() {
            return au_id;
        }

        public void setAu_id(String au_id) {
            this.au_id = au_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getPicture_url() {
            return picture_url;
        }

        public void setPicture_url(String picture_url) {
            this.picture_url = picture_url;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getTop_flag() {
            return top_flag;
        }

        public void setTop_flag(int top_flag) {
            this.top_flag = top_flag;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public Object getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(Object userInfo) {
            this.userInfo = userInfo;
        }
    }
}

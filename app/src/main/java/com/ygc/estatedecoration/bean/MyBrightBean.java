package com.ygc.estatedecoration.bean;

import java.util.List;

/**
 * Created by FC on 2017/12/14.
 * 我的亮点
 */

public class MyBrightBean {

    /**
     * data : [{"us_id":1,"au_id":"2","us_title":"1","us_picture":"","create_time":"2017-12-06 16:20:40.0"},{"us_id":2,"au_id":"2","us_title":"1","us_picture":"","create_time":"2017-12-06 16:20:40.0"}]
     * au_id : 2
     */

    private String au_id;
    private List<DataBean> data;

    public String getAu_id() {
        return au_id;
    }

    public void setAu_id(String au_id) {
        this.au_id = au_id;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * us_id : 1
         * au_id : 2
         * us_title : 1
         * us_picture :
         * create_time : 2017-12-06 16:20:40.0
         */

        private int us_id;
        private String au_id;
        private String us_title;
        private String us_picture;
        private String create_time;

        public int getUs_id() {
            return us_id;
        }

        public void setUs_id(int us_id) {
            this.us_id = us_id;
        }

        public String getAu_id() {
            return au_id;
        }

        public void setAu_id(String au_id) {
            this.au_id = au_id;
        }

        public String getUs_title() {
            return us_title;
        }

        public void setUs_title(String us_title) {
            this.us_title = us_title;
        }

        public String getUs_picture() {
            return us_picture;
        }

        public void setUs_picture(String us_picture) {
            this.us_picture = us_picture;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}

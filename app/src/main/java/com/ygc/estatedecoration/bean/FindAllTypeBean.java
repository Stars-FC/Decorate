package com.ygc.estatedecoration.bean;

import java.util.List;

/**
 * Created by FC on 2018/1/2.
 * 用户端，找设计/找施工/找监理
 */

public class FindAllTypeBean {

    /**
     * responseState : 1
     * message : 查询成功
     * data : [{"cp_id":1,"au_id":"1","d_id":"50","title":"小户型改造","city":"沈阳","address":"亚泰花园","house_type":"田园","static_picture":"/pictures/1.jpg","dynamic_picture":"","r_id":1,"create_time":"2017-12-14 09:07:35.0","s_name":"小月的材料屋","s_logo":"/pictures/1.jpg","bid_num":0,"work_year":"1","s_province":"辽宁","s_city":"沈阳"}]
     * page : {"pageNow":1,"pageSize":20,"totalCount":1,"totalPageCount":1,"startPos":0,"hasFirst":false,"hasPre":false,"hasNext":false,"hasLast":false}
     */

    private String responseState;
    private String message;
    private PageBean page;
    private List<DataBean> data;

    public String getResponseState() {
        return responseState;
    }

    public void setResponseState(String responseState) {
        this.responseState = responseState;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PageBean {
        /**
         * pageNow : 1
         * pageSize : 20
         * totalCount : 1
         * totalPageCount : 1
         * startPos : 0
         * hasFirst : false
         * hasPre : false
         * hasNext : false
         * hasLast : false
         */

        private int pageNow;
        private int pageSize;
        private int totalCount;
        private int totalPageCount;
        private int startPos;
        private boolean hasFirst;
        private boolean hasPre;
        private boolean hasNext;
        private boolean hasLast;

        public int getPageNow() {
            return pageNow;
        }

        public void setPageNow(int pageNow) {
            this.pageNow = pageNow;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPageCount() {
            return totalPageCount;
        }

        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
        }

        public int getStartPos() {
            return startPos;
        }

        public void setStartPos(int startPos) {
            this.startPos = startPos;
        }

        public boolean isHasFirst() {
            return hasFirst;
        }

        public void setHasFirst(boolean hasFirst) {
            this.hasFirst = hasFirst;
        }

        public boolean isHasPre() {
            return hasPre;
        }

        public void setHasPre(boolean hasPre) {
            this.hasPre = hasPre;
        }

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        public boolean isHasLast() {
            return hasLast;
        }

        public void setHasLast(boolean hasLast) {
            this.hasLast = hasLast;
        }
    }

    public static class DataBean {
        /**
         * cp_id : 1
         * au_id : 1
         * d_id : 50
         * title : 小户型改造
         * city : 沈阳
         * address : 亚泰花园
         * house_type : 田园
         * static_picture : /pictures/1.jpg
         * dynamic_picture :
         * r_id : 1
         * create_time : 2017-12-14 09:07:35.0
         * s_name : 小月的材料屋
         * s_logo : /pictures/1.jpg
         * bid_num : 0
         * work_year : 1
         * s_province : 辽宁
         * s_city : 沈阳
         */

        private int cp_id;
        private String au_id;
        private String d_id;
        private String title;
        private String city;
        private String address;
        private String house_type;
        private String static_picture;
        private String dynamic_picture;
        private int r_id;
        private String create_time;
        private String s_name;
        private String s_logo;
        private int bid_num;
        private String work_year;
        private String s_province;
        private String s_city;

        public int getCp_id() {
            return cp_id;
        }

        public void setCp_id(int cp_id) {
            this.cp_id = cp_id;
        }

        public String getAu_id() {
            return au_id;
        }

        public void setAu_id(String au_id) {
            this.au_id = au_id;
        }

        public String getD_id() {
            return d_id;
        }

        public void setD_id(String d_id) {
            this.d_id = d_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getHouse_type() {
            return house_type;
        }

        public void setHouse_type(String house_type) {
            this.house_type = house_type;
        }

        public String getStatic_picture() {
            return static_picture;
        }

        public void setStatic_picture(String static_picture) {
            this.static_picture = static_picture;
        }

        public String getDynamic_picture() {
            return dynamic_picture;
        }

        public void setDynamic_picture(String dynamic_picture) {
            this.dynamic_picture = dynamic_picture;
        }

        public int getR_id() {
            return r_id;
        }

        public void setR_id(int r_id) {
            this.r_id = r_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getS_name() {
            return s_name;
        }

        public void setS_name(String s_name) {
            this.s_name = s_name;
        }

        public String getS_logo() {
            return s_logo;
        }

        public void setS_logo(String s_logo) {
            this.s_logo = s_logo;
        }

        public int getBid_num() {
            return bid_num;
        }

        public void setBid_num(int bid_num) {
            this.bid_num = bid_num;
        }

        public String getWork_year() {
            return work_year;
        }

        public void setWork_year(String work_year) {
            this.work_year = work_year;
        }

        public String getS_province() {
            return s_province;
        }

        public void setS_province(String s_province) {
            this.s_province = s_province;
        }

        public String getS_city() {
            return s_city;
        }

        public void setS_city(String s_city) {
            this.s_city = s_city;
        }
    }
}

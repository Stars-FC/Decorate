package com.ygc.estatedecoration.bean;

/**
 * Created by FC on 2017/12/26.
 * 我的店铺
 */

public class  MyStoreBean {

    /**
     * responseState : 1
     * msg : 查询成功
     * data : {"s_id":1,"au_id":"1","s_name":"小月的材料屋","s_logo":"/pictures/1.jpg","s_type":"","turnover":0,"bid_num":0,"introduce":"真诚服务","background_info":"","work_experience":"","work_year":"1","s_province":"辽宁","s_city":"沈阳","r_id":"1","create_time":"2017-12-07 16:52:19.0","applause_rate":"100","comprehensive_score":"5","rinfo":{"r_id":1,"r_name":"木工","r_picture":"/pictures/1.jpg","type":4}}
     */

    private String responseState;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * s_id : 1
         * au_id : 1
         * s_name : 小月的材料屋
         * s_logo : /pictures/1.jpg
         * s_type :
         * turnover : 0
         * bid_num : 0
         * introduce : 真诚服务
         * background_info :
         * work_experience :
         * work_year : 1
         * s_province : 辽宁
         * s_city : 沈阳
         * r_id : 1
         * create_time : 2017-12-07 16:52:19.0
         * applause_rate : 100
         * comprehensive_score : 5
         * rinfo : {"r_id":1,"r_name":"木工","r_picture":"/pictures/1.jpg","type":4}
         */

        private int s_id;
        private String au_id;
        private String s_name;
        private String s_logo;
        private String s_type;
        private int turnover;
        private int bid_num;
        private String introduce;
        private String background_info;
        private String work_experience;
        private String work_year;
        private String s_province;
        private String s_city;
        private String r_id;
        private String create_time;
        private String applause_rate;
        private String comprehensive_score;
        private RinfoBean rinfo;

        public int getS_id() {
            return s_id;
        }

        public void setS_id(int s_id) {
            this.s_id = s_id;
        }

        public String getAu_id() {
            return au_id;
        }

        public void setAu_id(String au_id) {
            this.au_id = au_id;
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

        public String getS_type() {
            return s_type;
        }

        public void setS_type(String s_type) {
            this.s_type = s_type;
        }

        public int getTurnover() {
            return turnover;
        }

        public void setTurnover(int turnover) {
            this.turnover = turnover;
        }

        public int getBid_num() {
            return bid_num;
        }

        public void setBid_num(int bid_num) {
            this.bid_num = bid_num;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getBackground_info() {
            return background_info;
        }

        public void setBackground_info(String background_info) {
            this.background_info = background_info;
        }

        public String getWork_experience() {
            return work_experience;
        }

        public void setWork_experience(String work_experience) {
            this.work_experience = work_experience;
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

        public String getR_id() {
            return r_id;
        }

        public void setR_id(String r_id) {
            this.r_id = r_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getApplause_rate() {
            return applause_rate;
        }

        public void setApplause_rate(String applause_rate) {
            this.applause_rate = applause_rate;
        }

        public String getComprehensive_score() {
            return comprehensive_score;
        }

        public void setComprehensive_score(String comprehensive_score) {
            this.comprehensive_score = comprehensive_score;
        }

        public RinfoBean getRinfo() {
            return rinfo;
        }

        public void setRinfo(RinfoBean rinfo) {
            this.rinfo = rinfo;
        }

        public static class RinfoBean {
            /**
             * r_id : 1
             * r_name : 木工
             * r_picture : /pictures/1.jpg
             * type : 4
             */

            private int r_id;
            private String r_name;
            private String r_picture;
            private int type;

            public int getR_id() {
                return r_id;
            }

            public void setR_id(int r_id) {
                this.r_id = r_id;
            }

            public String getR_name() {
                return r_name;
            }

            public void setR_name(String r_name) {
                this.r_name = r_name;
            }

            public String getR_picture() {
                return r_picture;
            }

            public void setR_picture(String r_picture) {
                this.r_picture = r_picture;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}

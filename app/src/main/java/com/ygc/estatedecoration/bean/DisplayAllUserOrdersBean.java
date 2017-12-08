package com.ygc.estatedecoration.bean;

import java.util.List;

public class DisplayAllUserOrdersBean {

    /**
     * responseStatus : 0
     * msg : 查询成功
     * obj : [{"mpo_id":2,"au_id":2,"mpo_number":"20170911142152","ua_name":"未设置","ua_mobile":"未设置","ua_adress":"未设置","guest_book":"","freight":10,"mpo_state":"1","logistics_mode":"0","order_time":"2017-09-10 14:21:52.0","payment_time":"2017-09-10 14:21:52.0","express_number":"","mpo_total":6000},{"mpo_id":3,"au_id":2,"mpo_number":"20170911142400","ua_name":"未设置","ua_mobile":"未设置","ua_adress":"未设置","guest_book":"","freight":10,"mpo_state":"2","logistics_mode":"0","order_time":"2017-09-11 14:24:00.0","payment_time":"2017-09-11 14:24:00.0","express_number":"","mpo_total":9400},{"mpo_id":4,"au_id":2,"mpo_number":"20170911142531","ua_name":"未设置","ua_mobile":"未设置","ua_adress":"未设置","guest_book":"","freight":10,"mpo_state":"3","logistics_mode":"0","order_time":"2017-09-11 14:25:31.0","payment_time":"2017-09-11 14:25:31.0","express_number":"","mpo_total":9400},{"mpo_id":5,"au_id":2,"mpo_number":"20170911142532","ua_name":"未设置","ua_mobile":"未设置","ua_adress":"未设置","guest_book":"","freight":10,"mpo_state":"4","logistics_mode":"0","order_time":"2017-09-11 14:25:32.0","payment_time":"2017-09-11 14:25:32.0","express_number":"","mpo_total":9400},{"mpo_id":6,"au_id":2,"mpo_number":"20170911232620","ua_name":"未设置","ua_mobile":"未设置","ua_adress":"未设置","guest_book":"","freight":10,"mpo_state":"1","logistics_mode":"0","order_time":"2017-09-11 23:26:20.0","payment_time":"2017-09-11 23:26:20.0","express_number":"","mpo_total":36000}]
     * obj1 : [[{"mog_id":4,"mpo_id":2,"c_id":2,"c_name":"君的思念","c_cover":"pictures/ExoticFlowers/user/7/4a3e16c064ac4e18b508a314f03b2a7c.png","c_describe":"紫罗兰50支","unit_price":2400,"mog_count":20,"evaluate_number":0,"add_evaluate":"","picture_one":"","picture_two":"","picture_three":""},{"mog_id":3,"mpo_id":2,"c_id":1,"c_name":"爱的诺言","c_cover":"pictures/ExoticFlowers/user/7/4a3e16c064ac4e18b508a314f03b2a7c.png","c_describe":"白玫瑰50支","unit_price":1200,"mog_count":10,"evaluate_number":0,"add_evaluate":"","picture_one":"","picture_two":"","picture_three":""}],[{"mog_id":6,"mpo_id":3,"c_id":2,"c_name":"君的思念","c_cover":"pictures/ExoticFlowers/user/7/4a3e16c064ac4e18b508a314f03b2a7c.png","c_describe":"紫罗兰50支","unit_price":5800,"mog_count":1,"evaluate_number":0,"add_evaluate":"","picture_one":"","picture_two":"","picture_three":""},{"mog_id":5,"mpo_id":3,"c_id":1,"c_name":"爱的诺言","c_cover":"pictures/ExoticFlowers/user/7/4a3e16c064ac4e18b508a314f03b2a7c.png","c_describe":"白玫瑰50支","unit_price":3600,"mog_count":1,"evaluate_number":0,"add_evaluate":"","picture_one":"","picture_two":"","picture_three":""}],[{"mog_id":8,"mpo_id":4,"c_id":2,"c_name":"君的思念","c_cover":"pictures/ExoticFlowers/user/7/4a3e16c064ac4e18b508a314f03b2a7c.png","c_describe":"紫罗兰50支","unit_price":5800,"mog_count":1,"evaluate_number":0,"add_evaluate":"","picture_one":"","picture_two":"","picture_three":""},{"mog_id":7,"mpo_id":4,"c_id":1,"c_name":"爱的诺言","c_cover":"pictures/ExoticFlowers/user/7/4a3e16c064ac4e18b508a314f03b2a7c.png","c_describe":"白玫瑰50支","unit_price":3600,"mog_count":1,"evaluate_number":0,"add_evaluate":"","picture_one":"","picture_two":"","picture_three":""}],[{"mog_id":10,"mpo_id":5,"c_id":2,"c_name":"君的思念","c_cover":"pictures/ExoticFlowers/user/7/4a3e16c064ac4e18b508a314f03b2a7c.png","c_describe":"紫罗兰50支","unit_price":5800,"mog_count":1,"evaluate_number":0,"add_evaluate":"","picture_one":"","picture_two":"","picture_three":""},{"mog_id":9,"mpo_id":5,"c_id":1,"c_name":"爱的诺言","c_cover":"pictures/ExoticFlowers/user/7/4a3e16c064ac4e18b508a314f03b2a7c.png","c_describe":"白玫瑰50支","unit_price":3600,"mog_count":1,"evaluate_number":0,"add_evaluate":"","picture_one":"","picture_two":"","picture_three":""}],[{"mog_id":12,"mpo_id":6,"c_id":2,"c_name":"君的思念","c_cover":"pictures/ExoticFlowers/user/7/4a3e16c064ac4e18b508a314f03b2a7c.png","c_describe":"紫罗兰50支","unit_price":2400,"mog_count":10,"evaluate_number":0,"add_evaluate":"","picture_one":"","picture_two":"","picture_three":""},{"mog_id":11,"mpo_id":6,"c_id":1,"c_name":"爱的诺言","c_cover":"pictures/ExoticFlowers/user/7/4a3e16c064ac4e18b508a314f03b2a7c.png","c_describe":"白玫瑰50支","unit_price":1200,"mog_count":10,"evaluate_number":0,"add_evaluate":"","picture_one":"","picture_two":"","picture_three":""}]]
     */

    private String responseStatus;
    private String msg;
    private List<ObjBean> obj;
    private List<List<Obj1Bean>> obj1;

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public List<List<Obj1Bean>> getObj1() {
        return obj1;
    }

    public void setObj1(List<List<Obj1Bean>> obj1) {
        this.obj1 = obj1;
    }

    public static class ObjBean {
        /**
         * mpo_id : 2
         * au_id : 2
         * mpo_number : 20170911142152
         * ua_name : 未设置
         * ua_mobile : 未设置
         * ua_adress : 未设置
         * guest_book :
         * freight : 10
         * mpo_state : 1
         * logistics_mode : 0
         * order_time : 2017-09-10 14:21:52.0
         * payment_time : 2017-09-10 14:21:52.0
         * express_number :
         * mpo_total : 6000
         */

        private int mpo_id;
        private int au_id;
        private String mpo_number;
        private String ua_name;
        private String ua_mobile;
        private String ua_adress;
        private String guest_book;
        private int freight;
        private String mpo_state;
        private String logistics_mode;
        private String order_time;
        private String payment_time;
        private String express_number;
        private int mpo_total;

        public int getMpo_id() {
            return mpo_id;
        }

        public void setMpo_id(int mpo_id) {
            this.mpo_id = mpo_id;
        }

        public int getAu_id() {
            return au_id;
        }

        public void setAu_id(int au_id) {
            this.au_id = au_id;
        }

        public String getMpo_number() {
            return mpo_number;
        }

        public void setMpo_number(String mpo_number) {
            this.mpo_number = mpo_number;
        }

        public String getUa_name() {
            return ua_name;
        }

        public void setUa_name(String ua_name) {
            this.ua_name = ua_name;
        }

        public String getUa_mobile() {
            return ua_mobile;
        }

        public void setUa_mobile(String ua_mobile) {
            this.ua_mobile = ua_mobile;
        }

        public String getUa_adress() {
            return ua_adress;
        }

        public void setUa_adress(String ua_adress) {
            this.ua_adress = ua_adress;
        }

        public String getGuest_book() {
            return guest_book;
        }

        public void setGuest_book(String guest_book) {
            this.guest_book = guest_book;
        }

        public int getFreight() {
            return freight;
        }

        public void setFreight(int freight) {
            this.freight = freight;
        }

        public String getMpo_state() {
            return mpo_state;
        }

        public void setMpo_state(String mpo_state) {
            this.mpo_state = mpo_state;
        }

        public String getLogistics_mode() {
            return logistics_mode;
        }

        public void setLogistics_mode(String logistics_mode) {
            this.logistics_mode = logistics_mode;
        }

        public String getOrder_time() {
            return order_time;
        }

        public void setOrder_time(String order_time) {
            this.order_time = order_time;
        }

        public String getPayment_time() {
            return payment_time;
        }

        public void setPayment_time(String payment_time) {
            this.payment_time = payment_time;
        }

        public String getExpress_number() {
            return express_number;
        }

        public void setExpress_number(String express_number) {
            this.express_number = express_number;
        }

        public int getMpo_total() {
            return mpo_total;
        }

        public void setMpo_total(int mpo_total) {
            this.mpo_total = mpo_total;
        }
    }

    public static class Obj1Bean {
        /**
         * mog_id : 4
         * mpo_id : 2
         * c_id : 2
         * c_name : 君的思念
         * c_cover : pictures/ExoticFlowers/user/7/4a3e16c064ac4e18b508a314f03b2a7c.png
         * c_describe : 紫罗兰50支
         * unit_price : 2400
         * mog_count : 20
         * evaluate_number : 0
         * add_evaluate :
         * picture_one :
         * picture_two :
         * picture_three :
         */

        private int mog_id;
        private int mpo_id;
        private int c_id;
        private String c_name;
        private String c_cover;
        private String c_describe;
        private int unit_price;
        private int mog_count;
        private int evaluate_number;
        private String add_evaluate;
        private String picture_one;
        private String picture_two;
        private String picture_three;

        public int getMog_id() {
            return mog_id;
        }

        public void setMog_id(int mog_id) {
            this.mog_id = mog_id;
        }

        public int getMpo_id() {
            return mpo_id;
        }

        public void setMpo_id(int mpo_id) {
            this.mpo_id = mpo_id;
        }

        public int getC_id() {
            return c_id;
        }

        public void setC_id(int c_id) {
            this.c_id = c_id;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getC_cover() {
            return c_cover;
        }

        public void setC_cover(String c_cover) {
            this.c_cover = c_cover;
        }

        public String getC_describe() {
            return c_describe;
        }

        public void setC_describe(String c_describe) {
            this.c_describe = c_describe;
        }

        public int getUnit_price() {
            return unit_price;
        }

        public void setUnit_price(int unit_price) {
            this.unit_price = unit_price;
        }

        public int getMog_count() {
            return mog_count;
        }

        public void setMog_count(int mog_count) {
            this.mog_count = mog_count;
        }

        public int getEvaluate_number() {
            return evaluate_number;
        }

        public void setEvaluate_number(int evaluate_number) {
            this.evaluate_number = evaluate_number;
        }

        public String getAdd_evaluate() {
            return add_evaluate;
        }

        public void setAdd_evaluate(String add_evaluate) {
            this.add_evaluate = add_evaluate;
        }

        public String getPicture_one() {
            return picture_one;
        }

        public void setPicture_one(String picture_one) {
            this.picture_one = picture_one;
        }

        public String getPicture_two() {
            return picture_two;
        }

        public void setPicture_two(String picture_two) {
            this.picture_two = picture_two;
        }

        public String getPicture_three() {
            return picture_three;
        }

        public void setPicture_three(String picture_three) {
            this.picture_three = picture_three;
        }
    }
}

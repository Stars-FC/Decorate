package com.ygc.estatedecoration.bean;

import java.util.List;

/**
 * Created by FC on 2017/12/15.
 * 用户明细
 */

public class UserBalanceOrderBean {

    /**
     * responseState : 1
     * msg : 查询成功
     * data : [{"ab_type":0,"total_price":10,"create_time":1513237490000,"ab_state":1,"pay_type":0,"au_id":1,"ab_id":"df527e3792ad45538cf3d708ea74e506"},{"ab_type":0,"total_price":10,"create_time":1513237487000,"ab_state":1,"pay_type":1,"au_id":1,"ab_id":"966a0267a37f4f128ab05be07b2983f7"},{"ab_type":0,"total_price":10,"create_time":1513237484000,"ab_state":1,"pay_type":0,"au_id":1,"ab_id":"951854a9ffe84aedac20669ad1de8198"},{"ab_type":0,"total_price":10,"create_time":1513237482000,"ab_state":1,"pay_type":0,"au_id":1,"ab_id":"a1192673e8b44b8aa3c981bd1d6106e1"}]
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

    public static class DataBean {
        /**
         * ab_type : 0
         * total_price : 10
         * create_time : 1513237490000
         * ab_state : 1
         * pay_type : 0
         * au_id : 1
         * ab_id : df527e3792ad45538cf3d708ea74e506
         */

        private int ab_type;
        private int total_price;
        private long create_time;
        private int ab_state;
        private int pay_type;
        private int au_id;
        private String ab_id;

        public int getAb_type() {
            return ab_type;
        }

        public void setAb_type(int ab_type) {
            this.ab_type = ab_type;
        }

        public int getTotal_price() {
            return total_price;
        }

        public void setTotal_price(int total_price) {
            this.total_price = total_price;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public int getAb_state() {
            return ab_state;
        }

        public void setAb_state(int ab_state) {
            this.ab_state = ab_state;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public int getAu_id() {
            return au_id;
        }

        public void setAu_id(int au_id) {
            this.au_id = au_id;
        }

        public String getAb_id() {
            return ab_id;
        }

        public void setAb_id(String ab_id) {
            this.ab_id = ab_id;
        }
    }
}

package com.ygc.estatedecoration.bean;

import java.util.List;

/**
 * Created by FC on 2018/1/2.
 * 用户端，找材料
 */

public class FindMaterialBean {

    /**
     * responseState : 1
     * msg : 查询成功
     * data : [{"cm_id":1,"s_id":"1","r_id":"1","cm_name":"户外塑木木塑地板","cm_price":"7.97","cover_picture":"/pictures/1.jpg,/pictures/2.jpg","detail_picture":"/pictures/1.jpg,/pictures/2.jpg","remark":"买就送装饰鹅暖石","sale_num":0,"top_flag":0,"del_flag":0,"storeInfo":null,"specList":null,"saleSpecList":null,"skuList":null,"evaluateList":null,"collect":false,"totalEvaluate":0,"totalSku":0},{"cm_id":2,"s_id":"1","r_id":"1","cm_name":"美缝瓷砖","cm_price":"7.97","cover_picture":"/pictures/1.jpg,/pictures/2.jpg","detail_picture":"/pictures/1.jpg,/pictures/2.jpg","remark":"买就送装饰鹅暖石","sale_num":0,"top_flag":0,"del_flag":0,"storeInfo":null,"specList":null,"saleSpecList":null,"skuList":null,"evaluateList":null,"collect":false,"totalEvaluate":0,"totalSku":0},{"cm_id":3,"s_id":"2","r_id":"1","cm_name":"皇氏工匠瓷砖","cm_price":"7.97","cover_picture":"/pictures/1.jpg,/pictures/2.jpg","detail_picture":"/pictures/1.jpg,/pictures/2.jpg","remark":"买就送装饰鹅暖石","sale_num":0,"top_flag":0,"del_flag":0,"storeInfo":null,"specList":null,"saleSpecList":null,"skuList":null,"evaluateList":null,"collect":false,"totalEvaluate":0,"totalSku":0},{"cm_id":4,"s_id":"2","r_id":"1","cm_name":"房屋医生美缝剂","cm_price":"7.97","cover_picture":"/pictures/1.jpg,/pictures/2.jpg","detail_picture":"/pictures/1.jpg,/pictures/2.jpg","remark":"买就送装饰鹅暖石","sale_num":0,"top_flag":0,"del_flag":0,"storeInfo":null,"specList":null,"saleSpecList":null,"skuList":null,"evaluateList":null,"collect":false,"totalEvaluate":0,"totalSku":0}]
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
         * cm_id : 1
         * s_id : 1
         * r_id : 1
         * cm_name : 户外塑木木塑地板
         * cm_price : 7.97
         * cover_picture : /pictures/1.jpg,/pictures/2.jpg
         * detail_picture : /pictures/1.jpg,/pictures/2.jpg
         * remark : 买就送装饰鹅暖石
         * sale_num : 0
         * top_flag : 0
         * del_flag : 0
         * storeInfo : null
         * specList : null
         * saleSpecList : null
         * skuList : null
         * evaluateList : null
         * collect : false
         * totalEvaluate : 0
         * totalSku : 0
         */

        private int cm_id;
        private String s_id;
        private String r_id;
        private String cm_name;
        private String cm_price;
        private String cover_picture;
        private String detail_picture;
        private String remark;
        private int sale_num;
        private int top_flag;
        private int del_flag;
        private Object storeInfo;
        private Object specList;
        private Object saleSpecList;
        private Object skuList;
        private Object evaluateList;
        private boolean collect;
        private int totalEvaluate;
        private int totalSku;

        public int getCm_id() {
            return cm_id;
        }

        public void setCm_id(int cm_id) {
            this.cm_id = cm_id;
        }

        public String getS_id() {
            return s_id;
        }

        public void setS_id(String s_id) {
            this.s_id = s_id;
        }

        public String getR_id() {
            return r_id;
        }

        public void setR_id(String r_id) {
            this.r_id = r_id;
        }

        public String getCm_name() {
            return cm_name;
        }

        public void setCm_name(String cm_name) {
            this.cm_name = cm_name;
        }

        public String getCm_price() {
            return cm_price;
        }

        public void setCm_price(String cm_price) {
            this.cm_price = cm_price;
        }

        public String getCover_picture() {
            return cover_picture;
        }

        public void setCover_picture(String cover_picture) {
            this.cover_picture = cover_picture;
        }

        public String getDetail_picture() {
            return detail_picture;
        }

        public void setDetail_picture(String detail_picture) {
            this.detail_picture = detail_picture;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSale_num() {
            return sale_num;
        }

        public void setSale_num(int sale_num) {
            this.sale_num = sale_num;
        }

        public int getTop_flag() {
            return top_flag;
        }

        public void setTop_flag(int top_flag) {
            this.top_flag = top_flag;
        }

        public int getDel_flag() {
            return del_flag;
        }

        public void setDel_flag(int del_flag) {
            this.del_flag = del_flag;
        }

        public Object getStoreInfo() {
            return storeInfo;
        }

        public void setStoreInfo(Object storeInfo) {
            this.storeInfo = storeInfo;
        }

        public Object getSpecList() {
            return specList;
        }

        public void setSpecList(Object specList) {
            this.specList = specList;
        }

        public Object getSaleSpecList() {
            return saleSpecList;
        }

        public void setSaleSpecList(Object saleSpecList) {
            this.saleSpecList = saleSpecList;
        }

        public Object getSkuList() {
            return skuList;
        }

        public void setSkuList(Object skuList) {
            this.skuList = skuList;
        }

        public Object getEvaluateList() {
            return evaluateList;
        }

        public void setEvaluateList(Object evaluateList) {
            this.evaluateList = evaluateList;
        }

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public int getTotalEvaluate() {
            return totalEvaluate;
        }

        public void setTotalEvaluate(int totalEvaluate) {
            this.totalEvaluate = totalEvaluate;
        }

        public int getTotalSku() {
            return totalSku;
        }

        public void setTotalSku(int totalSku) {
            this.totalSku = totalSku;
        }
    }
}

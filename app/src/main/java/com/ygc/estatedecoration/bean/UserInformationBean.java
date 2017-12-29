package com.ygc.estatedecoration.bean;

import java.util.List;

/**
 * Created by FC on 2017/12/14.
 * 用户信息
 */

public class UserInformationBean {

    /**
     * responseState : 1
     * msg : 查询成功
     * data : {"au_id":9,"type":4,"r_id":4,"username":"17054088454","password":"1234","head_portrait":"/pictures/618cc20538934cc5aad56b70a8090d91.jpg","nickname":"还好，","sex":0,"warranty_gold":"","gold_coin":0,"balance":0,"openid":null,"certification":1,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-12 14:47:14.0","demandList":[]}
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
         * au_id : 9
         * type : 4
         * r_id : 4
         * username : 17054088454
         * password : 1234
         * head_portrait : /pictures/618cc20538934cc5aad56b70a8090d91.jpg
         * nickname : 还好，
         * sex : 0
         * warranty_gold :
         * gold_coin : 0
         * balance : 0
         * openid : null
         * certification : 1
         * real_name :
         * number :
         * tel :
         * picture_url :
         * create_time : 2017-12-12 14:47:14.0
         * demandList : []
         */

        private int au_id;
        private int type;
        private int r_id;
        private String username;
        private String password;
        private String head_portrait;
        private String nickname;
        private int sex;
        private String warranty_gold;
        private int gold_coin;
        private int balance;
        private Object openid;
        private int certification;
        private String real_name;
        private String number;
        private String tel;
        private String picture_url;
        private String create_time;
        private List<?> demandList;

        public int getAu_id() {
            return au_id;
        }

        public void setAu_id(int au_id) {
            this.au_id = au_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getR_id() {
            return r_id;
        }

        public void setR_id(int r_id) {
            this.r_id = r_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getHead_portrait() {
            return head_portrait;
        }

        public void setHead_portrait(String head_portrait) {
            this.head_portrait = head_portrait;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getWarranty_gold() {
            return warranty_gold;
        }

        public void setWarranty_gold(String warranty_gold) {
            this.warranty_gold = warranty_gold;
        }

        public int getGold_coin() {
            return gold_coin;
        }

        public void setGold_coin(int gold_coin) {
            this.gold_coin = gold_coin;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public Object getOpenid() {
            return openid;
        }

        public void setOpenid(Object openid) {
            this.openid = openid;
        }

        public int getCertification() {
            return certification;
        }

        public void setCertification(int certification) {
            this.certification = certification;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getPicture_url() {
            return picture_url;
        }

        public void setPicture_url(String picture_url) {
            this.picture_url = picture_url;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public List<?> getDemandList() {
            return demandList;
        }

        public void setDemandList(List<?> demandList) {
            this.demandList = demandList;
        }
    }
}

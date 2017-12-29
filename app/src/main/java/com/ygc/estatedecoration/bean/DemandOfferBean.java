package com.ygc.estatedecoration.bean;

import com.ygc.estatedecoration.entity.base.Base;

import java.io.Serializable;
import java.util.List;

public class DemandOfferBean extends Base implements Serializable{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * doId : a883c750f7f541cf95f60a00c28c03d9
         * cId : 1
         * dId : 1
         * price : 5000
         * needTime : 30
         * toOpen : 0
         * message : 房屋整体改造,并且我们有上百种方案供您选择,合同签署优惠多多
         * beChecked : 0
         * createTime : 2017-12-06 11:49:39.0
         * creator : {"password":"123456","balance":0,"create_time":1512445530000,"sex":0,"nickname":"小月","au_id":1,"type":0,"username":"18742494800"}
         */

        private String doId;
        private String cId;
        private String dId;
        private int price;
        private int needTime;
        private int toOpen;
        private String message;
        private int beChecked;
        private String createTime;
        private CreatorBean creator;

        public String getDoId() {
            return doId;
        }

        public void setDoId(String doId) {
            this.doId = doId;
        }

        public String getCId() {
            return cId;
        }

        public void setCId(String cId) {
            this.cId = cId;
        }

        public String getDId() {
            return dId;
        }

        public void setDId(String dId) {
            this.dId = dId;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getNeedTime() {
            return needTime;
        }

        public void setNeedTime(int needTime) {
            this.needTime = needTime;
        }

        public int getToOpen() {
            return toOpen;
        }

        public void setToOpen(int toOpen) {
            this.toOpen = toOpen;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getBeChecked() {
            return beChecked;
        }

        public void setBeChecked(int beChecked) {
            this.beChecked = beChecked;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public CreatorBean getCreator() {
            return creator;
        }

        public void setCreator(CreatorBean creator) {
            this.creator = creator;
        }

        public static class CreatorBean implements Serializable{
            /**
             * password : 123456
             * balance : 0
             * create_time : 1512445530000
             * sex : 0
             * nickname : 小月
             * au_id : 1
             * type : 0
             * username : 18742494800
             */

            private String password;
            private int balance;
            private long create_time;
            private int sex;
            private String nickname;
            private int au_id;
            private int type;
            private String username;

            public String getHead_portrait() {
                return head_portrait;
            }

            public void setHead_portrait(String head_portrait) {
                this.head_portrait = head_portrait;
            }

            private String head_portrait;


            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public int getBalance() {
                return balance;
            }

            public void setBalance(int balance) {
                this.balance = balance;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

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

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}

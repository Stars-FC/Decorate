package com.ygc.estatedecoration.bean;

import com.ygc.estatedecoration.entity.base.Base;

import java.io.Serializable;
import java.util.List;

public class NeedBean extends Base {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * dId : 18
         * dType : 0
         * missionStartTime : 20170552545
         * missionType : 0
         * constructionStatusQuo : 0
         * buildingArea : 260
         * offer : 10000
         * address : 辽宁沈阳
         * demandDetails : 厕所改装
         * demandNote : 防水
         * createTime : 2017-12-15 16:57:38.0
         * dState : 1
         * creator : {"picture_url":"","create_time":1512445530000,"openid":"","sex":0,"warranty_gold":"","real_name":"","au_id":1,"type":1,"certification":1,"number":"","password":"123456","balance":64.51,"nickname":"小月","tel":"","username":"18742494800","head_portrait":""}
         * title :
         * photos :
         * needDays : 3
         * payId :
         */

        private int dId;
        private String dType;
        private long missionStartTime;
        private String missionType;
        private int constructionStatusQuo;
        private int buildingArea;
        private int offer;
        private String address;
        private String demandDetails;
        private String demandNote;
        private String createTime;
        private int dState;
        private CreatorBean creator;
        private String title;
        private String photos;
        private int needDays;
        private String payId;

        public int getDId() {
            return dId;
        }

        public void setDId(int dId) {
            this.dId = dId;
        }

        public String getDType() {
            return dType;
        }

        public void setDType(String dType) {
            this.dType = dType;
        }

        public long getMissionStartTime() {
            return missionStartTime;
        }

        public void setMissionStartTime(long missionStartTime) {
            this.missionStartTime = missionStartTime;
        }

        public String getMissionType() {
            return missionType;
        }

        public void setMissionType(String missionType) {
            this.missionType = missionType;
        }

        public int getConstructionStatusQuo() {
            return constructionStatusQuo;
        }

        public void setConstructionStatusQuo(int constructionStatusQuo) {
            this.constructionStatusQuo = constructionStatusQuo;
        }

        public int getBuildingArea() {
            return buildingArea;
        }

        public void setBuildingArea(int buildingArea) {
            this.buildingArea = buildingArea;
        }

        public int getOffer() {
            return offer;
        }

        public void setOffer(int offer) {
            this.offer = offer;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDemandDetails() {
            return demandDetails;
        }

        public void setDemandDetails(String demandDetails) {
            this.demandDetails = demandDetails;
        }

        public String getDemandNote() {
            return demandNote;
        }

        public void setDemandNote(String demandNote) {
            this.demandNote = demandNote;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDState() {
            return dState;
        }

        public void setDState(int dState) {
            this.dState = dState;
        }

        public CreatorBean getCreator() {
            return creator;
        }

        public void setCreator(CreatorBean creator) {
            this.creator = creator;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPhotos() {
            return photos;
        }

        public void setPhotos(String photos) {
            this.photos = photos;
        }

        public int getNeedDays() {
            return needDays;
        }

        public void setNeedDays(int needDays) {
            this.needDays = needDays;
        }

        public String getPayId() {
            return payId;
        }

        public void setPayId(String payId) {
            this.payId = payId;
        }

        public static class CreatorBean implements Serializable{
            /**
             * picture_url :
             * create_time : 1512445530000
             * openid :
             * sex : 0
             * warranty_gold :
             * real_name :
             * au_id : 1
             * type : 1
             * certification : 1
             * number :
             * password : 123456
             * balance : 64.51
             * nickname : 小月
             * tel :
             * username : 18742494800
             * head_portrait :
             */

            private String picture_url;
            private long create_time;
            private String openid;
            private int sex;
            private String warranty_gold;
            private String real_name;
            private int au_id;
            private int type;
            private int certification;
            private String number;
            private String password;
            private String balance;
            private String nickname;
            private String tel;
            private String username;
            private String head_portrait;

            public String getPicture_url() {
                return picture_url;
            }

            public void setPicture_url(String picture_url) {
                this.picture_url = picture_url;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
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

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
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

            public int getCertification() {
                return certification;
            }

            public void setCertification(int certification) {
                this.certification = certification;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getHead_portrait() {
                return head_portrait;
            }

            public void setHead_portrait(String head_portrait) {
                this.head_portrait = head_portrait;
            }
        }
    }
}

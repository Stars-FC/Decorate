package com.ygc.estatedecoration.bean;

import com.ygc.estatedecoration.entity.base.Base;

import java.util.List;

public class NeedBean extends Base {


    /**
     * responseState : 1
     * data : [{"dId":3,"dType":"0","missionStartTime":20170552545,"missionType":"0","constructionStatusQuo":0,"buildingArea":260,"offer":10000,"address":"辽宁沈阳","demandDetails":"厕所改装","demandNote":"防水","createTime":"2017-12-05 16:15:48.0","dState":2,"creator":{"password":"123456","balance":0,"create_time":1512445530000,"sex":0,"nickname":"小月","au_id":1,"type":0,"username":"18742494800"}},{"dId":2,"dType":"0","missionStartTime":20170552545,"missionType":"0","constructionStatusQuo":0,"buildingArea":260,"offer":10000,"address":"辽宁沈阳","demandDetails":"厕所改装","demandNote":"防水","createTime":"2017-12-05 16:15:46.0","dState":1,"creator":{"password":"123456","balance":0,"create_time":1512445530000,"sex":0,"nickname":"小月","au_id":1,"type":0,"username":"18742494800"}},{"dId":1,"dType":"0","missionStartTime":20170552545,"missionType":"0","constructionStatusQuo":0,"buildingArea":260,"offer":10000,"address":"辽宁沈阳","demandDetails":"厕所改装","demandNote":"防水","createTime":"2017-12-05 16:15:42.0","dState":1,"creator":{"password":"123456","balance":0,"create_time":1512445530000,"sex":0,"nickname":"小月","au_id":1,"type":0,"username":"18742494800"}}]
     */

    private String responseState;
    private List<DataBean> data;

    public String getResponseState() {
        return responseState;
    }

    public void setResponseState(String responseState) {
        this.responseState = responseState;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * dId : 3
         * dType : 0
         * missionStartTime : 20170552545
         * missionType : 0
         * constructionStatusQuo : 0
         * buildingArea : 260
         * offer : 10000
         * address : 辽宁沈阳
         * demandDetails : 厕所改装
         * demandNote : 防水
         * createTime : 2017-12-05 16:15:48.0
         * dState : 2
         * creator : {"password":"123456","balance":0,"create_time":1512445530000,"sex":0,"nickname":"小月","au_id":1,"type":0,"username":"18742494800"}
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

        public static class CreatorBean {
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

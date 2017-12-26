package com.ygc.estatedecoration.bean;

import java.util.List;

/**
 * Created by FC on 2017/12/19.
 * 获取服务商评价
 */

public class UserCommentBean {

    /**
     * responseState : 1
     * msg : 查询成功
     * data : [{"dcId":"c9588a7d01ba4ec0a83e41b8bc492e71","serverId":"2","conId":"498b43ac76d94221baa28317f412360b","starLeval":"4","cotent":"必须好评,厕所再也不漏了","auId":"1","photos":"demandComment/11/20171214102122-c343e691dfadc89845bc55.jpg,demandComment/11/20171214102122-0048d8ba1db5a003d3c7f4.gif,demandComment/11/20171214102122-7a4e378d2fb87e629c1719.jpg","createTime":"2017-12-14 10:24:08.0","creator":{"picture_url":"","create_time":1512461586000,"openid":"1","sex":0,"warranty_gold":"","real_name":"","au_id":2,"type":2,"certification":0,"number":"","password":"123456","balance":0,"nickname":"小梅","tel":"","username":"13804221090","head_portrait":""}}]
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
         * dcId : c9588a7d01ba4ec0a83e41b8bc492e71
         * serverId : 2
         * conId : 498b43ac76d94221baa28317f412360b
         * starLeval : 4
         * cotent : 必须好评,厕所再也不漏了
         * auId : 1
         * photos : demandComment/11/20171214102122-c343e691dfadc89845bc55.jpg,demandComment/11/20171214102122-0048d8ba1db5a003d3c7f4.gif,demandComment/11/20171214102122-7a4e378d2fb87e629c1719.jpg
         * createTime : 2017-12-14 10:24:08.0
         * creator : {"picture_url":"","create_time":1512461586000,"openid":"1","sex":0,"warranty_gold":"","real_name":"","au_id":2,"type":2,"certification":0,"number":"","password":"123456","balance":0,"nickname":"小梅","tel":"","username":"13804221090","head_portrait":""}
         */

        private String dcId;
        private String serverId;
        private String conId;
        private String starLeval;
        private String cotent;
        private String auId;
        private String photos;
        private String createTime;
        private CreatorBean creator;

        public String getDcId() {
            return dcId;
        }

        public void setDcId(String dcId) {
            this.dcId = dcId;
        }

        public String getServerId() {
            return serverId;
        }

        public void setServerId(String serverId) {
            this.serverId = serverId;
        }

        public String getConId() {
            return conId;
        }

        public void setConId(String conId) {
            this.conId = conId;
        }

        public String getStarLeval() {
            return starLeval;
        }

        public void setStarLeval(String starLeval) {
            this.starLeval = starLeval;
        }

        public String getCotent() {
            return cotent;
        }

        public void setCotent(String cotent) {
            this.cotent = cotent;
        }

        public String getAuId() {
            return auId;
        }

        public void setAuId(String auId) {
            this.auId = auId;
        }

        public String getPhotos() {
            return photos;
        }

        public void setPhotos(String photos) {
            this.photos = photos;
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

        public static class CreatorBean {
            /**
             * picture_url :
             * create_time : 1512461586000
             * openid : 1
             * sex : 0
             * warranty_gold :
             * real_name :
             * au_id : 2
             * type : 2
             * certification : 0
             * number :
             * password : 123456
             * balance : 0
             * nickname : 小梅
             * tel :
             * username : 13804221090
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
            private int balance;
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

            public int getBalance() {
                return balance;
            }

            public void setBalance(int balance) {
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

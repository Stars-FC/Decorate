package com.ygc.estatedecoration.bean;

import java.util.List;

/**
 * Created by FC on 2017/12/27.
 * 我的访客
 */

public class VisiterBean {

    /**
     * responseState : 1
     * msg : 查询成功
     * data : {"todayNum":0,"sumNum":1,"list":[{"visited_time":"2017-12-08","userList":[{"uv_id":2,"au_id":"1","visitor_id":"4","uv_time":"2017-12-08 14:06:55.0","uv_num":1,"userInfo":{"au_id":1,"type":1,"r_id":null,"username":"18742494800","password":"123456","head_portrait":"","nickname":"小月","sex":0,"warranty_gold":"","gold_coin":0,"balance":64.51,"openid":"","certification":1,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-05 11:45:30.0"}}]}]}
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
         * todayNum : 0
         * sumNum : 1
         * list : [{"visited_time":"2017-12-08","userList":[{"uv_id":2,"au_id":"1","visitor_id":"4","uv_time":"2017-12-08 14:06:55.0","uv_num":1,"userInfo":{"au_id":1,"type":1,"r_id":null,"username":"18742494800","password":"123456","head_portrait":"","nickname":"小月","sex":0,"warranty_gold":"","gold_coin":0,"balance":64.51,"openid":"","certification":1,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-05 11:45:30.0"}}]}]
         */

        private int todayNum;
        private int sumNum;
        private List<ListBean> list;

        public int getTodayNum() {
            return todayNum;
        }

        public void setTodayNum(int todayNum) {
            this.todayNum = todayNum;
        }

        public int getSumNum() {
            return sumNum;
        }

        public void setSumNum(int sumNum) {
            this.sumNum = sumNum;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * visited_time : 2017-12-08
             * userList : [{"uv_id":2,"au_id":"1","visitor_id":"4","uv_time":"2017-12-08 14:06:55.0","uv_num":1,"userInfo":{"au_id":1,"type":1,"r_id":null,"username":"18742494800","password":"123456","head_portrait":"","nickname":"小月","sex":0,"warranty_gold":"","gold_coin":0,"balance":64.51,"openid":"","certification":1,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-05 11:45:30.0"}}]
             */

            private String visited_time;
            private List<UserListBean> userList;

            public String getVisited_time() {
                return visited_time;
            }

            public void setVisited_time(String visited_time) {
                this.visited_time = visited_time;
            }

            public List<UserListBean> getUserList() {
                return userList;
            }

            public void setUserList(List<UserListBean> userList) {
                this.userList = userList;
            }

            public static class UserListBean {
                /**
                 * uv_id : 2
                 * au_id : 1
                 * visitor_id : 4
                 * uv_time : 2017-12-08 14:06:55.0
                 * uv_num : 1
                 * userInfo : {"au_id":1,"type":1,"r_id":null,"username":"18742494800","password":"123456","head_portrait":"","nickname":"小月","sex":0,"warranty_gold":"","gold_coin":0,"balance":64.51,"openid":"","certification":1,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-05 11:45:30.0"}
                 */

                private int uv_id;
                private String au_id;
                private String visitor_id;
                private String uv_time;
                private int uv_num;
                private UserInfoBean userInfo;

                public int getUv_id() {
                    return uv_id;
                }

                public void setUv_id(int uv_id) {
                    this.uv_id = uv_id;
                }

                public String getAu_id() {
                    return au_id;
                }

                public void setAu_id(String au_id) {
                    this.au_id = au_id;
                }

                public String getVisitor_id() {
                    return visitor_id;
                }

                public void setVisitor_id(String visitor_id) {
                    this.visitor_id = visitor_id;
                }

                public String getUv_time() {
                    return uv_time;
                }

                public void setUv_time(String uv_time) {
                    this.uv_time = uv_time;
                }

                public int getUv_num() {
                    return uv_num;
                }

                public void setUv_num(int uv_num) {
                    this.uv_num = uv_num;
                }

                public UserInfoBean getUserInfo() {
                    return userInfo;
                }

                public void setUserInfo(UserInfoBean userInfo) {
                    this.userInfo = userInfo;
                }

                public static class UserInfoBean {
                    /**
                     * au_id : 1
                     * type : 1
                     * r_id : null
                     * username : 18742494800
                     * password : 123456
                     * head_portrait :
                     * nickname : 小月
                     * sex : 0
                     * warranty_gold :
                     * gold_coin : 0
                     * balance : 64.51
                     * openid :
                     * certification : 1
                     * real_name :
                     * number :
                     * tel :
                     * picture_url :
                     * create_time : 2017-12-05 11:45:30.0
                     */

                    private int au_id;
                    private int type;
                    private Object r_id;
                    private String username;
                    private String password;
                    private String head_portrait;
                    private String nickname;
                    private int sex;
                    private String warranty_gold;
                    private int gold_coin;
                    private double balance;
                    private String openid;
                    private int certification;
                    private String real_name;
                    private String number;
                    private String tel;
                    private String picture_url;
                    private String create_time;

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

                    public Object getR_id() {
                        return r_id;
                    }

                    public void setR_id(Object r_id) {
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

                    public double getBalance() {
                        return balance;
                    }

                    public void setBalance(double balance) {
                        this.balance = balance;
                    }

                    public String getOpenid() {
                        return openid;
                    }

                    public void setOpenid(String openid) {
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
                }
            }
        }
    }
}

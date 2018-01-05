package com.ygc.estatedecoration.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by FC on 2018/1/2.
 * 服务商，找活动
 */

public class FindActivitesBean {

    /**
     * responseState : 1
     * msg : 查询成功
     * data : [{"activity_id":8,"au_id":"12","title":"","start_time":"2017-12-18","end_time":"2028-07-18","place":"Sdfhdslg ","introduce":"Ahaha","picture_url":null,"money":0,"status":3,"top_flag":0,"create_time":"2017-12-19 08:38:33.0","userInfo":{"au_id":12,"type":0,"r_id":0,"username":"18842314646","password":"123456","head_portrait":"/pictures/9db012a69ee14eaa937074464dfe6e31.jpg","nickname":"Huh","sex":1,"warranty_gold":"","gold_coin":0,"balance":0,"openid":null,"certification":0,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-15 13:20:18.0"}},{"activity_id":6,"au_id":"12","title":"","start_time":"2017-12-18","end_time":"2028-07-18","place":"Sdfhdslg ","introduce":"Ahaha","picture_url":null,"money":0,"status":3,"top_flag":0,"create_time":"2017-12-18 17:02:25.0","userInfo":{"au_id":12,"type":0,"r_id":0,"username":"18842314646","password":"123456","head_portrait":"/pictures/9db012a69ee14eaa937074464dfe6e31.jpg","nickname":"Huh","sex":1,"warranty_gold":"","gold_coin":0,"balance":0,"openid":null,"certification":0,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-15 13:20:18.0"}},{"activity_id":7,"au_id":"12","title":"","start_time":"2017-12-18","end_time":"2028-07-18","place":"Sdfhdslg ","introduce":"Ahaha","picture_url":null,"money":0,"status":3,"top_flag":0,"create_time":"2017-12-18 17:02:25.0","userInfo":{"au_id":12,"type":0,"r_id":0,"username":"18842314646","password":"123456","head_portrait":"/pictures/9db012a69ee14eaa937074464dfe6e31.jpg","nickname":"Huh","sex":1,"warranty_gold":"","gold_coin":0,"balance":0,"openid":null,"certification":0,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-15 13:20:18.0"}},{"activity_id":5,"au_id":"12","title":"Huh ","start_time":"2017-12-18","end_time":"2018-03-18","place":"Sssss","introduce":"Assad\u2019s","picture_url":null,"money":0,"status":3,"top_flag":0,"create_time":"2017-12-18 16:54:48.0","userInfo":{"au_id":12,"type":0,"r_id":0,"username":"18842314646","password":"123456","head_portrait":"/pictures/9db012a69ee14eaa937074464dfe6e31.jpg","nickname":"Huh","sex":1,"warranty_gold":"","gold_coin":0,"balance":0,"openid":null,"certification":0,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-15 13:20:18.0"}},{"activity_id":4,"au_id":"12","title":"Shdflhsd ","start_time":"2017-12-18","end_time":"2017-12-18","place":"Did","introduce":"diff","picture_url":null,"money":0,"status":3,"top_flag":0,"create_time":"2017-12-18 16:46:05.0","userInfo":{"au_id":12,"type":0,"r_id":0,"username":"18842314646","password":"123456","head_portrait":"/pictures/9db012a69ee14eaa937074464dfe6e31.jpg","nickname":"Huh","sex":1,"warranty_gold":"","gold_coin":0,"balance":0,"openid":null,"certification":0,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-15 13:20:18.0"}},{"activity_id":3,"au_id":"10","title":":)","start_time":"12-12","end_time":"12-30","place":"锦州","introduce":"介绍","picture_url":"/pictures/94382aae2444411592e59385b73ce81f.jpg","money":0,"status":3,"top_flag":0,"create_time":"2017-12-18 16:27:58.0","userInfo":{"au_id":10,"type":0,"r_id":0,"username":"13841698175","password":"1234","head_portrait":"","nickname":"冯_用户","sex":0,"warranty_gold":"","gold_coin":0,"balance":0,"openid":null,"certification":0,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-12 16:22:58.0"}},{"activity_id":2,"au_id":"9","title":"元旦快乐","start_time":"2017-12-18","end_time":"2017-12-30","place":"锦州市","introduce":"元旦活动，元旦活动，元旦活动","picture_url":"/pictures/2d0bab7a1369498ca563a3187b719681.jpg","money":0,"status":3,"top_flag":0,"create_time":"2017-12-18 14:42:17.0","userInfo":{"au_id":9,"type":4,"r_id":4,"username":"17054088454","password":"1234","head_portrait":"/pictures/ed790580cd054a5cbdcf76e258c2b2c3.jpg","nickname":"好啦","sex":1,"warranty_gold":"","gold_coin":0,"balance":0,"openid":null,"certification":1,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-12 14:47:14.0"}},{"activity_id":1,"au_id":"1","title":"哈哈","start_time":"2017-12-05 11:45:30","end_time":"2017-12-25 11:45:30","place":"辽宁省鞍山市铁西区","introduce":"完美","picture_url":"/pictures/9546dce9a59745cd830997cf97a25c09.jpg","money":0,"status":3,"top_flag":0,"create_time":"2017-12-13 11:42:43.0","userInfo":{"au_id":1,"type":3,"r_id":null,"username":"18742494800","password":"123456","head_portrait":"","nickname":"小月","sex":0,"warranty_gold":"","gold_coin":0,"balance":64.51,"openid":"","certification":1,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-05 11:45:30.0"}}]
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

    public static class DataBean implements Serializable {
        /**
         * activity_id : 8
         * au_id : 12
         * title :
         * start_time : 2017-12-18
         * end_time : 2028-07-18
         * place : Sdfhdslg
         * introduce : Ahaha
         * picture_url : null
         * money : 0
         * status : 3
         * top_flag : 0
         * create_time : 2017-12-19 08:38:33.0
         * userInfo : {"au_id":12,"type":0,"r_id":0,"username":"18842314646","password":"123456","head_portrait":"/pictures/9db012a69ee14eaa937074464dfe6e31.jpg","nickname":"Huh","sex":1,"warranty_gold":"","gold_coin":0,"balance":0,"openid":null,"certification":0,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-15 13:20:18.0"}
         */

        private int activity_id;
        private String au_id;
        private String title;
        private String start_time;
        private String end_time;
        private String place;
        private String introduce;
        private Object picture_url;
        private int money;
        private int status;
        private int top_flag;
        private String create_time;
        private UserInfoBean userInfo;

        public int getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(int activity_id) {
            this.activity_id = activity_id;
        }

        public String getAu_id() {
            return au_id;
        }

        public void setAu_id(String au_id) {
            this.au_id = au_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public Object getPicture_url() {
            return picture_url;
        }

        public void setPicture_url(Object picture_url) {
            this.picture_url = picture_url;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getTop_flag() {
            return top_flag;
        }

        public void setTop_flag(int top_flag) {
            this.top_flag = top_flag;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBean implements Serializable {
            /**
             * au_id : 12
             * type : 0
             * r_id : 0
             * username : 18842314646
             * password : 123456
             * head_portrait : /pictures/9db012a69ee14eaa937074464dfe6e31.jpg
             * nickname : Huh
             * sex : 1
             * warranty_gold :
             * gold_coin : 0
             * balance : 0
             * openid : null
             * certification : 0
             * real_name :
             * number :
             * tel :
             * picture_url :
             * create_time : 2017-12-15 13:20:18.0
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
        }
    }
}

package com.ygc.estatedecoration.bean;

import com.ygc.estatedecoration.entity.base.Base;

import java.util.List;

public class PanoramaBean extends Base {


    /**
     * message : 查询成功
     * data : [{"cp_id":1,"au_id":"1","d_id":"50","title":"小户型改造","city":"沈阳","address":"亚泰花园","house_type":"田园","static_picture":"/pictures/1.jpg","dynamic_picture":"","r_id":"1","create_time":"2017-12-14 09:07:35.0","upload_type":0,"userInfo":{"au_id":1,"type":1,"r_id":null,"username":"18742494800","password":"123456","head_portrait":"","nickname":"小月","sex":0,"warranty_gold":"","gold_coin":0,"balance":64.51,"openid":"","certification":1,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-05 11:45:30.0"},"collect":false},{"cp_id":3,"au_id":"3","d_id":"50","title":"小户型改造","city":"沈阳","address":"亚泰花园","house_type":"田园","static_picture":"/pictures/1.jpg","dynamic_picture":"","r_id":"3","create_time":"2017-12-14 09:07:35.0","upload_type":0,"userInfo":{"au_id":3,"type":3,"r_id":null,"username":"15902475318","password":"1","head_portrait":"","nickname":"1","sex":0,"warranty_gold":"","gold_coin":0,"balance":0,"openid":null,"certification":0,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-07 13:43:39.0"},"collect":false},{"cp_id":4,"au_id":"9","d_id":"50","title":"小户型改造","city":"沈阳","address":"亚泰花园","house_type":"田园","static_picture":"/pictures/1.jpg","dynamic_picture":"","r_id":"2","create_time":"2017-12-14 09:07:35.0","upload_type":0,"userInfo":{"au_id":9,"type":4,"r_id":4,"username":"17054088454","password":"1234","head_portrait":"/pictures/ed790580cd054a5cbdcf76e258c2b2c3.jpg","nickname":"好啦","sex":1,"warranty_gold":"","gold_coin":0,"balance":0,"openid":null,"certification":1,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-12 14:47:14.0"},"collect":false},{"cp_id":6,"au_id":"2","d_id":"50","title":"小户型改造","city":"沈阳","address":"亚泰花园","house_type":"田园","static_picture":"/pictures/1.jpg","dynamic_picture":"","r_id":"2","create_time":"2017-12-14 09:07:35.0","upload_type":0,"userInfo":{"au_id":2,"type":2,"r_id":null,"username":"13804221090","password":"123456","head_portrait":"","nickname":"小梅","sex":0,"warranty_gold":"","gold_coin":0,"balance":0,"openid":"1","certification":0,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-05 16:13:06.0"},"collect":false},{"cp_id":7,"au_id":"3","d_id":"50","title":"小户型改造","city":"沈阳","address":"亚泰花园","house_type":"田园","static_picture":"/pictures/1.jpg","dynamic_picture":"","r_id":"3","create_time":"2017-12-14 09:07:35.0","upload_type":0,"userInfo":{"au_id":3,"type":3,"r_id":null,"username":"15902475318","password":"1","head_portrait":"","nickname":"1","sex":0,"warranty_gold":"","gold_coin":0,"balance":0,"openid":null,"certification":0,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-07 13:43:39.0"},"collect":false},{"cp_id":9,"au_id":"1","d_id":"50","title":"小户型改造","city":"沈阳","address":"亚泰花园","house_type":"田园","static_picture":"/pictures/1.jpg","dynamic_picture":"","r_id":"2","create_time":"2017-12-14 09:07:35.0","upload_type":0,"userInfo":{"au_id":1,"type":1,"r_id":null,"username":"18742494800","password":"123456","head_portrait":"","nickname":"小月","sex":0,"warranty_gold":"","gold_coin":0,"balance":64.51,"openid":"","certification":1,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-05 11:45:30.0"},"collect":false}]
     * page : {"pageNow":1,"pageSize":20,"totalCount":6,"totalPageCount":1,"startPos":0,"hasFirst":false,"hasPre":false,"hasNext":true,"hasLast":true}
     */

    private String message;
    private PageBean page;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PageBean {
        /**
         * pageNow : 1
         * pageSize : 20
         * totalCount : 6
         * totalPageCount : 1
         * startPos : 0
         * hasFirst : false
         * hasPre : false
         * hasNext : true
         * hasLast : true
         */

        private int pageNow;
        private int pageSize;
        private int totalCount;
        private int totalPageCount;
        private int startPos;
        private boolean hasFirst;
        private boolean hasPre;
        private boolean hasNext;
        private boolean hasLast;

        public int getPageNow() {
            return pageNow;
        }

        public void setPageNow(int pageNow) {
            this.pageNow = pageNow;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPageCount() {
            return totalPageCount;
        }

        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
        }

        public int getStartPos() {
            return startPos;
        }

        public void setStartPos(int startPos) {
            this.startPos = startPos;
        }

        public boolean isHasFirst() {
            return hasFirst;
        }

        public void setHasFirst(boolean hasFirst) {
            this.hasFirst = hasFirst;
        }

        public boolean isHasPre() {
            return hasPre;
        }

        public void setHasPre(boolean hasPre) {
            this.hasPre = hasPre;
        }

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        public boolean isHasLast() {
            return hasLast;
        }

        public void setHasLast(boolean hasLast) {
            this.hasLast = hasLast;
        }
    }

    public static class DataBean {
        /**
         * cp_id : 1
         * au_id : 1
         * d_id : 50
         * title : 小户型改造
         * city : 沈阳
         * address : 亚泰花园
         * house_type : 田园
         * static_picture : /pictures/1.jpg
         * dynamic_picture :
         * r_id : 1
         * create_time : 2017-12-14 09:07:35.0
         * upload_type : 0
         * userInfo : {"au_id":1,"type":1,"r_id":null,"username":"18742494800","password":"123456","head_portrait":"","nickname":"小月","sex":0,"warranty_gold":"","gold_coin":0,"balance":64.51,"openid":"","certification":1,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-05 11:45:30.0"}
         * collect : false
         */

        private int cp_id;
        private String au_id;
        private String d_id;
        private String title;
        private String city;
        private String address;
        private String house_type;
        private String static_picture;
        private String dynamic_picture;
        private String r_id;
        private String create_time;
        private int upload_type;
        private UserInfoBean userInfo;
        private boolean collect;

        public int getCp_id() {
            return cp_id;
        }

        public void setCp_id(int cp_id) {
            this.cp_id = cp_id;
        }

        public String getAu_id() {
            return au_id;
        }

        public void setAu_id(String au_id) {
            this.au_id = au_id;
        }

        public String getD_id() {
            return d_id;
        }

        public void setD_id(String d_id) {
            this.d_id = d_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getHouse_type() {
            return house_type;
        }

        public void setHouse_type(String house_type) {
            this.house_type = house_type;
        }

        public String getStatic_picture() {
            return static_picture;
        }

        public void setStatic_picture(String static_picture) {
            this.static_picture = static_picture;
        }

        public String getDynamic_picture() {
            return dynamic_picture;
        }

        public void setDynamic_picture(String dynamic_picture) {
            this.dynamic_picture = dynamic_picture;
        }

        public String getR_id() {
            return r_id;
        }

        public void setR_id(String r_id) {
            this.r_id = r_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getUpload_type() {
            return upload_type;
        }

        public void setUpload_type(int upload_type) {
            this.upload_type = upload_type;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
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

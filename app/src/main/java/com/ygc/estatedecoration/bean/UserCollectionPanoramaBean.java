package com.ygc.estatedecoration.bean;

import java.util.List;

/**
 * Created by FC on 2017/12/28.
 * * 我的收藏-全景图
 */

public class UserCollectionPanoramaBean {

    /**
     * responseState : 1
     * msg : 查询成功
     * data : [{"uc_id":2,"auId":"1","articleId":"1","articleType":2,"createTime":"2017-12-19 15:00:45.0","article":{"cp_id":1,"au_id":"1","d_id":"50","title":"小户型改造","city":"沈阳","address":"亚泰花园","house_type":"田园","static_picture":"/pictures/1.jpg","dynamic_picture":"","r_id":"1","create_time":"2017-12-14 09:07:35.0","upload_type":0,"userInfo":{"au_id":1,"type":1,"r_id":null,"username":"18742494800","password":"123456","head_portrait":"","nickname":"小月","sex":0,"warranty_gold":"","gold_coin":0,"balance":64.51,"openid":"","certification":1,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-05 11:45:30.0"},"collect":true}}]
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
         * uc_id : 2
         * auId : 1
         * articleId : 1
         * articleType : 2
         * createTime : 2017-12-19 15:00:45.0
         * article : {"cp_id":1,"au_id":"1","d_id":"50","title":"小户型改造","city":"沈阳","address":"亚泰花园","house_type":"田园","static_picture":"/pictures/1.jpg","dynamic_picture":"","r_id":"1","create_time":"2017-12-14 09:07:35.0","upload_type":0,"userInfo":{"au_id":1,"type":1,"r_id":null,"username":"18742494800","password":"123456","head_portrait":"","nickname":"小月","sex":0,"warranty_gold":"","gold_coin":0,"balance":64.51,"openid":"","certification":1,"real_name":"","number":"","tel":"","picture_url":"","create_time":"2017-12-05 11:45:30.0"},"collect":true}
         */

        private int uc_id;
        private String auId;
        private String articleId;
        private int articleType;
        private String createTime;
        private ArticleBean article;

        public int getUc_id() {
            return uc_id;
        }

        public void setUc_id(int uc_id) {
            this.uc_id = uc_id;
        }

        public String getAuId() {
            return auId;
        }

        public void setAuId(String auId) {
            this.auId = auId;
        }

        public String getArticleId() {
            return articleId;
        }

        public void setArticleId(String articleId) {
            this.articleId = articleId;
        }

        public int getArticleType() {
            return articleType;
        }

        public void setArticleType(int articleType) {
            this.articleType = articleType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public ArticleBean getArticle() {
            return article;
        }

        public void setArticle(ArticleBean article) {
            this.article = article;
        }

        public static class ArticleBean {
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
             * collect : true
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
}

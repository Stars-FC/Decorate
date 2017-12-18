package com.ygc.estatedecoration.bean;

import java.util.List;

/**
 * Created by FC on 2017/12/18.
 * 购物车
 */

public class UserShopCarBean {

    /**
     * responseState : 1
     * msg : 操作成功
     * data : [{"comCarId":"f78f985a7381432aa1cbd6351a108fd6","sId":"1","auId":"1","createTime":"2017-12-13 10:16:45.0","commodityCarItem":[{"comCarItemId":"e56c0b510cbf488092aa455d33f06532","comCarId":"f78f985a7381432aa1cbd6351a108fd6","comId":"1","counts":"10","commodity":[{"del_flag":0,"s_id":"1","cm_price":"7.97","top_flag":0,"sale_num":0,"cm_id":1,"cm_name":"户外塑木木塑地板","cover_picture":"/pictures/1.jpg,/pictures/2.jpg","detail_picture":"/pictures/1.jpg,/pictures/2.jpg","remark":"买就送装饰鹅暖石","r_id":"1"}]},{"comCarItemId":"d2bc494b0e4f4f008a9c383783bfb068","comCarId":"f78f985a7381432aa1cbd6351a108fd6","comId":"2","counts":"10","commodity":[{"del_flag":0,"s_id":"1","cm_price":"7.97","top_flag":1,"sale_num":0,"cm_id":2,"cm_name":"美缝瓷砖","cover_picture":"/pictures/1.jpg,/pictures/2.jpg","detail_picture":"/pictures/1.jpg,/pictures/2.jpg","remark":"买就送装饰鹅暖石","r_id":"1"}]},{"comCarItemId":"d6cab1a36c414f0cbbc7c529ed31610c","comCarId":"f78f985a7381432aa1cbd6351a108fd6","comId":"3","counts":"10","commodity":[{"del_flag":0,"s_id":"2","cm_price":"7.97","top_flag":1,"sale_num":0,"cm_id":3,"cm_name":"皇氏工匠瓷砖","cover_picture":"/pictures/1.jpg,/pictures/2.jpg","detail_picture":"/pictures/1.jpg,/pictures/2.jpg","remark":"买就送装饰鹅暖石","r_id":"1"}]}],"store":[{"background_info":"","s_id":1,"create_time":1512636739000,"introduce":"真诚服务","bid_num":0,"au_id":"1","s_name":"小月的材料屋","turnover":0,"work_experience":""}]}]
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
         * comCarId : f78f985a7381432aa1cbd6351a108fd6
         * sId : 1
         * auId : 1
         * createTime : 2017-12-13 10:16:45.0
         * commodityCarItem : [{"comCarItemId":"e56c0b510cbf488092aa455d33f06532","comCarId":"f78f985a7381432aa1cbd6351a108fd6","comId":"1","counts":"10","commodity":[{"del_flag":0,"s_id":"1","cm_price":"7.97","top_flag":0,"sale_num":0,"cm_id":1,"cm_name":"户外塑木木塑地板","cover_picture":"/pictures/1.jpg,/pictures/2.jpg","detail_picture":"/pictures/1.jpg,/pictures/2.jpg","remark":"买就送装饰鹅暖石","r_id":"1"}]},{"comCarItemId":"d2bc494b0e4f4f008a9c383783bfb068","comCarId":"f78f985a7381432aa1cbd6351a108fd6","comId":"2","counts":"10","commodity":[{"del_flag":0,"s_id":"1","cm_price":"7.97","top_flag":1,"sale_num":0,"cm_id":2,"cm_name":"美缝瓷砖","cover_picture":"/pictures/1.jpg,/pictures/2.jpg","detail_picture":"/pictures/1.jpg,/pictures/2.jpg","remark":"买就送装饰鹅暖石","r_id":"1"}]},{"comCarItemId":"d6cab1a36c414f0cbbc7c529ed31610c","comCarId":"f78f985a7381432aa1cbd6351a108fd6","comId":"3","counts":"10","commodity":[{"del_flag":0,"s_id":"2","cm_price":"7.97","top_flag":1,"sale_num":0,"cm_id":3,"cm_name":"皇氏工匠瓷砖","cover_picture":"/pictures/1.jpg,/pictures/2.jpg","detail_picture":"/pictures/1.jpg,/pictures/2.jpg","remark":"买就送装饰鹅暖石","r_id":"1"}]}]
         * store : [{"background_info":"","s_id":1,"create_time":1512636739000,"introduce":"真诚服务","bid_num":0,"au_id":"1","s_name":"小月的材料屋","turnover":0,"work_experience":""}]
         */

        private String comCarId;
        private String sId;
        private String auId;
        private String createTime;
        private List<CommodityCarItemBean> commodityCarItem;
        private List<StoreBean> store;

        public String getComCarId() {
            return comCarId;
        }

        public void setComCarId(String comCarId) {
            this.comCarId = comCarId;
        }

        public String getSId() {
            return sId;
        }

        public void setSId(String sId) {
            this.sId = sId;
        }

        public String getAuId() {
            return auId;
        }

        public void setAuId(String auId) {
            this.auId = auId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public List<CommodityCarItemBean> getCommodityCarItem() {
            return commodityCarItem;
        }

        public void setCommodityCarItem(List<CommodityCarItemBean> commodityCarItem) {
            this.commodityCarItem = commodityCarItem;
        }

        public List<StoreBean> getStore() {
            return store;
        }

        public void setStore(List<StoreBean> store) {
            this.store = store;
        }

        public static class CommodityCarItemBean {
            /**
             * comCarItemId : e56c0b510cbf488092aa455d33f06532
             * comCarId : f78f985a7381432aa1cbd6351a108fd6
             * comId : 1
             * counts : 10
             * commodity : [{"del_flag":0,"s_id":"1","cm_price":"7.97","top_flag":0,"sale_num":0,"cm_id":1,"cm_name":"户外塑木木塑地板","cover_picture":"/pictures/1.jpg,/pictures/2.jpg","detail_picture":"/pictures/1.jpg,/pictures/2.jpg","remark":"买就送装饰鹅暖石","r_id":"1"}]
             */

            private String comCarItemId;
            private String comCarId;
            private String comId;
            private String counts;
            private List<CommodityBean> commodity;

            public String getComCarItemId() {
                return comCarItemId;
            }

            public void setComCarItemId(String comCarItemId) {
                this.comCarItemId = comCarItemId;
            }

            public String getComCarId() {
                return comCarId;
            }

            public void setComCarId(String comCarId) {
                this.comCarId = comCarId;
            }

            public String getComId() {
                return comId;
            }

            public void setComId(String comId) {
                this.comId = comId;
            }

            public String getCounts() {
                return counts;
            }

            public void setCounts(String counts) {
                this.counts = counts;
            }

            public List<CommodityBean> getCommodity() {
                return commodity;
            }

            public void setCommodity(List<CommodityBean> commodity) {
                this.commodity = commodity;
            }

            public static class CommodityBean {
                /**
                 * del_flag : 0
                 * s_id : 1
                 * cm_price : 7.97
                 * top_flag : 0
                 * sale_num : 0
                 * cm_id : 1
                 * cm_name : 户外塑木木塑地板
                 * cover_picture : /pictures/1.jpg,/pictures/2.jpg
                 * detail_picture : /pictures/1.jpg,/pictures/2.jpg
                 * remark : 买就送装饰鹅暖石
                 * r_id : 1
                 */

                private int del_flag;
                private String s_id;
                private String cm_price;
                private int top_flag;
                private int sale_num;
                private int cm_id;
                private String cm_name;
                private String cover_picture;
                private String detail_picture;
                private String remark;
                private String r_id;

                public int getDel_flag() {
                    return del_flag;
                }

                public void setDel_flag(int del_flag) {
                    this.del_flag = del_flag;
                }

                public String getS_id() {
                    return s_id;
                }

                public void setS_id(String s_id) {
                    this.s_id = s_id;
                }

                public String getCm_price() {
                    return cm_price;
                }

                public void setCm_price(String cm_price) {
                    this.cm_price = cm_price;
                }

                public int getTop_flag() {
                    return top_flag;
                }

                public void setTop_flag(int top_flag) {
                    this.top_flag = top_flag;
                }

                public int getSale_num() {
                    return sale_num;
                }

                public void setSale_num(int sale_num) {
                    this.sale_num = sale_num;
                }

                public int getCm_id() {
                    return cm_id;
                }

                public void setCm_id(int cm_id) {
                    this.cm_id = cm_id;
                }

                public String getCm_name() {
                    return cm_name;
                }

                public void setCm_name(String cm_name) {
                    this.cm_name = cm_name;
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

                public String getR_id() {
                    return r_id;
                }

                public void setR_id(String r_id) {
                    this.r_id = r_id;
                }
            }
        }

        public static class StoreBean {
            /**
             * background_info :
             * s_id : 1
             * create_time : 1512636739000
             * introduce : 真诚服务
             * bid_num : 0
             * au_id : 1
             * s_name : 小月的材料屋
             * turnover : 0
             * work_experience :
             */

            private String background_info;
            private int s_id;
            private long create_time;
            private String introduce;
            private int bid_num;
            private String au_id;
            private String s_name;
            private int turnover;
            private String work_experience;

            public String getBackground_info() {
                return background_info;
            }

            public void setBackground_info(String background_info) {
                this.background_info = background_info;
            }

            public int getS_id() {
                return s_id;
            }

            public void setS_id(int s_id) {
                this.s_id = s_id;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public int getBid_num() {
                return bid_num;
            }

            public void setBid_num(int bid_num) {
                this.bid_num = bid_num;
            }

            public String getAu_id() {
                return au_id;
            }

            public void setAu_id(String au_id) {
                this.au_id = au_id;
            }

            public String getS_name() {
                return s_name;
            }

            public void setS_name(String s_name) {
                this.s_name = s_name;
            }

            public int getTurnover() {
                return turnover;
            }

            public void setTurnover(int turnover) {
                this.turnover = turnover;
            }

            public String getWork_experience() {
                return work_experience;
            }

            public void setWork_experience(String work_experience) {
                this.work_experience = work_experience;
            }
        }
    }
}

package com.ygc.estatedecoration.bean;

import com.ygc.estatedecoration.entity.base.Base;


public class UserCaseEffectDetailBean extends Base {

    /**
     * data : {"d_id":"50","ce_id_designer":"4","ce_id_construction":"2","ce_id_supervisor":"1","ce_id_material":"3","designerInfo":{"ce_id":4,"au_id":"14","d_id":"50","title":"大苏打","r_id":"2","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":{"s_id":11,"au_id":"14","s_name":"1","s_logo":"/pictures/fa1b7c05c6614fcdbb622394571fea9f.jpg","s_type":"","turnover":0,"bid_num":0,"introduce":"我是一个粉刷匠,粉刷本领强","background_info":" ","work_experience":"2","work_year":"1","s_province":"北京市","s_city":"北京市","r_id":"1","create_time":"2017-12-07 16:52:19.0","applause_rate":"100","comprehensive_score":"5","rinfo":{"r_id":1,"r_name":"木工","r_picture":"/pictures/1.jpg","type":4}}},"constructionInfo":{"ce_id":2,"au_id":"2","d_id":"50","title":"大苏打","r_id":"2","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":{"s_id":2,"au_id":"2","s_name":"1111","s_logo":"/pictures/8a707411d0514391924a944261f351c6.jpg","s_type":"个人","turnover":0,"bid_num":0,"introduce":"我是一个粉刷匠,粉刷本领强","background_info":" ","work_experience":"11","work_year":"1","s_province":"北京市","s_city":"北京市","r_id":"1","create_time":"2017-12-07 16:52:19.0","applause_rate":"100","comprehensive_score":"5","rinfo":{"r_id":1,"r_name":"木工","r_picture":"/pictures/1.jpg","type":4}}},"supervisorInfo":{"ce_id":1,"au_id":"1","d_id":"50","title":"大苏打","r_id":"1","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":{"s_id":1,"au_id":"1","s_name":"小月的材料屋","s_logo":"/pictures/1.jpg","s_type":"","turnover":0,"bid_num":0,"introduce":"真诚服务","background_info":"","work_experience":"","work_year":"1","s_province":"辽宁","s_city":"沈阳","r_id":"1","create_time":"2017-12-07 16:52:19.0","applause_rate":"100","comprehensive_score":"5","rinfo":{"r_id":1,"r_name":"木工","r_picture":"/pictures/1.jpg","type":4}}},"materialInfo":{"ce_id":3,"au_id":"9","d_id":"50","title":"大苏打","r_id":"3","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":{"s_id":6,"au_id":"9","s_name":"1","s_logo":"/pictures/fa1b7c05c6614fcdbb622394571fea9f.jpg","s_type":"","turnover":0,"bid_num":0,"introduce":"我是一个粉刷匠,粉刷本领强","background_info":" ","work_experience":"2","work_year":"1","s_province":"北京市","s_city":"北京市","r_id":"1","create_time":"2017-12-07 16:52:19.0","applause_rate":"100","comprehensive_score":"5","rinfo":{"r_id":1,"r_name":"木工","r_picture":"/pictures/1.jpg","type":4}}},"collect":false}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * d_id : 50
         * ce_id_designer : 4
         * ce_id_construction : 2
         * ce_id_supervisor : 1
         * ce_id_material : 3
         * designerInfo : {"ce_id":4,"au_id":"14","d_id":"50","title":"大苏打","r_id":"2","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":{"s_id":11,"au_id":"14","s_name":"1","s_logo":"/pictures/fa1b7c05c6614fcdbb622394571fea9f.jpg","s_type":"","turnover":0,"bid_num":0,"introduce":"我是一个粉刷匠,粉刷本领强","background_info":" ","work_experience":"2","work_year":"1","s_province":"北京市","s_city":"北京市","r_id":"1","create_time":"2017-12-07 16:52:19.0","applause_rate":"100","comprehensive_score":"5","rinfo":{"r_id":1,"r_name":"木工","r_picture":"/pictures/1.jpg","type":4}}}
         * constructionInfo : {"ce_id":2,"au_id":"2","d_id":"50","title":"大苏打","r_id":"2","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":{"s_id":2,"au_id":"2","s_name":"1111","s_logo":"/pictures/8a707411d0514391924a944261f351c6.jpg","s_type":"个人","turnover":0,"bid_num":0,"introduce":"我是一个粉刷匠,粉刷本领强","background_info":" ","work_experience":"11","work_year":"1","s_province":"北京市","s_city":"北京市","r_id":"1","create_time":"2017-12-07 16:52:19.0","applause_rate":"100","comprehensive_score":"5","rinfo":{"r_id":1,"r_name":"木工","r_picture":"/pictures/1.jpg","type":4}}}
         * supervisorInfo : {"ce_id":1,"au_id":"1","d_id":"50","title":"大苏打","r_id":"1","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":{"s_id":1,"au_id":"1","s_name":"小月的材料屋","s_logo":"/pictures/1.jpg","s_type":"","turnover":0,"bid_num":0,"introduce":"真诚服务","background_info":"","work_experience":"","work_year":"1","s_province":"辽宁","s_city":"沈阳","r_id":"1","create_time":"2017-12-07 16:52:19.0","applause_rate":"100","comprehensive_score":"5","rinfo":{"r_id":1,"r_name":"木工","r_picture":"/pictures/1.jpg","type":4}}}
         * materialInfo : {"ce_id":3,"au_id":"9","d_id":"50","title":"大苏打","r_id":"3","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":{"s_id":6,"au_id":"9","s_name":"1","s_logo":"/pictures/fa1b7c05c6614fcdbb622394571fea9f.jpg","s_type":"","turnover":0,"bid_num":0,"introduce":"我是一个粉刷匠,粉刷本领强","background_info":" ","work_experience":"2","work_year":"1","s_province":"北京市","s_city":"北京市","r_id":"1","create_time":"2017-12-07 16:52:19.0","applause_rate":"100","comprehensive_score":"5","rinfo":{"r_id":1,"r_name":"木工","r_picture":"/pictures/1.jpg","type":4}}}
         * collect : false
         */

        private String d_id;
        private String ce_id_designer;
        private String ce_id_construction;
        private String ce_id_supervisor;
        private String ce_id_material;
        private DesignerInfoBean designerInfo;
        private ConstructionInfoBean constructionInfo;
        private SupervisorInfoBean supervisorInfo;
        private MaterialInfoBean materialInfo;
        private boolean collect;

        public String getD_id() {
            return d_id;
        }

        public void setD_id(String d_id) {
            this.d_id = d_id;
        }

        public String getCe_id_designer() {
            return ce_id_designer;
        }

        public void setCe_id_designer(String ce_id_designer) {
            this.ce_id_designer = ce_id_designer;
        }

        public String getCe_id_construction() {
            return ce_id_construction;
        }

        public void setCe_id_construction(String ce_id_construction) {
            this.ce_id_construction = ce_id_construction;
        }

        public String getCe_id_supervisor() {
            return ce_id_supervisor;
        }

        public void setCe_id_supervisor(String ce_id_supervisor) {
            this.ce_id_supervisor = ce_id_supervisor;
        }

        public String getCe_id_material() {
            return ce_id_material;
        }

        public void setCe_id_material(String ce_id_material) {
            this.ce_id_material = ce_id_material;
        }

        public DesignerInfoBean getDesignerInfo() {
            return designerInfo;
        }

        public void setDesignerInfo(DesignerInfoBean designerInfo) {
            this.designerInfo = designerInfo;
        }

        public ConstructionInfoBean getConstructionInfo() {
            return constructionInfo;
        }

        public void setConstructionInfo(ConstructionInfoBean constructionInfo) {
            this.constructionInfo = constructionInfo;
        }

        public SupervisorInfoBean getSupervisorInfo() {
            return supervisorInfo;
        }

        public void setSupervisorInfo(SupervisorInfoBean supervisorInfo) {
            this.supervisorInfo = supervisorInfo;
        }

        public MaterialInfoBean getMaterialInfo() {
            return materialInfo;
        }

        public void setMaterialInfo(MaterialInfoBean materialInfo) {
            this.materialInfo = materialInfo;
        }

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public static class DesignerInfoBean {
            /**
             * ce_id : 4
             * au_id : 14
             * d_id : 50
             * title : 大苏打
             * r_id : 2
             * effect_picture : /pictures/1.jpg
             * create_time : 2017-12-11 10:25:58.0
             * upload_type : 0
             * storeInfo : {"s_id":11,"au_id":"14","s_name":"1","s_logo":"/pictures/fa1b7c05c6614fcdbb622394571fea9f.jpg","s_type":"","turnover":0,"bid_num":0,"introduce":"我是一个粉刷匠,粉刷本领强","background_info":" ","work_experience":"2","work_year":"1","s_province":"北京市","s_city":"北京市","r_id":"1","create_time":"2017-12-07 16:52:19.0","applause_rate":"100","comprehensive_score":"5","rinfo":{"r_id":1,"r_name":"木工","r_picture":"/pictures/1.jpg","type":4}}
             */

            private int ce_id;
            private String au_id;
            private String d_id;
            private String title;
            private String r_id;
            private String effect_picture;
            private String create_time;
            private int upload_type;
            private StoreInfoBean storeInfo;

            public int getCe_id() {
                return ce_id;
            }

            public void setCe_id(int ce_id) {
                this.ce_id = ce_id;
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

            public String getR_id() {
                return r_id;
            }

            public void setR_id(String r_id) {
                this.r_id = r_id;
            }

            public String getEffect_picture() {
                return effect_picture;
            }

            public void setEffect_picture(String effect_picture) {
                this.effect_picture = effect_picture;
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

            public StoreInfoBean getStoreInfo() {
                return storeInfo;
            }

            public void setStoreInfo(StoreInfoBean storeInfo) {
                this.storeInfo = storeInfo;
            }

            public static class StoreInfoBean {
                /**
                 * s_id : 11
                 * au_id : 14
                 * s_name : 1
                 * s_logo : /pictures/fa1b7c05c6614fcdbb622394571fea9f.jpg
                 * s_type :
                 * turnover : 0
                 * bid_num : 0
                 * introduce : 我是一个粉刷匠,粉刷本领强
                 * background_info :
                 * work_experience : 2
                 * work_year : 1
                 * s_province : 北京市
                 * s_city : 北京市
                 * r_id : 1
                 * create_time : 2017-12-07 16:52:19.0
                 * applause_rate : 100
                 * comprehensive_score : 5
                 * rinfo : {"r_id":1,"r_name":"木工","r_picture":"/pictures/1.jpg","type":4}
                 */

                private int s_id;
                private String au_id;
                private String s_name;
                private String s_logo;
                private String s_type;
                private int turnover;
                private int bid_num;
                private String introduce;
                private String background_info;
                private String work_experience;
                private String work_year;
                private String s_province;
                private String s_city;
                private String r_id;
                private String create_time;
                private String applause_rate;
                private String comprehensive_score;
                private RinfoBean rinfo;

                public int getS_id() {
                    return s_id;
                }

                public void setS_id(int s_id) {
                    this.s_id = s_id;
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

                public String getS_logo() {
                    return s_logo;
                }

                public void setS_logo(String s_logo) {
                    this.s_logo = s_logo;
                }

                public String getS_type() {
                    return s_type;
                }

                public void setS_type(String s_type) {
                    this.s_type = s_type;
                }

                public int getTurnover() {
                    return turnover;
                }

                public void setTurnover(int turnover) {
                    this.turnover = turnover;
                }

                public int getBid_num() {
                    return bid_num;
                }

                public void setBid_num(int bid_num) {
                    this.bid_num = bid_num;
                }

                public String getIntroduce() {
                    return introduce;
                }

                public void setIntroduce(String introduce) {
                    this.introduce = introduce;
                }

                public String getBackground_info() {
                    return background_info;
                }

                public void setBackground_info(String background_info) {
                    this.background_info = background_info;
                }

                public String getWork_experience() {
                    return work_experience;
                }

                public void setWork_experience(String work_experience) {
                    this.work_experience = work_experience;
                }

                public String getWork_year() {
                    return work_year;
                }

                public void setWork_year(String work_year) {
                    this.work_year = work_year;
                }

                public String getS_province() {
                    return s_province;
                }

                public void setS_province(String s_province) {
                    this.s_province = s_province;
                }

                public String getS_city() {
                    return s_city;
                }

                public void setS_city(String s_city) {
                    this.s_city = s_city;
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

                public String getApplause_rate() {
                    return applause_rate;
                }

                public void setApplause_rate(String applause_rate) {
                    this.applause_rate = applause_rate;
                }

                public String getComprehensive_score() {
                    return comprehensive_score;
                }

                public void setComprehensive_score(String comprehensive_score) {
                    this.comprehensive_score = comprehensive_score;
                }

                public RinfoBean getRinfo() {
                    return rinfo;
                }

                public void setRinfo(RinfoBean rinfo) {
                    this.rinfo = rinfo;
                }

                public static class RinfoBean {
                    /**
                     * r_id : 1
                     * r_name : 木工
                     * r_picture : /pictures/1.jpg
                     * type : 4
                     */

                    private int r_id;
                    private String r_name;
                    private String r_picture;
                    private int type;

                    public int getR_id() {
                        return r_id;
                    }

                    public void setR_id(int r_id) {
                        this.r_id = r_id;
                    }

                    public String getR_name() {
                        return r_name;
                    }

                    public void setR_name(String r_name) {
                        this.r_name = r_name;
                    }

                    public String getR_picture() {
                        return r_picture;
                    }

                    public void setR_picture(String r_picture) {
                        this.r_picture = r_picture;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }
                }
            }
        }

        public static class ConstructionInfoBean {
            /**
             * ce_id : 2
             * au_id : 2
             * d_id : 50
             * title : 大苏打
             * r_id : 2
             * effect_picture : /pictures/1.jpg
             * create_time : 2017-12-11 10:25:58.0
             * upload_type : 0
             * storeInfo : {"s_id":2,"au_id":"2","s_name":"1111","s_logo":"/pictures/8a707411d0514391924a944261f351c6.jpg","s_type":"个人","turnover":0,"bid_num":0,"introduce":"我是一个粉刷匠,粉刷本领强","background_info":" ","work_experience":"11","work_year":"1","s_province":"北京市","s_city":"北京市","r_id":"1","create_time":"2017-12-07 16:52:19.0","applause_rate":"100","comprehensive_score":"5","rinfo":{"r_id":1,"r_name":"木工","r_picture":"/pictures/1.jpg","type":4}}
             */

            private int ce_id;
            private String au_id;
            private String d_id;
            private String title;
            private String r_id;
            private String effect_picture;
            private String create_time;
            private int upload_type;
            private StoreInfoBeanX storeInfo;

            public int getCe_id() {
                return ce_id;
            }

            public void setCe_id(int ce_id) {
                this.ce_id = ce_id;
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

            public String getR_id() {
                return r_id;
            }

            public void setR_id(String r_id) {
                this.r_id = r_id;
            }

            public String getEffect_picture() {
                return effect_picture;
            }

            public void setEffect_picture(String effect_picture) {
                this.effect_picture = effect_picture;
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

            public StoreInfoBeanX getStoreInfo() {
                return storeInfo;
            }

            public void setStoreInfo(StoreInfoBeanX storeInfo) {
                this.storeInfo = storeInfo;
            }

            public static class StoreInfoBeanX {
                /**
                 * s_id : 2
                 * au_id : 2
                 * s_name : 1111
                 * s_logo : /pictures/8a707411d0514391924a944261f351c6.jpg
                 * s_type : 个人
                 * turnover : 0
                 * bid_num : 0
                 * introduce : 我是一个粉刷匠,粉刷本领强
                 * background_info :
                 * work_experience : 11
                 * work_year : 1
                 * s_province : 北京市
                 * s_city : 北京市
                 * r_id : 1
                 * create_time : 2017-12-07 16:52:19.0
                 * applause_rate : 100
                 * comprehensive_score : 5
                 * rinfo : {"r_id":1,"r_name":"木工","r_picture":"/pictures/1.jpg","type":4}
                 */

                private int s_id;
                private String au_id;
                private String s_name;
                private String s_logo;
                private String s_type;
                private int turnover;
                private int bid_num;
                private String introduce;
                private String background_info;
                private String work_experience;
                private String work_year;
                private String s_province;
                private String s_city;
                private String r_id;
                private String create_time;
                private String applause_rate;
                private String comprehensive_score;
                private RinfoBeanX rinfo;

                public int getS_id() {
                    return s_id;
                }

                public void setS_id(int s_id) {
                    this.s_id = s_id;
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

                public String getS_logo() {
                    return s_logo;
                }

                public void setS_logo(String s_logo) {
                    this.s_logo = s_logo;
                }

                public String getS_type() {
                    return s_type;
                }

                public void setS_type(String s_type) {
                    this.s_type = s_type;
                }

                public int getTurnover() {
                    return turnover;
                }

                public void setTurnover(int turnover) {
                    this.turnover = turnover;
                }

                public int getBid_num() {
                    return bid_num;
                }

                public void setBid_num(int bid_num) {
                    this.bid_num = bid_num;
                }

                public String getIntroduce() {
                    return introduce;
                }

                public void setIntroduce(String introduce) {
                    this.introduce = introduce;
                }

                public String getBackground_info() {
                    return background_info;
                }

                public void setBackground_info(String background_info) {
                    this.background_info = background_info;
                }

                public String getWork_experience() {
                    return work_experience;
                }

                public void setWork_experience(String work_experience) {
                    this.work_experience = work_experience;
                }

                public String getWork_year() {
                    return work_year;
                }

                public void setWork_year(String work_year) {
                    this.work_year = work_year;
                }

                public String getS_province() {
                    return s_province;
                }

                public void setS_province(String s_province) {
                    this.s_province = s_province;
                }

                public String getS_city() {
                    return s_city;
                }

                public void setS_city(String s_city) {
                    this.s_city = s_city;
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

                public String getApplause_rate() {
                    return applause_rate;
                }

                public void setApplause_rate(String applause_rate) {
                    this.applause_rate = applause_rate;
                }

                public String getComprehensive_score() {
                    return comprehensive_score;
                }

                public void setComprehensive_score(String comprehensive_score) {
                    this.comprehensive_score = comprehensive_score;
                }

                public RinfoBeanX getRinfo() {
                    return rinfo;
                }

                public void setRinfo(RinfoBeanX rinfo) {
                    this.rinfo = rinfo;
                }

                public static class RinfoBeanX {
                    /**
                     * r_id : 1
                     * r_name : 木工
                     * r_picture : /pictures/1.jpg
                     * type : 4
                     */

                    private int r_id;
                    private String r_name;
                    private String r_picture;
                    private int type;

                    public int getR_id() {
                        return r_id;
                    }

                    public void setR_id(int r_id) {
                        this.r_id = r_id;
                    }

                    public String getR_name() {
                        return r_name;
                    }

                    public void setR_name(String r_name) {
                        this.r_name = r_name;
                    }

                    public String getR_picture() {
                        return r_picture;
                    }

                    public void setR_picture(String r_picture) {
                        this.r_picture = r_picture;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }
                }
            }
        }

        public static class SupervisorInfoBean {
            /**
             * ce_id : 1
             * au_id : 1
             * d_id : 50
             * title : 大苏打
             * r_id : 1
             * effect_picture : /pictures/1.jpg
             * create_time : 2017-12-11 10:25:58.0
             * upload_type : 0
             * storeInfo : {"s_id":1,"au_id":"1","s_name":"小月的材料屋","s_logo":"/pictures/1.jpg","s_type":"","turnover":0,"bid_num":0,"introduce":"真诚服务","background_info":"","work_experience":"","work_year":"1","s_province":"辽宁","s_city":"沈阳","r_id":"1","create_time":"2017-12-07 16:52:19.0","applause_rate":"100","comprehensive_score":"5","rinfo":{"r_id":1,"r_name":"木工","r_picture":"/pictures/1.jpg","type":4}}
             */

            private int ce_id;
            private String au_id;
            private String d_id;
            private String title;
            private String r_id;
            private String effect_picture;
            private String create_time;
            private int upload_type;
            private StoreInfoBeanXX storeInfo;

            public int getCe_id() {
                return ce_id;
            }

            public void setCe_id(int ce_id) {
                this.ce_id = ce_id;
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

            public String getR_id() {
                return r_id;
            }

            public void setR_id(String r_id) {
                this.r_id = r_id;
            }

            public String getEffect_picture() {
                return effect_picture;
            }

            public void setEffect_picture(String effect_picture) {
                this.effect_picture = effect_picture;
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

            public StoreInfoBeanXX getStoreInfo() {
                return storeInfo;
            }

            public void setStoreInfo(StoreInfoBeanXX storeInfo) {
                this.storeInfo = storeInfo;
            }

            public static class StoreInfoBeanXX {
                /**
                 * s_id : 1
                 * au_id : 1
                 * s_name : 小月的材料屋
                 * s_logo : /pictures/1.jpg
                 * s_type :
                 * turnover : 0
                 * bid_num : 0
                 * introduce : 真诚服务
                 * background_info :
                 * work_experience :
                 * work_year : 1
                 * s_province : 辽宁
                 * s_city : 沈阳
                 * r_id : 1
                 * create_time : 2017-12-07 16:52:19.0
                 * applause_rate : 100
                 * comprehensive_score : 5
                 * rinfo : {"r_id":1,"r_name":"木工","r_picture":"/pictures/1.jpg","type":4}
                 */

                private int s_id;
                private String au_id;
                private String s_name;
                private String s_logo;
                private String s_type;
                private int turnover;
                private int bid_num;
                private String introduce;
                private String background_info;
                private String work_experience;
                private String work_year;
                private String s_province;
                private String s_city;
                private String r_id;
                private String create_time;
                private String applause_rate;
                private String comprehensive_score;
                private RinfoBeanXX rinfo;

                public int getS_id() {
                    return s_id;
                }

                public void setS_id(int s_id) {
                    this.s_id = s_id;
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

                public String getS_logo() {
                    return s_logo;
                }

                public void setS_logo(String s_logo) {
                    this.s_logo = s_logo;
                }

                public String getS_type() {
                    return s_type;
                }

                public void setS_type(String s_type) {
                    this.s_type = s_type;
                }

                public int getTurnover() {
                    return turnover;
                }

                public void setTurnover(int turnover) {
                    this.turnover = turnover;
                }

                public int getBid_num() {
                    return bid_num;
                }

                public void setBid_num(int bid_num) {
                    this.bid_num = bid_num;
                }

                public String getIntroduce() {
                    return introduce;
                }

                public void setIntroduce(String introduce) {
                    this.introduce = introduce;
                }

                public String getBackground_info() {
                    return background_info;
                }

                public void setBackground_info(String background_info) {
                    this.background_info = background_info;
                }

                public String getWork_experience() {
                    return work_experience;
                }

                public void setWork_experience(String work_experience) {
                    this.work_experience = work_experience;
                }

                public String getWork_year() {
                    return work_year;
                }

                public void setWork_year(String work_year) {
                    this.work_year = work_year;
                }

                public String getS_province() {
                    return s_province;
                }

                public void setS_province(String s_province) {
                    this.s_province = s_province;
                }

                public String getS_city() {
                    return s_city;
                }

                public void setS_city(String s_city) {
                    this.s_city = s_city;
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

                public String getApplause_rate() {
                    return applause_rate;
                }

                public void setApplause_rate(String applause_rate) {
                    this.applause_rate = applause_rate;
                }

                public String getComprehensive_score() {
                    return comprehensive_score;
                }

                public void setComprehensive_score(String comprehensive_score) {
                    this.comprehensive_score = comprehensive_score;
                }

                public RinfoBeanXX getRinfo() {
                    return rinfo;
                }

                public void setRinfo(RinfoBeanXX rinfo) {
                    this.rinfo = rinfo;
                }

                public static class RinfoBeanXX {
                    /**
                     * r_id : 1
                     * r_name : 木工
                     * r_picture : /pictures/1.jpg
                     * type : 4
                     */

                    private int r_id;
                    private String r_name;
                    private String r_picture;
                    private int type;

                    public int getR_id() {
                        return r_id;
                    }

                    public void setR_id(int r_id) {
                        this.r_id = r_id;
                    }

                    public String getR_name() {
                        return r_name;
                    }

                    public void setR_name(String r_name) {
                        this.r_name = r_name;
                    }

                    public String getR_picture() {
                        return r_picture;
                    }

                    public void setR_picture(String r_picture) {
                        this.r_picture = r_picture;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }
                }
            }
        }

        public static class MaterialInfoBean {
            /**
             * ce_id : 3
             * au_id : 9
             * d_id : 50
             * title : 大苏打
             * r_id : 3
             * effect_picture : /pictures/1.jpg
             * create_time : 2017-12-11 10:25:58.0
             * upload_type : 0
             * storeInfo : {"s_id":6,"au_id":"9","s_name":"1","s_logo":"/pictures/fa1b7c05c6614fcdbb622394571fea9f.jpg","s_type":"","turnover":0,"bid_num":0,"introduce":"我是一个粉刷匠,粉刷本领强","background_info":" ","work_experience":"2","work_year":"1","s_province":"北京市","s_city":"北京市","r_id":"1","create_time":"2017-12-07 16:52:19.0","applause_rate":"100","comprehensive_score":"5","rinfo":{"r_id":1,"r_name":"木工","r_picture":"/pictures/1.jpg","type":4}}
             */

            private int ce_id;
            private String au_id;
            private String d_id;
            private String title;
            private String r_id;
            private String effect_picture;
            private String create_time;
            private int upload_type;
            private StoreInfoBeanXXX storeInfo;

            public int getCe_id() {
                return ce_id;
            }

            public void setCe_id(int ce_id) {
                this.ce_id = ce_id;
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

            public String getR_id() {
                return r_id;
            }

            public void setR_id(String r_id) {
                this.r_id = r_id;
            }

            public String getEffect_picture() {
                return effect_picture;
            }

            public void setEffect_picture(String effect_picture) {
                this.effect_picture = effect_picture;
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

            public StoreInfoBeanXXX getStoreInfo() {
                return storeInfo;
            }

            public void setStoreInfo(StoreInfoBeanXXX storeInfo) {
                this.storeInfo = storeInfo;
            }

            public static class StoreInfoBeanXXX {
                /**
                 * s_id : 6
                 * au_id : 9
                 * s_name : 1
                 * s_logo : /pictures/fa1b7c05c6614fcdbb622394571fea9f.jpg
                 * s_type :
                 * turnover : 0
                 * bid_num : 0
                 * introduce : 我是一个粉刷匠,粉刷本领强
                 * background_info :
                 * work_experience : 2
                 * work_year : 1
                 * s_province : 北京市
                 * s_city : 北京市
                 * r_id : 1
                 * create_time : 2017-12-07 16:52:19.0
                 * applause_rate : 100
                 * comprehensive_score : 5
                 * rinfo : {"r_id":1,"r_name":"木工","r_picture":"/pictures/1.jpg","type":4}
                 */

                private int s_id;
                private String au_id;
                private String s_name;
                private String s_logo;
                private String s_type;
                private int turnover;
                private int bid_num;
                private String introduce;
                private String background_info;
                private String work_experience;
                private String work_year;
                private String s_province;
                private String s_city;
                private String r_id;
                private String create_time;
                private String applause_rate;
                private String comprehensive_score;
                private RinfoBeanXXX rinfo;

                public int getS_id() {
                    return s_id;
                }

                public void setS_id(int s_id) {
                    this.s_id = s_id;
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

                public String getS_logo() {
                    return s_logo;
                }

                public void setS_logo(String s_logo) {
                    this.s_logo = s_logo;
                }

                public String getS_type() {
                    return s_type;
                }

                public void setS_type(String s_type) {
                    this.s_type = s_type;
                }

                public int getTurnover() {
                    return turnover;
                }

                public void setTurnover(int turnover) {
                    this.turnover = turnover;
                }

                public int getBid_num() {
                    return bid_num;
                }

                public void setBid_num(int bid_num) {
                    this.bid_num = bid_num;
                }

                public String getIntroduce() {
                    return introduce;
                }

                public void setIntroduce(String introduce) {
                    this.introduce = introduce;
                }

                public String getBackground_info() {
                    return background_info;
                }

                public void setBackground_info(String background_info) {
                    this.background_info = background_info;
                }

                public String getWork_experience() {
                    return work_experience;
                }

                public void setWork_experience(String work_experience) {
                    this.work_experience = work_experience;
                }

                public String getWork_year() {
                    return work_year;
                }

                public void setWork_year(String work_year) {
                    this.work_year = work_year;
                }

                public String getS_province() {
                    return s_province;
                }

                public void setS_province(String s_province) {
                    this.s_province = s_province;
                }

                public String getS_city() {
                    return s_city;
                }

                public void setS_city(String s_city) {
                    this.s_city = s_city;
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

                public String getApplause_rate() {
                    return applause_rate;
                }

                public void setApplause_rate(String applause_rate) {
                    this.applause_rate = applause_rate;
                }

                public String getComprehensive_score() {
                    return comprehensive_score;
                }

                public void setComprehensive_score(String comprehensive_score) {
                    this.comprehensive_score = comprehensive_score;
                }

                public RinfoBeanXXX getRinfo() {
                    return rinfo;
                }

                public void setRinfo(RinfoBeanXXX rinfo) {
                    this.rinfo = rinfo;
                }

                public static class RinfoBeanXXX {
                    /**
                     * r_id : 1
                     * r_name : 木工
                     * r_picture : /pictures/1.jpg
                     * type : 4
                     */

                    private int r_id;
                    private String r_name;
                    private String r_picture;
                    private int type;

                    public int getR_id() {
                        return r_id;
                    }

                    public void setR_id(int r_id) {
                        this.r_id = r_id;
                    }

                    public String getR_name() {
                        return r_name;
                    }

                    public void setR_name(String r_name) {
                        this.r_name = r_name;
                    }

                    public String getR_picture() {
                        return r_picture;
                    }

                    public void setR_picture(String r_picture) {
                        this.r_picture = r_picture;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }
                }
            }
        }
    }
}

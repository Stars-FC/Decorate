package com.ygc.estatedecoration.bean;

import java.util.List;

/**
 * Created by FC on 2017/12/28.
 * 用户，我的收藏-效果图
 */

public class UserCollectionResultChartBean {

    /**
     * responseState : 1
     * msg : 查询成功
     * data : [{"uc_id":1,"auId":"1","articleId":"50","articleType":1,"createTime":"2017-12-19 14:55:34.0","article":{"d_id":"50","ce_id_designer":"4","ce_id_construction":"2","ce_id_supervisor":"1","ce_id_material":"3","designerInfo":{"ce_id":4,"au_id":"14","d_id":"50","title":"大苏打","r_id":"2","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":null},"constructionInfo":{"ce_id":2,"au_id":"2","d_id":"50","title":"大苏打","r_id":"2","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":null},"supervisorInfo":{"ce_id":1,"au_id":"1","d_id":"50","title":"大苏打","r_id":"1","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":null},"materialInfo":{"ce_id":3,"au_id":"9","d_id":"50","title":"大苏打","r_id":"3","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":null},"collect":true,"title":"标题","effect_picture":"/pictures/1.jpg"}}]
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
         * uc_id : 1
         * auId : 1
         * articleId : 50
         * articleType : 1
         * createTime : 2017-12-19 14:55:34.0
         * article : {"d_id":"50","ce_id_designer":"4","ce_id_construction":"2","ce_id_supervisor":"1","ce_id_material":"3","designerInfo":{"ce_id":4,"au_id":"14","d_id":"50","title":"大苏打","r_id":"2","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":null},"constructionInfo":{"ce_id":2,"au_id":"2","d_id":"50","title":"大苏打","r_id":"2","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":null},"supervisorInfo":{"ce_id":1,"au_id":"1","d_id":"50","title":"大苏打","r_id":"1","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":null},"materialInfo":{"ce_id":3,"au_id":"9","d_id":"50","title":"大苏打","r_id":"3","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":null},"collect":true,"title":"标题","effect_picture":"/pictures/1.jpg"}
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
             * d_id : 50
             * ce_id_designer : 4
             * ce_id_construction : 2
             * ce_id_supervisor : 1
             * ce_id_material : 3
             * designerInfo : {"ce_id":4,"au_id":"14","d_id":"50","title":"大苏打","r_id":"2","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":null}
             * constructionInfo : {"ce_id":2,"au_id":"2","d_id":"50","title":"大苏打","r_id":"2","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":null}
             * supervisorInfo : {"ce_id":1,"au_id":"1","d_id":"50","title":"大苏打","r_id":"1","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":null}
             * materialInfo : {"ce_id":3,"au_id":"9","d_id":"50","title":"大苏打","r_id":"3","effect_picture":"/pictures/1.jpg","create_time":"2017-12-11 10:25:58.0","upload_type":0,"storeInfo":null}
             * collect : true
             * title : 标题
             * effect_picture : /pictures/1.jpg
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
            private String title;
            private String effect_picture;

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getEffect_picture() {
                return effect_picture;
            }

            public void setEffect_picture(String effect_picture) {
                this.effect_picture = effect_picture;
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
                 * storeInfo : null
                 */

                private int ce_id;
                private String au_id;
                private String d_id;
                private String title;
                private String r_id;
                private String effect_picture;
                private String create_time;
                private int upload_type;
                private Object storeInfo;

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

                public Object getStoreInfo() {
                    return storeInfo;
                }

                public void setStoreInfo(Object storeInfo) {
                    this.storeInfo = storeInfo;
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
                 * storeInfo : null
                 */

                private int ce_id;
                private String au_id;
                private String d_id;
                private String title;
                private String r_id;
                private String effect_picture;
                private String create_time;
                private int upload_type;
                private Object storeInfo;

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

                public Object getStoreInfo() {
                    return storeInfo;
                }

                public void setStoreInfo(Object storeInfo) {
                    this.storeInfo = storeInfo;
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
                 * storeInfo : null
                 */

                private int ce_id;
                private String au_id;
                private String d_id;
                private String title;
                private String r_id;
                private String effect_picture;
                private String create_time;
                private int upload_type;
                private Object storeInfo;

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

                public Object getStoreInfo() {
                    return storeInfo;
                }

                public void setStoreInfo(Object storeInfo) {
                    this.storeInfo = storeInfo;
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
                 * storeInfo : null
                 */

                private int ce_id;
                private String au_id;
                private String d_id;
                private String title;
                private String r_id;
                private String effect_picture;
                private String create_time;
                private int upload_type;
                private Object storeInfo;

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

                public Object getStoreInfo() {
                    return storeInfo;
                }

                public void setStoreInfo(Object storeInfo) {
                    this.storeInfo = storeInfo;
                }
            }
        }
    }
}

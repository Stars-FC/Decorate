package com.ygc.estatedecoration.bean;

import java.util.List;

public class ScheduleBean{

    /**
     * responseState : 1
     * msg : 操作成功
     * data : [{"time":"2017-12-18 13:51:23.0","detail":"雇主已经与您达成意向合同已发起","title":"确认工作"},{"data":[{"conId":"80fd626fed074da28554b4f5ecc42910","conState":1,"time":"1970-01-18 20:20:21","detail":"主体合同已确认"},{"rcState":0,"conId":"80fd626fed074da28554b4f5ecc42910","time":"2017-12-07 15:32:47.0","detail":"补充合同已发起","rcId":"81226bb7482a46fda7a72b54214f77f4"},{"rcState":0,"conId":"80fd626fed074da28554b4f5ecc42910","time":"2017-12-07 15:37:48.0","detail":"补充合同已发起","rcId":"c6fac26192ff4f109b4c342368868e70"},{"rcState":0,"conId":"80fd626fed074da28554b4f5ecc42910","time":"2017-12-07 15:42:56.0","detail":"补充合同已发起","rcId":"64f4694158a44f7aa3048a35213cf83f"},{"rcState":0,"conId":"80fd626fed074da28554b4f5ecc42910","time":"2017-12-07 15:43:36.0","detail":"补充合同已发起","rcId":"735bd73b8c994c2d9f4fca68f0b8ec0c"},{"rcState":0,"conId":"80fd626fed074da28554b4f5ecc42910","time":"2017-12-07 15:52:39.0","detail":"补充合同已发起","rcId":"273bac11d42b4897a40c5a79d49d0dfd"},{"rcState":0,"conId":"80fd626fed074da28554b4f5ecc42910","time":"2017-12-07 15:53:40.0","detail":"补充合同已发起","rcId":"b4cc884c629742fc8416179048c250f2"}],"title":"合同签署"},{"contractStageList":[{"consId":"234243324234123412341234213423","conId":"80fd626fed074da28554b4f5ecc42910","title":"阶段1","detail":"阶段1阶段1阶段1阶段1阶段1阶段1阶段1的内容","price":55555,"needDays":90,"sign":0,"csState":0,"createTime":"2017-12-14 09:18:55.0"},{"consId":"16858f0b133b40c4b952774ac388cdcf","conId":"80fd626fed074da28554b4f5ecc42910","title":"阶段2","detail":"内容2","price":5000,"needDays":10,"sign":1,"csState":0,"createTime":"2017-12-13 16:54:48.0"},{"consId":"d833cb0f6fe04fab8815228445a31ed8","conId":"80fd626fed074da28554b4f5ecc42910","title":"阶段3","detail":"内容3","price":6000,"needDays":10,"sign":2,"csState":0,"createTime":"2017-12-13 16:54:51.0"}],"title":"服务商进度","replenishContractList":[{"rcId":"81226bb7482a46fda7a72b54214f77f4","conId":"80fd626fed074da28554b4f5ecc42910","title":"厕所贴砖","detail":"大理磁砖","price":1000,"needDays":7,"sign":0,"replenishDetail":"舔砖熬","accessory":"","createTime":"2017-12-07 15:32:47.0","confirmTime":"0","rcState":0},{"rcId":"c6fac26192ff4f109b4c342368868e70","conId":"80fd626fed074da28554b4f5ecc42910","title":"厕所贴砖","detail":"大理磁砖","price":1000,"needDays":7,"sign":0,"replenishDetail":"舔砖熬","accessory":"","createTime":"2017-12-07 15:37:48.0","confirmTime":"0","rcState":0},{"rcId":"64f4694158a44f7aa3048a35213cf83f","conId":"80fd626fed074da28554b4f5ecc42910","title":"厕所贴砖","detail":"大理磁砖","price":1000,"needDays":7,"sign":0,"replenishDetail":"舔砖熬","accessory":"","createTime":"2017-12-07 15:42:56.0","confirmTime":"0","rcState":0},{"rcId":"735bd73b8c994c2d9f4fca68f0b8ec0c","conId":"80fd626fed074da28554b4f5ecc42910","title":"厕所贴砖","detail":"大理磁砖","price":1000,"needDays":7,"sign":0,"replenishDetail":"舔砖熬","accessory":"file/11/20171207154318-e54415b93639068da793f6.pdf","createTime":"2017-12-07 15:43:36.0","confirmTime":"0","rcState":0},{"rcId":"273bac11d42b4897a40c5a79d49d0dfd","conId":"80fd626fed074da28554b4f5ecc42910","title":"厕所贴砖","detail":"大理磁砖","price":1000,"needDays":7,"sign":0,"replenishDetail":"舔砖熬","accessory":"file/11/20171207155219-454699ba5237f67c78fb91.jpg,file/11/20171207155219-2649d2a1886376423a41b3.jpg","createTime":"2017-12-07 15:52:39.0","confirmTime":"0","rcState":0},{"rcId":"b4cc884c629742fc8416179048c250f2","conId":"80fd626fed074da28554b4f5ecc42910","title":"厕所贴砖","detail":"大理磁砖","price":1000,"needDays":7,"sign":0,"replenishDetail":"舔砖熬","accessory":"file/11/20171207155322-2240fd91adc066edc036a0.jpg,file/11/20171207155322-3140e2b4df9cfd988594a6.jpg","createTime":"2017-12-07 15:53:40.0","confirmTime":"0","rcState":0}]}]
     */

    private String responseState;
    private String msg;
    private List<DataBeanX> data;

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

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * time : 2017-12-18 13:51:23.0
         * detail : 雇主已经与您达成意向合同已发起
         * title : 确认工作
         * data : [{"conId":"80fd626fed074da28554b4f5ecc42910","conState":1,"time":"1970-01-18 20:20:21","detail":"主体合同已确认"},{"rcState":0,"conId":"80fd626fed074da28554b4f5ecc42910","time":"2017-12-07 15:32:47.0","detail":"补充合同已发起","rcId":"81226bb7482a46fda7a72b54214f77f4"},{"rcState":0,"conId":"80fd626fed074da28554b4f5ecc42910","time":"2017-12-07 15:37:48.0","detail":"补充合同已发起","rcId":"c6fac26192ff4f109b4c342368868e70"},{"rcState":0,"conId":"80fd626fed074da28554b4f5ecc42910","time":"2017-12-07 15:42:56.0","detail":"补充合同已发起","rcId":"64f4694158a44f7aa3048a35213cf83f"},{"rcState":0,"conId":"80fd626fed074da28554b4f5ecc42910","time":"2017-12-07 15:43:36.0","detail":"补充合同已发起","rcId":"735bd73b8c994c2d9f4fca68f0b8ec0c"},{"rcState":0,"conId":"80fd626fed074da28554b4f5ecc42910","time":"2017-12-07 15:52:39.0","detail":"补充合同已发起","rcId":"273bac11d42b4897a40c5a79d49d0dfd"},{"rcState":0,"conId":"80fd626fed074da28554b4f5ecc42910","time":"2017-12-07 15:53:40.0","detail":"补充合同已发起","rcId":"b4cc884c629742fc8416179048c250f2"}]
         * contractStageList : [{"consId":"234243324234123412341234213423","conId":"80fd626fed074da28554b4f5ecc42910","title":"阶段1","detail":"阶段1阶段1阶段1阶段1阶段1阶段1阶段1的内容","price":55555,"needDays":90,"sign":0,"csState":0,"createTime":"2017-12-14 09:18:55.0"},{"consId":"16858f0b133b40c4b952774ac388cdcf","conId":"80fd626fed074da28554b4f5ecc42910","title":"阶段2","detail":"内容2","price":5000,"needDays":10,"sign":1,"csState":0,"createTime":"2017-12-13 16:54:48.0"},{"consId":"d833cb0f6fe04fab8815228445a31ed8","conId":"80fd626fed074da28554b4f5ecc42910","title":"阶段3","detail":"内容3","price":6000,"needDays":10,"sign":2,"csState":0,"createTime":"2017-12-13 16:54:51.0"}]
         * replenishContractList : [{"rcId":"81226bb7482a46fda7a72b54214f77f4","conId":"80fd626fed074da28554b4f5ecc42910","title":"厕所贴砖","detail":"大理磁砖","price":1000,"needDays":7,"sign":0,"replenishDetail":"舔砖熬","accessory":"","createTime":"2017-12-07 15:32:47.0","confirmTime":"0","rcState":0},{"rcId":"c6fac26192ff4f109b4c342368868e70","conId":"80fd626fed074da28554b4f5ecc42910","title":"厕所贴砖","detail":"大理磁砖","price":1000,"needDays":7,"sign":0,"replenishDetail":"舔砖熬","accessory":"","createTime":"2017-12-07 15:37:48.0","confirmTime":"0","rcState":0},{"rcId":"64f4694158a44f7aa3048a35213cf83f","conId":"80fd626fed074da28554b4f5ecc42910","title":"厕所贴砖","detail":"大理磁砖","price":1000,"needDays":7,"sign":0,"replenishDetail":"舔砖熬","accessory":"","createTime":"2017-12-07 15:42:56.0","confirmTime":"0","rcState":0},{"rcId":"735bd73b8c994c2d9f4fca68f0b8ec0c","conId":"80fd626fed074da28554b4f5ecc42910","title":"厕所贴砖","detail":"大理磁砖","price":1000,"needDays":7,"sign":0,"replenishDetail":"舔砖熬","accessory":"file/11/20171207154318-e54415b93639068da793f6.pdf","createTime":"2017-12-07 15:43:36.0","confirmTime":"0","rcState":0},{"rcId":"273bac11d42b4897a40c5a79d49d0dfd","conId":"80fd626fed074da28554b4f5ecc42910","title":"厕所贴砖","detail":"大理磁砖","price":1000,"needDays":7,"sign":0,"replenishDetail":"舔砖熬","accessory":"file/11/20171207155219-454699ba5237f67c78fb91.jpg,file/11/20171207155219-2649d2a1886376423a41b3.jpg","createTime":"2017-12-07 15:52:39.0","confirmTime":"0","rcState":0},{"rcId":"b4cc884c629742fc8416179048c250f2","conId":"80fd626fed074da28554b4f5ecc42910","title":"厕所贴砖","detail":"大理磁砖","price":1000,"needDays":7,"sign":0,"replenishDetail":"舔砖熬","accessory":"file/11/20171207155322-2240fd91adc066edc036a0.jpg,file/11/20171207155322-3140e2b4df9cfd988594a6.jpg","createTime":"2017-12-07 15:53:40.0","confirmTime":"0","rcState":0}]
         */

        private String time;
        private String detail;
        private String title;
        private List<DataBean> data;
        private List<ContractStageListBean> contractStageList;
        private List<ReplenishContractListBean> replenishContractList;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public List<ContractStageListBean> getContractStageList() {
            return contractStageList;
        }

        public void setContractStageList(List<ContractStageListBean> contractStageList) {
            this.contractStageList = contractStageList;
        }

        public List<ReplenishContractListBean> getReplenishContractList() {
            return replenishContractList;
        }

        public void setReplenishContractList(List<ReplenishContractListBean> replenishContractList) {
            this.replenishContractList = replenishContractList;
        }

        public static class DataBean {
            /**
             * conId : 80fd626fed074da28554b4f5ecc42910
             * conState : 1
             * time : 1970-01-18 20:20:21
             * detail : 主体合同已确认
             * rcState : 0
             * rcId : 81226bb7482a46fda7a72b54214f77f4
             */

            private String conId;
            private int conState;
            private String time;
            private String detail;
            private int rcState;
            private String rcId;

            public String getConId() {
                return conId;
            }

            public void setConId(String conId) {
                this.conId = conId;
            }

            public int getConState() {
                return conState;
            }

            public void setConState(int conState) {
                this.conState = conState;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public int getRcState() {
                return rcState;
            }

            public void setRcState(int rcState) {
                this.rcState = rcState;
            }

            public String getRcId() {
                return rcId;
            }

            public void setRcId(String rcId) {
                this.rcId = rcId;
            }
        }

        public static class ContractStageListBean {
            /**
             * consId : 234243324234123412341234213423
             * conId : 80fd626fed074da28554b4f5ecc42910
             * title : 阶段1
             * detail : 阶段1阶段1阶段1阶段1阶段1阶段1阶段1的内容
             * price : 55555
             * needDays : 90
             * sign : 0
             * csState : 0
             * createTime : 2017-12-14 09:18:55.0
             */

            private String consId;
            private String conId;
            private String title;
            private String detail;
            private int price;
            private int needDays;
            private int sign;
            private int csState;
            private String createTime;

            public String getConsId() {
                return consId;
            }

            public void setConsId(String consId) {
                this.consId = consId;
            }

            public String getConId() {
                return conId;
            }

            public void setConId(String conId) {
                this.conId = conId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getNeedDays() {
                return needDays;
            }

            public void setNeedDays(int needDays) {
                this.needDays = needDays;
            }

            public int getSign() {
                return sign;
            }

            public void setSign(int sign) {
                this.sign = sign;
            }

            public int getCsState() {
                return csState;
            }

            public void setCsState(int csState) {
                this.csState = csState;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }

        public static class ReplenishContractListBean {
            /**
             * rcId : 81226bb7482a46fda7a72b54214f77f4
             * conId : 80fd626fed074da28554b4f5ecc42910
             * title : 厕所贴砖
             * detail : 大理磁砖
             * price : 1000
             * needDays : 7
             * sign : 0
             * replenishDetail : 舔砖熬
             * accessory :
             * createTime : 2017-12-07 15:32:47.0
             * confirmTime : 0
             * rcState : 0
             */

            private String rcId;
            private String conId;
            private String title;
            private String detail;
            private int price;
            private int needDays;
            private int sign;
            private String replenishDetail;
            private String accessory;
            private String createTime;
            private String confirmTime;
            private int rcState;

            public String getRcId() {
                return rcId;
            }

            public void setRcId(String rcId) {
                this.rcId = rcId;
            }

            public String getConId() {
                return conId;
            }

            public void setConId(String conId) {
                this.conId = conId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getNeedDays() {
                return needDays;
            }

            public void setNeedDays(int needDays) {
                this.needDays = needDays;
            }

            public int getSign() {
                return sign;
            }

            public void setSign(int sign) {
                this.sign = sign;
            }

            public String getReplenishDetail() {
                return replenishDetail;
            }

            public void setReplenishDetail(String replenishDetail) {
                this.replenishDetail = replenishDetail;
            }

            public String getAccessory() {
                return accessory;
            }

            public void setAccessory(String accessory) {
                this.accessory = accessory;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getConfirmTime() {
                return confirmTime;
            }

            public void setConfirmTime(String confirmTime) {
                this.confirmTime = confirmTime;
            }

            public int getRcState() {
                return rcState;
            }

            public void setRcState(int rcState) {
                this.rcState = rcState;
            }
        }
    }
}

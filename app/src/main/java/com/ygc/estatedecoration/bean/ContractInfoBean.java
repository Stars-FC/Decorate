package com.ygc.estatedecoration.bean;

import com.ygc.estatedecoration.entity.base.Base;

import java.io.Serializable;
import java.util.List;

public class ContractInfoBean extends Base {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * conId : 80fd626fed074da28554b4f5ecc42910
         * contractDetail : 合同主体
         * contractStageList : [{"consId":"d833cb0f6fe04fab8815228445a31ed8","conId":"80fd626fed074da28554b4f5ecc42910","title":"阶段3","detail":"内容3","price":6000,"needDays":10,"sign":0,"csState":0,"createTime":"2017-12-07 10:56:56.0"}]
         * confirmId : 2
         * dId : 1
         * totalPrice : 10000
         * startTime : 2017050013
         * endTime : 20645811
         * needTime : 2012244555
         * qualityGuaranteeDeposit : 1000
         * qgdState : 0
         * contractState : 0
         * createTime : 2017-12-07 15:14:30.0
         * category : 2
         * serviceProvidersId : 1
         * replenishContract : [{"rcId":"4968b0b8748a485ba5eed5cd057b530c","conId":"80fd626fed074da28554b4f5ecc42910","title":"厕所贴砖","detail":"大理磁砖","price":1000,"needDays":7,"sign":0,"replenishDetail":"舔砖熬","accessory":"","createTime":"2017-12-07 14:17:09.0","rcState":"0"}]
         */

        private String conId;
        private String contractDetail;
        private int confirmId;
        private String dId;
        private int totalPrice;
        private String startTime;
        private String endTime;
        private String needTime;
        private int qualityGuaranteeDeposit;
        private int qgdState;
        private int contractState;
        private String createTime;
        private int category;
        private int serviceProvidersId;
        private List<ContractStageListBean> contractStageList;
        private List<ReplenishContractBean> replenishContract;

        public String getConId() {
            return conId;
        }

        public void setConId(String conId) {
            this.conId = conId;
        }

        public String getContractDetail() {
            return contractDetail;
        }

        public void setContractDetail(String contractDetail) {
            this.contractDetail = contractDetail;
        }

        public int getConfirmId() {
            return confirmId;
        }

        public void setConfirmId(int confirmId) {
            this.confirmId = confirmId;
        }

        public String getDId() {
            return dId;
        }

        public void setDId(String dId) {
            this.dId = dId;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getNeedTime() {
            return needTime;
        }

        public void setNeedTime(String needTime) {
            this.needTime = needTime;
        }

        public int getQualityGuaranteeDeposit() {
            return qualityGuaranteeDeposit;
        }

        public void setQualityGuaranteeDeposit(int qualityGuaranteeDeposit) {
            this.qualityGuaranteeDeposit = qualityGuaranteeDeposit;
        }

        public int getQgdState() {
            return qgdState;
        }

        public void setQgdState(int qgdState) {
            this.qgdState = qgdState;
        }

        public int getContractState() {
            return contractState;
        }

        public void setContractState(int contractState) {
            this.contractState = contractState;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public int getServiceProvidersId() {
            return serviceProvidersId;
        }

        public void setServiceProvidersId(int serviceProvidersId) {
            this.serviceProvidersId = serviceProvidersId;
        }

        public List<ContractStageListBean> getContractStageList() {
            return contractStageList;
        }

        public void setContractStageList(List<ContractStageListBean> contractStageList) {
            this.contractStageList = contractStageList;
        }

        public List<ReplenishContractBean> getReplenishContract() {
            return replenishContract;
        }

        public void setReplenishContract(List<ReplenishContractBean> replenishContract) {
            this.replenishContract = replenishContract;
        }

        public static class ContractStageListBean implements Serializable{
            /**
             * consId : d833cb0f6fe04fab8815228445a31ed8
             * conId : 80fd626fed074da28554b4f5ecc42910
             * title : 阶段3
             * detail : 内容3
             * price : 6000
             * needDays : 10
             * sign : 0
             * csState : 0
             * createTime : 2017-12-07 10:56:56.0
             */

            private String consId;
            private String conId;
            private String title;
            private String detail;
            private String price;
            private int needDays;
            private int sign;
            private int csState;
            private String createTime;
            private String stageStartTime;
            private String stageEndTime;

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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
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

            public String getStageStartTime() {
                return stageStartTime;
            }

            public void setStageStartTime(String stageStartTime) {
                this.stageStartTime = stageStartTime;
            }

            public String getStageEndTime() {
                return stageEndTime;
            }

            public void setStageEndTime(String stageEndTime) {
                this.stageEndTime = stageEndTime;
            }
        }

        public static class ReplenishContractBean {
            /**
             * rcId : 4968b0b8748a485ba5eed5cd057b530c
             * conId : 80fd626fed074da28554b4f5ecc42910
             * title : 厕所贴砖
             * detail : 大理磁砖
             * price : 1000
             * needDays : 7
             * sign : 0
             * replenishDetail : 舔砖熬
             * accessory :
             * createTime : 2017-12-07 14:17:09.0
             * rcState : 0
             */

            private String rcId;
            private String conId;
            private String title;
            private String detail;
            private String price;
            private int needDays;
            private int sign;
            private String replenishDetail;
            private String accessory;
            private String createTime;
            private String rcState;
            private String startTime;
            private String endTime;

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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
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

            public String getRcState() {
                return rcState;
            }

            public void setRcState(String rcState) {
                this.rcState = rcState;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }
        }
    }
}

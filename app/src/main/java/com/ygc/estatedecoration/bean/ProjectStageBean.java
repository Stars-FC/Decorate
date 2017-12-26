package com.ygc.estatedecoration.bean;

import java.util.List;

public class ProjectStageBean {

    public List<DataBean> getDataBeanList() {
        return mDataBeanList;
    }

    public void setDataBeanList(List<DataBean> dataBeanList) {
        mDataBeanList = dataBeanList;
    }

    private List<DataBean> mDataBeanList;

    public static class DataBean{
        private String projectStageName;

        private String projectContent;

        private String payMoneyJinE;

        private String needDays;

        public String getProjectStageName() {
            return projectStageName;
        }

        public void setProjectStageName(String projectStageName) {
            this.projectStageName = projectStageName;
        }

        public String getProjectContent() {
            return projectContent;
        }

        public void setProjectContent(String projectContent) {
            this.projectContent = projectContent;
        }

        public String getPayMoneyJinE() {
            return payMoneyJinE;
        }

        public void setPayMoneyJinE(String payMoneyJinE) {
            this.payMoneyJinE = payMoneyJinE;
        }

        public String getNeedDays() {
            return needDays;
        }

        public void setNeedDays(String needDays) {
            this.needDays = needDays;
        }
    }
}

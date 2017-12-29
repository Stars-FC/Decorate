package com.ygc.estatedecoration.bean;

import java.io.Serializable;
import java.util.List;

public class ProjectStageBean implements Serializable{

    public List<DataBean> getDataBeanList() {
        return mDataBeanList;
    }

    public void setDataBeanList(List<DataBean> dataBeanList) {
        mDataBeanList = dataBeanList;
    }

    private List<DataBean> mDataBeanList;

    public static class DataBean implements Serializable{
        private String projectStageName;

        private String projectContent;

        private String payMoneyJinE;

        private String startTime;

        private String endTime;

        private String needDays;

        private int position;

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        private String mark = "1";

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

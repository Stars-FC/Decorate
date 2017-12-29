package com.ygc.estatedecoration.bean;

import java.util.List;

/**
 * Created by FC on 2017/12/28.
 * 获取绑定银行卡信息
 */

public class BankCardBean {

    /**
     * responseState : 1
     * msg : 操作成功
     * data : [{"bcId":"bc05632d2dfe4deb82b256b4920bcc42","bankNumber":"6210810730016925704","bankName":"中国建设银行","userName":"王振读","userMobile":"15140088201","createTime":"2017-12-26 15:51:20.0","auId":"1"}]
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
         * bcId : bc05632d2dfe4deb82b256b4920bcc42
         * bankNumber : 6210810730016925704
         * bankName : 中国建设银行
         * userName : 王振读
         * userMobile : 15140088201
         * createTime : 2017-12-26 15:51:20.0
         * auId : 1
         */

        private String bcId;
        private String bankNumber;
        private String bankName;
        private String userName;
        private String userMobile;
        private String createTime;
        private String auId;

        public String getBcId() {
            return bcId;
        }

        public void setBcId(String bcId) {
            this.bcId = bcId;
        }

        public String getBankNumber() {
            return bankNumber;
        }

        public void setBankNumber(String bankNumber) {
            this.bankNumber = bankNumber;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserMobile() {
            return userMobile;
        }

        public void setUserMobile(String userMobile) {
            this.userMobile = userMobile;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getAuId() {
            return auId;
        }

        public void setAuId(String auId) {
            this.auId = auId;
        }
    }
}

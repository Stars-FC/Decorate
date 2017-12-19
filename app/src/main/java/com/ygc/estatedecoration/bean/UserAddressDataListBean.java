package com.ygc.estatedecoration.bean;

import com.ygc.estatedecoration.entity.base.Base;

import java.io.Serializable;
import java.util.List;

public class UserAddressDataListBean extends Base {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * aId : 4119d2d25d75410c801e76a03780e339
         * auId : 2
         * userName : 读少
         * userMobile : 15140088222
         * province : 辽宁沈阳
         * detail : 浑南新区理想新城1期
         * defau : 0
         */

        private String aId;
        private String auId;
        private String userName;
        private String userMobile;
        private String province;
        private String detail;
        private int defau;

        public String getAId() {
            return aId;
        }

        public void setAId(String aId) {
            this.aId = aId;
        }

        public String getAuId() {
            return auId;
        }

        public void setAuId(String auId) {
            this.auId = auId;
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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getDefau() {
            return defau;
        }

        public void setDefau(int defau) {
            this.defau = defau;
        }
    }
}

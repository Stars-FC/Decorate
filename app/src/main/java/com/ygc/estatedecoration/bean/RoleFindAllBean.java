package com.ygc.estatedecoration.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by FC on 2017/12/11.
 * 注册~服务商与细致身份间的联动
 * 备注（身份（0-普通用户/1-设计师/2-施工/3-监理/4-材料商））
 */

public class RoleFindAllBean {

    /**
     * responseState : 1
     * msg : 查询成功
     * data : {"0":[],"1":[],"2":[],"3":[],"4":[{"r_id":1,"r_name":"木工","type":4},{"r_id":2,"r_name":"泥工","type":4},{"r_id":3,"r_name":"水电工","type":4},{"r_id":4,"r_name":"漆工","type":4},{"r_id":5,"r_name":"安装工","type":4},{"r_id":6,"r_name":"杂物","type":4},{"r_id":7,"r_name":"门窗","type":4},{"r_id":8,"r_name":"空调","type":4},{"r_id":9,"r_name":"地暖","type":4},{"r_id":10,"r_name":"灯具","type":4},{"r_id":11,"r_name":"洁具","type":4},{"r_id":12,"r_name":"软装","type":4}]}
     */

    private String responseState;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("0")
        private List<?> user;
        @SerializedName("1")
        private List<?> designer;
        @SerializedName("2")
        private List<?> construction;
        @SerializedName("3")
        private List<?> supervisor;
        @SerializedName("4")
        private List<materialBean> material;


        public List<?> getUser() {
            return user;
        }

        public void setUser(List<?> user) {
            this.user = user;
        }

        public List<?> getDesigner() {
            return designer;
        }

        public void setDesigner(List<?> designer) {
            this.designer = designer;
        }

        public List<?> getConstruction() {
            return construction;
        }

        public void setConstruction(List<?> construction) {
            this.construction = construction;
        }

        public List<?> getSupervisor() {
            return supervisor;
        }

        public void setSupervisor(List<?> supervisor) {
            this.supervisor = supervisor;
        }

        public List<materialBean> getMaterial() {
            return material;
        }

        public void setMaterial(List<materialBean> material) {
            this.material = material;
        }

        public static class materialBean {
            /**
             * r_id : 1
             * r_name : 木工
             * type : 4
             */

            private int r_id;
            private String r_name;
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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}

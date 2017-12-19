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
     * data : {"0":[],"1":[],"2":[{"r_id":13,"r_name":"水电工","r_picture":"/pictures/1.jpg","type":2},{"r_id":14,"r_name":"木工","r_picture":"/pictures/1.jpg","type":2},{"r_id":15,"r_name":"泥工","r_picture":"/pictures/1.jpg","type":2},{"r_id":16,"r_name":"漆工","r_picture":"/pictures/1.jpg","type":2},{"r_id":17,"r_name":"安装工","r_picture":"/pictures/1.jpg","type":2},{"r_id":18,"r_name":"杂工","r_picture":"/pictures/1.jpg","type":2}],"3":[],"4":[{"r_id":1,"r_name":"木工","r_picture":"/pictures/1.jpg","type":4},{"r_id":2,"r_name":"泥工","r_picture":"/pictures/1.jpg","type":4},{"r_id":3,"r_name":"水电工","r_picture":"/pictures/1.jpg","type":4},{"r_id":4,"r_name":"漆工","r_picture":"/pictures/1.jpg","type":4},{"r_id":5,"r_name":"安装工","r_picture":"/pictures/1.jpg","type":4},{"r_id":6,"r_name":"杂物","r_picture":"/pictures/1.jpg","type":4},{"r_id":7,"r_name":"门窗","r_picture":"/pictures/1.jpg","type":4},{"r_id":8,"r_name":"空调","r_picture":"/pictures/1.jpg","type":4},{"r_id":9,"r_name":"地暖","r_picture":"/pictures/1.jpg","type":4},{"r_id":10,"r_name":"灯具","r_picture":"/pictures/1.jpg","type":4},{"r_id":11,"r_name":"洁具","r_picture":"/pictures/1.jpg","type":4},{"r_id":12,"r_name":"软装","r_picture":"/pictures/1.jpg","type":4}]}
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
        private List<?> _$0;
        @SerializedName("1")
        private List<?> _$1;
        @SerializedName("2")
        private List<_$2Bean> _$2;
        @SerializedName("3")
        private List<?> _$3;
        @SerializedName("4")
        private List<_$4Bean> _$4;

        public List<?> get_$0() {
            return _$0;
        }

        public void set_$0(List<?> _$0) {
            this._$0 = _$0;
        }

        public List<?> get_$1() {
            return _$1;
        }

        public void set_$1(List<?> _$1) {
            this._$1 = _$1;
        }

        public List<_$2Bean> get_$2() {
            return _$2;
        }

        public void set_$2(List<_$2Bean> _$2) {
            this._$2 = _$2;
        }

        public List<?> get_$3() {
            return _$3;
        }

        public void set_$3(List<?> _$3) {
            this._$3 = _$3;
        }

        public List<_$4Bean> get_$4() {
            return _$4;
        }

        public void set_$4(List<_$4Bean> _$4) {
            this._$4 = _$4;
        }

        public static class _$2Bean {
            /**
             * r_id : 13
             * r_name : 水电工
             * r_picture : /pictures/1.jpg
             * type : 2
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

        public static class _$4Bean {
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

package com.ygc.estatedecoration.bean;

import java.util.List;

/**
 * Created by FC on 2018/1/3.
 * 用户端，小技巧
 */

public class UserHomeSkillBean {

    /**
     * responseState : 1
     * msg : 查询成功
     * data : [{"skill_id":1,"skill_title":"30平变5室两厅","skill_picture":"/pictures/98c3288bd2114dd4a547cd8faa05a4da.jpg","skill_content":"哈哈哈哈哈哈","create_time":"2017-12-21 16:10:31.0"}]
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
         * skill_id : 1
         * skill_title : 30平变5室两厅
         * skill_picture : /pictures/98c3288bd2114dd4a547cd8faa05a4da.jpg
         * skill_content : 哈哈哈哈哈哈
         * create_time : 2017-12-21 16:10:31.0
         */

        private int skill_id;
        private String skill_title;
        private String skill_picture;
        private String skill_content;
        private String create_time;

        public int getSkill_id() {
            return skill_id;
        }

        public void setSkill_id(int skill_id) {
            this.skill_id = skill_id;
        }

        public String getSkill_title() {
            return skill_title;
        }

        public void setSkill_title(String skill_title) {
            this.skill_title = skill_title;
        }

        public String getSkill_picture() {
            return skill_picture;
        }

        public void setSkill_picture(String skill_picture) {
            this.skill_picture = skill_picture;
        }

        public String getSkill_content() {
            return skill_content;
        }

        public void setSkill_content(String skill_content) {
            this.skill_content = skill_content;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}

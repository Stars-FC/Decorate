package com.ygc.estatedecoration.bean;

import com.ygc.estatedecoration.entity.base.Base;

public class ContractContentBean extends Base{

    /**
     * data : {"supervision_contract_model":"监理合同模板","material_contract_model":"材料商合同模板","create_time":1513843177000,"sys_demand_banner":"pictures/demand/11/20171221140510-fd475a90817e8f48211af1.jpg,pictures/demand/11/20171221140510-ab48f6b34edf82e487afe9.jpg,pictures/demand/11/20171221140510-ce4dd9bfa230de641cca4f.jpg","sys_case_banner":"pictures/demand/11/20171221140510-fd475a90817e8f48211af1.jpg,pictures/demand/11/20171221140510-ab48f6b34edf82e487afe9.jpg,pictures/demand/11/20171221140510-ce4dd9bfa230de641cca4f.jpg","construction_contract_model":"施工合同模板 ","citys":"北京,上海,广州,深圳,沈阳,青岛,葫芦岛","sys_banner":"pictures/demand/11/20171221140510-fd475a90817e8f48211af1.jpg,pictures/demand/11/20171221140510-ab48f6b34edf82e487afe9.jpg,pictures/demand/11/20171221140510-ce4dd9bfa230de641cca4f.jpg","design_contract_model":"设计师合同模板"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * supervision_contract_model : 监理合同模板
         * material_contract_model : 材料商合同模板
         * create_time : 1513843177000
         * sys_demand_banner : pictures/demand/11/20171221140510-fd475a90817e8f48211af1.jpg,pictures/demand/11/20171221140510-ab48f6b34edf82e487afe9.jpg,pictures/demand/11/20171221140510-ce4dd9bfa230de641cca4f.jpg
         * sys_case_banner : pictures/demand/11/20171221140510-fd475a90817e8f48211af1.jpg,pictures/demand/11/20171221140510-ab48f6b34edf82e487afe9.jpg,pictures/demand/11/20171221140510-ce4dd9bfa230de641cca4f.jpg
         * construction_contract_model : 施工合同模板
         * citys : 北京,上海,广州,深圳,沈阳,青岛,葫芦岛
         * sys_banner : pictures/demand/11/20171221140510-fd475a90817e8f48211af1.jpg,pictures/demand/11/20171221140510-ab48f6b34edf82e487afe9.jpg,pictures/demand/11/20171221140510-ce4dd9bfa230de641cca4f.jpg
         * design_contract_model : 设计师合同模板
         */

        private String supervision_contract_model;
        private String material_contract_model;
        private long create_time;
        private String sys_demand_banner;
        private String sys_case_banner;
        private String construction_contract_model;
        private String citys;
        private String sys_banner;
        private String design_contract_model;

        public String getSupervision_contract_model() {
            return supervision_contract_model;
        }

        public void setSupervision_contract_model(String supervision_contract_model) {
            this.supervision_contract_model = supervision_contract_model;
        }

        public String getMaterial_contract_model() {
            return material_contract_model;
        }

        public void setMaterial_contract_model(String material_contract_model) {
            this.material_contract_model = material_contract_model;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public String getSys_demand_banner() {
            return sys_demand_banner;
        }

        public void setSys_demand_banner(String sys_demand_banner) {
            this.sys_demand_banner = sys_demand_banner;
        }

        public String getSys_case_banner() {
            return sys_case_banner;
        }

        public void setSys_case_banner(String sys_case_banner) {
            this.sys_case_banner = sys_case_banner;
        }

        public String getConstruction_contract_model() {
            return construction_contract_model;
        }

        public void setConstruction_contract_model(String construction_contract_model) {
            this.construction_contract_model = construction_contract_model;
        }

        public String getCitys() {
            return citys;
        }

        public void setCitys(String citys) {
            this.citys = citys;
        }

        public String getSys_banner() {
            return sys_banner;
        }

        public void setSys_banner(String sys_banner) {
            this.sys_banner = sys_banner;
        }

        public String getDesign_contract_model() {
            return design_contract_model;
        }

        public void setDesign_contract_model(String design_contract_model) {
            this.design_contract_model = design_contract_model;
        }
    }
}

package com.shenkangyun.medicalplatform.BeanFolder;

import java.util.List;

/**
 * Created by Administrator on 2018/5/5.
 */

public class KeyWordBean {

    /**
     * status : 0
     * data : {"pageCount":10,"totalCount":2,"pageNo":0,"list":[{"keyWordName":"智力障碍","id":"16","name":"泰安市复退军人精神病院","responsibilityName":"吴金凤","prevention":"","scope":"智力的诊断治疗，康复指导，健康咨询，等级评定。","subsidy":"免鉴定费80元。","content":"泰安市复员退伍军人精神病院（泰安市优抚医院），是一家集精神疾病预防、治疗、康复为主兼优抚对象康复休养为一体的优抚医院。<br />","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"5362327","xAxis":"117.148992","yAxis":"36.201349","bdContent":null,"createUser":"admin","createTime":1510648637000,"updateUser":"admin","updateTime":1525682683000,"delFlag":0,"delTime":1522225947000,"remark":null},{"keyWordName":"智力障碍","id":"40288056695c751401695c7bd3010001","name":"星童特教学校","responsibilityName":"迟玉君","prevention":"","scope":"","subsidy":"","content":"","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902004","village":null,"phone":"13734439906","xAxis":"117.188221","yAxis":"36.20595","bdContent":null,"createUser":"admin","createTime":1552034812000,"updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null}],"baiduMessage":["智力障碍（MR）又称智力缺陷，一般指的是由于大脑受到器质性的损害或是由于脑发育不完全从而造成认识活动的持续障碍以及整个心理活动的障碍。 由于遗传变异、感染、中毒、头部受伤、颅脑畸形或内分泌异常等有害因素造成胎儿或婴幼儿的大脑不能正常发育或发育不完全，使智力活动的发育停留在某个比较低的阶段中，称为智力迟滞。由于大脑受到物理、化学或病毒、病菌等因素的损伤使原来正常的智力受到损害，造成缺陷，则称痴呆。"]}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pageCount : 10
         * totalCount : 2
         * pageNo : 0
         * list : [{"keyWordName":"智力障碍","id":"16","name":"泰安市复退军人精神病院","responsibilityName":"吴金凤","prevention":"","scope":"智力的诊断治疗，康复指导，健康咨询，等级评定。","subsidy":"免鉴定费80元。","content":"泰安市复员退伍军人精神病院（泰安市优抚医院），是一家集精神疾病预防、治疗、康复为主兼优抚对象康复休养为一体的优抚医院。<br />","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"5362327","xAxis":"117.148992","yAxis":"36.201349","bdContent":null,"createUser":"admin","createTime":1510648637000,"updateUser":"admin","updateTime":1525682683000,"delFlag":0,"delTime":1522225947000,"remark":null},{"keyWordName":"智力障碍","id":"40288056695c751401695c7bd3010001","name":"星童特教学校","responsibilityName":"迟玉君","prevention":"","scope":"","subsidy":"","content":"","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902004","village":null,"phone":"13734439906","xAxis":"117.188221","yAxis":"36.20595","bdContent":null,"createUser":"admin","createTime":1552034812000,"updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null}]
         * baiduMessage : ["智力障碍（MR）又称智力缺陷，一般指的是由于大脑受到器质性的损害或是由于脑发育不完全从而造成认识活动的持续障碍以及整个心理活动的障碍。 由于遗传变异、感染、中毒、头部受伤、颅脑畸形或内分泌异常等有害因素造成胎儿或婴幼儿的大脑不能正常发育或发育不完全，使智力活动的发育停留在某个比较低的阶段中，称为智力迟滞。由于大脑受到物理、化学或病毒、病菌等因素的损伤使原来正常的智力受到损害，造成缺陷，则称痴呆。"]
         */

        private int pageCount;
        private int totalCount;
        private int pageNo;
        private List<ListBean> list;
        private List<String> baiduMessage;

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<String> getBaiduMessage() {
            return baiduMessage;
        }

        public void setBaiduMessage(List<String> baiduMessage) {
            this.baiduMessage = baiduMessage;
        }

        public static class ListBean {
            /**
             * keyWordName : 智力障碍
             * id : 16
             * name : 泰安市复退军人精神病院
             * responsibilityName : 吴金凤
             * prevention :
             * scope : 智力的诊断治疗，康复指导，健康咨询，等级评定。
             * subsidy : 免鉴定费80元。
             * content : 泰安市复员退伍军人精神病院（泰安市优抚医院），是一家集精神疾病预防、治疗、康复为主兼优抚对象康复休养为一体的优抚医院。<br />
             * provinceID : 370000
             * cityID : 370900
             * regionID : 370902
             * street : 370902001
             * village : null
             * phone : 5362327
             * xAxis : 117.148992
             * yAxis : 36.201349
             * bdContent : null
             * createUser : admin
             * createTime : 1510648637000
             * updateUser : admin
             * updateTime : 1525682683000
             * delFlag : 0
             * delTime : 1522225947000
             * remark : null
             */

            private String keyWordName;
            private String id;
            private String name;
            private String responsibilityName;
            private String prevention;
            private String scope;
            private String subsidy;
            private String content;
            private String provinceID;
            private String cityID;
            private String regionID;
            private String street;
            private Object village;
            private String phone;
            private String xAxis;
            private String yAxis;
            private Object bdContent;
            private String createUser;
            private long createTime;
            private String updateUser;
            private long updateTime;
            private int delFlag;
            private long delTime;
            private Object remark;

            public String getKeyWordName() {
                return keyWordName;
            }

            public void setKeyWordName(String keyWordName) {
                this.keyWordName = keyWordName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getResponsibilityName() {
                return responsibilityName;
            }

            public void setResponsibilityName(String responsibilityName) {
                this.responsibilityName = responsibilityName;
            }

            public String getPrevention() {
                return prevention;
            }

            public void setPrevention(String prevention) {
                this.prevention = prevention;
            }

            public String getScope() {
                return scope;
            }

            public void setScope(String scope) {
                this.scope = scope;
            }

            public String getSubsidy() {
                return subsidy;
            }

            public void setSubsidy(String subsidy) {
                this.subsidy = subsidy;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getProvinceID() {
                return provinceID;
            }

            public void setProvinceID(String provinceID) {
                this.provinceID = provinceID;
            }

            public String getCityID() {
                return cityID;
            }

            public void setCityID(String cityID) {
                this.cityID = cityID;
            }

            public String getRegionID() {
                return regionID;
            }

            public void setRegionID(String regionID) {
                this.regionID = regionID;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public Object getVillage() {
                return village;
            }

            public void setVillage(Object village) {
                this.village = village;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getXAxis() {
                return xAxis;
            }

            public void setXAxis(String xAxis) {
                this.xAxis = xAxis;
            }

            public String getYAxis() {
                return yAxis;
            }

            public void setYAxis(String yAxis) {
                this.yAxis = yAxis;
            }

            public Object getBdContent() {
                return bdContent;
            }

            public void setBdContent(Object bdContent) {
                this.bdContent = bdContent;
            }

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(String updateUser) {
                this.updateUser = updateUser;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public long getDelTime() {
                return delTime;
            }

            public void setDelTime(long delTime) {
                this.delTime = delTime;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }
        }
    }
}

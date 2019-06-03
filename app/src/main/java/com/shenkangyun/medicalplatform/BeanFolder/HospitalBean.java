package com.shenkangyun.medicalplatform.BeanFolder;

import java.util.List;

public class HospitalBean {

    /**
     * status : 0
     * data : {"pageCount":100,"totalCount":4,"pageNo":0,"list":[{"id":"2","name":"泰山区小天使康复中心","responsibilityName":"李红","prevention":"","scope":"脑瘫、智力障碍、孤独症","subsidy":"脑瘫儿童补贴13200元/人/年，智力、孤独症儿童补贴12000元/人/年","content":"小天使康复中心与中国康复中心、中国康复技术委员会、残疾人康复协会等国内一流康复科研基地建立友好关系。<br />","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"13053853235","xAxis":"117.134887","yAxis":"36.168076","bdContent":null,"createUser":"admin","createTime":1510648625000,"updateUser":"admin","updateTime":1523519045000,"delFlag":0,"delTime":1522225975000,"remark":null},{"id":"1","name":"泰山区人民医院儿康中心","responsibilityName":"贾宁","prevention":"","scope":"脑瘫、智力障碍、孤独症","subsidy":"脑瘫儿童补贴13200元/人/年，智力、孤独症儿童补贴12000元/人/年","content":"泰山区人民医院儿康中心儿童康复中心成立2006年2009年儿康中心成为省残疾儿童智障康复技术指导中心定点机构。<br />","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902002","village":null,"phone":"13953816355","xAxis":"117.139156","yAxis":"36.197457","bdContent":null,"createUser":"admin","createTime":1510130204000,"updateUser":"admin","updateTime":1523516672000,"delFlag":0,"delTime":1522225893000,"remark":null},{"id":"3","name":"泰山区智康能力儿童活动中心","responsibilityName":"周红娜","prevention":"","scope":"脑瘫、智力障碍、孤独症","subsidy":"脑瘫儿童补贴13200元/人/年，智力、孤独症儿童补贴12000元/人/年","content":"泰安市泰山区智康能力儿童活动中心创办于二00八年一月，机构性质民办非营利慈善机构。几年来智康人秉承\u201c立智高远，康达人生\u201d的办学宗旨。<br />","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902004","village":null,"phone":"15966018558","xAxis":"117.123562","yAxis":"36.211454","bdContent":null,"createUser":"admin","createTime":1510648628000,"updateUser":"admin","updateTime":1523518990000,"delFlag":0,"delTime":1522225980000,"remark":null},{"id":"4","name":"泰山区东岳特教中心","responsibilityName":"宋洪举","prevention":"","scope":"孤独症","subsidy":"脑瘫儿童补贴13200元/人/年，智力、孤独症儿童补贴12000元/人/年","content":"泰安市泰山区东岳特教中心成立于2010年6月，由泰安市泰山区残联主管，2010年12月6日在泰山区民政局正式注册的民办非企单位。<br />","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902004","village":null,"phone":"13605485252","xAxis":"117.178124","yAxis":"36.216101","bdContent":null,"createUser":"admin","createTime":1510648633000,"updateUser":"admin","updateTime":1523519097000,"delFlag":0,"delTime":1522225984000,"remark":null}]}
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
         * pageCount : 100
         * totalCount : 4
         * pageNo : 0
         * list : [{"id":"2","name":"泰山区小天使康复中心","responsibilityName":"李红","prevention":"","scope":"脑瘫、智力障碍、孤独症","subsidy":"脑瘫儿童补贴13200元/人/年，智力、孤独症儿童补贴12000元/人/年","content":"小天使康复中心与中国康复中心、中国康复技术委员会、残疾人康复协会等国内一流康复科研基地建立友好关系。<br />","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"13053853235","xAxis":"117.134887","yAxis":"36.168076","bdContent":null,"createUser":"admin","createTime":1510648625000,"updateUser":"admin","updateTime":1523519045000,"delFlag":0,"delTime":1522225975000,"remark":null},{"id":"1","name":"泰山区人民医院儿康中心","responsibilityName":"贾宁","prevention":"","scope":"脑瘫、智力障碍、孤独症","subsidy":"脑瘫儿童补贴13200元/人/年，智力、孤独症儿童补贴12000元/人/年","content":"泰山区人民医院儿康中心儿童康复中心成立2006年2009年儿康中心成为省残疾儿童智障康复技术指导中心定点机构。<br />","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902002","village":null,"phone":"13953816355","xAxis":"117.139156","yAxis":"36.197457","bdContent":null,"createUser":"admin","createTime":1510130204000,"updateUser":"admin","updateTime":1523516672000,"delFlag":0,"delTime":1522225893000,"remark":null},{"id":"3","name":"泰山区智康能力儿童活动中心","responsibilityName":"周红娜","prevention":"","scope":"脑瘫、智力障碍、孤独症","subsidy":"脑瘫儿童补贴13200元/人/年，智力、孤独症儿童补贴12000元/人/年","content":"泰安市泰山区智康能力儿童活动中心创办于二00八年一月，机构性质民办非营利慈善机构。几年来智康人秉承\u201c立智高远，康达人生\u201d的办学宗旨。<br />","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902004","village":null,"phone":"15966018558","xAxis":"117.123562","yAxis":"36.211454","bdContent":null,"createUser":"admin","createTime":1510648628000,"updateUser":"admin","updateTime":1523518990000,"delFlag":0,"delTime":1522225980000,"remark":null},{"id":"4","name":"泰山区东岳特教中心","responsibilityName":"宋洪举","prevention":"","scope":"孤独症","subsidy":"脑瘫儿童补贴13200元/人/年，智力、孤独症儿童补贴12000元/人/年","content":"泰安市泰山区东岳特教中心成立于2010年6月，由泰安市泰山区残联主管，2010年12月6日在泰山区民政局正式注册的民办非企单位。<br />","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902004","village":null,"phone":"13605485252","xAxis":"117.178124","yAxis":"36.216101","bdContent":null,"createUser":"admin","createTime":1510648633000,"updateUser":"admin","updateTime":1523519097000,"delFlag":0,"delTime":1522225984000,"remark":null}]
         */

        private int pageCount;
        private int totalCount;
        private int pageNo;
        private List<ListBean> list;

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

        public static class ListBean {
            /**
             * id : 2
             * name : 泰山区小天使康复中心
             * responsibilityName : 李红
             * prevention :
             * scope : 脑瘫、智力障碍、孤独症
             * subsidy : 脑瘫儿童补贴13200元/人/年，智力、孤独症儿童补贴12000元/人/年
             * content : 小天使康复中心与中国康复中心、中国康复技术委员会、残疾人康复协会等国内一流康复科研基地建立友好关系。<br />
             * provinceID : 370000
             * cityID : 370900
             * regionID : 370902
             * street : 370902001
             * village : null
             * phone : 13053853235
             * xAxis : 117.134887
             * yAxis : 36.168076
             * bdContent : null
             * createUser : admin
             * createTime : 1510648625000
             * updateUser : admin
             * updateTime : 1523519045000
             * delFlag : 0
             * delTime : 1522225975000
             * remark : null
             */

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
            private String village;
            private String phone;
            private String xAxis;
            private String yAxis;
            private String bdContent;
            private String createUser;
            private long createTime;
            private String updateUser;
            private long updateTime;
            private int delFlag;
            private long delTime;
            private String remark;

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

            public String getVillage() {
                return village;
            }

            public void setVillage(String village) {
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

            public String getBdContent() {
                return bdContent;
            }

            public void setBdContent(String bdContent) {
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

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}

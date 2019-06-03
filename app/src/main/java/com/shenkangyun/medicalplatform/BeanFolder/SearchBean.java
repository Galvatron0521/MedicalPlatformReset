package com.shenkangyun.medicalplatform.BeanFolder;

import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class SearchBean {

    /**
     * status : 0
     * data : {"pageCount":8,"totalCount":49,"pageNo":0,"list":[{"id":"2","name":"泰山区小天使康复中心","responsibilityName":"李红","prevention":null,"content":"<strong><span style=\"font-family:KaiTi_GB2312;font-size:32px;\">负责医疗项目:脑瘫、智力障碍、孤独症<\/span><\/strong>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"13053853235","xAxis":"117.134887","yAxis":"36.168076","bdContent":null,"createUser":"admin","createTime":1510648625000,"updateUser":"admin","updateTime":1522389382000,"delFlag":0,"delTime":1522225975000,"remark":null},{"id":"5","name":"泰安市复退军人精神病院","responsibilityName":"赵燕/吴金凤","prevention":null,"content":"<p>\n\t<strong><span style=\"font-family:KaiTi_GB2312;font-size:32px;\">精神疾病的诊断治疗，心理咨询，康复。<\/span><\/strong>\n<\/p>\n<p>\n\t<strong><span style=\"font-family:KaiTi_GB2312;font-size:32px;\">智力的诊断治疗，康复指导，健康咨询，等级评定。<br />\n<\/span><\/strong>\n<\/p>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"5361329/5362327","xAxis":"117.148992","yAxis":"36.201349","bdContent":null,"createUser":"admin","createTime":1510648637000,"updateUser":"admin","updateTime":1522393197000,"delFlag":0,"delTime":1522225947000,"remark":null},{"id":"6","name":"泰安市爱尔眼科医院","responsibilityName":"朱燕","prevention":null,"content":"<strong><span style=\"font-family:KaiTi_GB2312;font-size:32px;\">负责医疗项目:白内障筛查、诊断及复明手术<\/span><\/strong>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"8538666","xAxis":"117.154624","yAxis":"36.200472","bdContent":null,"createUser":"admin","createTime":1510648640000,"updateUser":"admin","updateTime":1522393046000,"delFlag":0,"delTime":1522225941000,"remark":null},{"id":"7","name":"泰安市姜玉坤眼镜有限公司","responsibilityName":"付东升","prevention":null,"content":"<strong><span style=\"font-family:KaiTi_GB2312;font-size:32px;\">负责医疗项目:低视力筛查诊断<\/span><\/strong>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"2187300","xAxis":"117.110542","yAxis":"36.201893","bdContent":null,"createUser":"admin","createTime":1510648643000,"updateUser":"admin","updateTime":1522393006000,"delFlag":0,"delTime":1522225928000,"remark":null},{"id":"4028801e6266209d0162662c9e890015","name":"岱庙街道办事处","responsibilityName":"李国祥","prevention":null,"content":"岱庙街道办事处","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"18854822444","xAxis":"117.156474","yAxis":"36.199055","bdContent":null,"createUser":"admin","createTime":1522132622000,"updateUser":"admin","updateTime":1522379610000,"delFlag":0,"delTime":null,"remark":null},{"id":"4028801e6266209d0162662e029e0017","name":"东关村","responsibilityName":"胥树红","prevention":null,"content":"东关村","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"15953801818","xAxis":"117.155837","yAxis":"36.195669","bdContent":null,"createUser":"admin","createTime":1522132714000,"updateUser":"admin","updateTime":1522379588000,"delFlag":0,"delTime":null,"remark":null},{"id":"4028801e6274848c016274b1bf920003","name":"南关社区","responsibilityName":"张静","prevention":null,"content":"南关社区","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"15169888611","xAxis":"117.139653","yAxis":"36.190149","bdContent":null,"createUser":"admin","createTime":1522376228000,"updateUser":"admin","updateTime":1522379659000,"delFlag":0,"delTime":null,"remark":null},{"id":"4028801e6274848c016274b31fc20005","name":"三友社区","responsibilityName":"戴明辉","prevention":null,"content":"三友社区","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"6131106","xAxis":"117.158061","yAxis":"36.193894","bdContent":null,"createUser":"admin","createTime":1522376318000,"updateUser":"admin","updateTime":1522379306000,"delFlag":0,"delTime":null,"remark":null}]}
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
         * pageCount : 8
         * totalCount : 49
         * pageNo : 0
         * list : [{"id":"2","name":"泰山区小天使康复中心","responsibilityName":"李红","prevention":null,"content":"<strong><span style=\"font-family:KaiTi_GB2312;font-size:32px;\">负责医疗项目:脑瘫、智力障碍、孤独症<\/span><\/strong>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"13053853235","xAxis":"117.134887","yAxis":"36.168076","bdContent":null,"createUser":"admin","createTime":1510648625000,"updateUser":"admin","updateTime":1522389382000,"delFlag":0,"delTime":1522225975000,"remark":null},{"id":"5","name":"泰安市复退军人精神病院","responsibilityName":"赵燕/吴金凤","prevention":null,"content":"<p>\n\t<strong><span style=\"font-family:KaiTi_GB2312;font-size:32px;\">精神疾病的诊断治疗，心理咨询，康复。<\/span><\/strong>\n<\/p>\n<p>\n\t<strong><span style=\"font-family:KaiTi_GB2312;font-size:32px;\">智力的诊断治疗，康复指导，健康咨询，等级评定。<br />\n<\/span><\/strong>\n<\/p>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"5361329/5362327","xAxis":"117.148992","yAxis":"36.201349","bdContent":null,"createUser":"admin","createTime":1510648637000,"updateUser":"admin","updateTime":1522393197000,"delFlag":0,"delTime":1522225947000,"remark":null},{"id":"6","name":"泰安市爱尔眼科医院","responsibilityName":"朱燕","prevention":null,"content":"<strong><span style=\"font-family:KaiTi_GB2312;font-size:32px;\">负责医疗项目:白内障筛查、诊断及复明手术<\/span><\/strong>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"8538666","xAxis":"117.154624","yAxis":"36.200472","bdContent":null,"createUser":"admin","createTime":1510648640000,"updateUser":"admin","updateTime":1522393046000,"delFlag":0,"delTime":1522225941000,"remark":null},{"id":"7","name":"泰安市姜玉坤眼镜有限公司","responsibilityName":"付东升","prevention":null,"content":"<strong><span style=\"font-family:KaiTi_GB2312;font-size:32px;\">负责医疗项目:低视力筛查诊断<\/span><\/strong>","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"2187300","xAxis":"117.110542","yAxis":"36.201893","bdContent":null,"createUser":"admin","createTime":1510648643000,"updateUser":"admin","updateTime":1522393006000,"delFlag":0,"delTime":1522225928000,"remark":null},{"id":"4028801e6266209d0162662c9e890015","name":"岱庙街道办事处","responsibilityName":"李国祥","prevention":null,"content":"岱庙街道办事处","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"18854822444","xAxis":"117.156474","yAxis":"36.199055","bdContent":null,"createUser":"admin","createTime":1522132622000,"updateUser":"admin","updateTime":1522379610000,"delFlag":0,"delTime":null,"remark":null},{"id":"4028801e6266209d0162662e029e0017","name":"东关村","responsibilityName":"胥树红","prevention":null,"content":"东关村","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"15953801818","xAxis":"117.155837","yAxis":"36.195669","bdContent":null,"createUser":"admin","createTime":1522132714000,"updateUser":"admin","updateTime":1522379588000,"delFlag":0,"delTime":null,"remark":null},{"id":"4028801e6274848c016274b1bf920003","name":"南关社区","responsibilityName":"张静","prevention":null,"content":"南关社区","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"15169888611","xAxis":"117.139653","yAxis":"36.190149","bdContent":null,"createUser":"admin","createTime":1522376228000,"updateUser":"admin","updateTime":1522379659000,"delFlag":0,"delTime":null,"remark":null},{"id":"4028801e6274848c016274b31fc20005","name":"三友社区","responsibilityName":"戴明辉","prevention":null,"content":"三友社区","provinceID":"370000","cityID":"370900","regionID":"370902","street":"370902001","village":null,"phone":"6131106","xAxis":"117.158061","yAxis":"36.193894","bdContent":null,"createUser":"admin","createTime":1522376318000,"updateUser":"admin","updateTime":1522379306000,"delFlag":0,"delTime":null,"remark":null}]
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
             * prevention : null
             * content : <strong><span style="font-family:KaiTi_GB2312;font-size:32px;">负责医疗项目:脑瘫、智力障碍、孤独症</span></strong>
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
             * updateTime : 1522389382000
             * delFlag : 0
             * delTime : 1522225975000
             * remark : null
             */

            private String id;
            private String name;
            private String responsibilityName;
            private String prevention;
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

            public String getxAxis() {
                return xAxis;
            }

            public void setxAxis(String xAxis) {
                this.xAxis = xAxis;
            }

            public String getyAxis() {
                return yAxis;
            }

            public void setyAxis(String yAxis) {
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

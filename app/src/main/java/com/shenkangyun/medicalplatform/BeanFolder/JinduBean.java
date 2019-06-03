package com.shenkangyun.medicalplatform.BeanFolder;

import java.util.List;

public class JinduBean {

    /**
     * status : 0
     * data : {"patientList":[{"id":"40288052697106a701697106a7e80000","applyType":2,"recoveryType":1,"organizeID":"1","patientID":6,"patientName":"石屹哲","picUrl":"","province":null,"city":"370900","region":"370902","town":"370902001","address":"1332323","canjiTypeID":"9","canjiTypeContent":"0-11岁儿童","toolID":"","toolName":"","townstatus":0,"regionstatus":0,"createUser":null,"createTime":1552379455000,"updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null}]}
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
        private List<PatientListBean> patientList;

        public List<PatientListBean> getPatientList() {
            return patientList;
        }

        public void setPatientList(List<PatientListBean> patientList) {
            this.patientList = patientList;
        }

        public static class PatientListBean {
            /**
             * id : 40288052697106a701697106a7e80000
             * applyType : 2
             * recoveryType : 1
             * organizeID : 1
             * patientID : 6
             * patientName : 石屹哲
             * picUrl :
             * province : null
             * city : 370900
             * region : 370902
             * town : 370902001
             * address : 1332323
             * canjiTypeID : 9
             * canjiTypeContent : 0-11岁儿童
             * toolID :
             * toolName :
             * townstatus : 0
             * regionstatus : 0
             * createUser : null
             * createTime : 1552379455000
             * updateUser : null
             * updateTime : null
             * delFlag : 0
             * delTime : null
             * remark : null
             */

            private String id;
            private int applyType;
            private int recoveryType;
            private String organizeID;
            private int patientID;
            private String patientName;
            private String picUrl;
            private Object province;
            private String city;
            private String region;
            private String town;
            private String address;
            private String canjiTypeID;
            private String canjiTypeContent;
            private String toolID;
            private String toolName;
            private int townstatus;
            private int regionstatus;
            private Object createUser;
            private long createTime;
            private Object updateUser;
            private Object updateTime;
            private int delFlag;
            private Object delTime;
            private Object remark;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getApplyType() {
                return applyType;
            }

            public void setApplyType(int applyType) {
                this.applyType = applyType;
            }

            public int getRecoveryType() {
                return recoveryType;
            }

            public void setRecoveryType(int recoveryType) {
                this.recoveryType = recoveryType;
            }

            public String getOrganizeID() {
                return organizeID;
            }

            public void setOrganizeID(String organizeID) {
                this.organizeID = organizeID;
            }

            public int getPatientID() {
                return patientID;
            }

            public void setPatientID(int patientID) {
                this.patientID = patientID;
            }

            public String getPatientName() {
                return patientName;
            }

            public void setPatientName(String patientName) {
                this.patientName = patientName;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public Object getProvince() {
                return province;
            }

            public void setProvince(Object province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getTown() {
                return town;
            }

            public void setTown(String town) {
                this.town = town;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getCanjiTypeID() {
                return canjiTypeID;
            }

            public void setCanjiTypeID(String canjiTypeID) {
                this.canjiTypeID = canjiTypeID;
            }

            public String getCanjiTypeContent() {
                return canjiTypeContent;
            }

            public void setCanjiTypeContent(String canjiTypeContent) {
                this.canjiTypeContent = canjiTypeContent;
            }

            public String getToolID() {
                return toolID;
            }

            public void setToolID(String toolID) {
                this.toolID = toolID;
            }

            public String getToolName() {
                return toolName;
            }

            public void setToolName(String toolName) {
                this.toolName = toolName;
            }

            public int getTownstatus() {
                return townstatus;
            }

            public void setTownstatus(int townstatus) {
                this.townstatus = townstatus;
            }

            public int getRegionstatus() {
                return regionstatus;
            }

            public void setRegionstatus(int regionstatus) {
                this.regionstatus = regionstatus;
            }

            public Object getCreateUser() {
                return createUser;
            }

            public void setCreateUser(Object createUser) {
                this.createUser = createUser;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(Object updateUser) {
                this.updateUser = updateUser;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public Object getDelTime() {
                return delTime;
            }

            public void setDelTime(Object delTime) {
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

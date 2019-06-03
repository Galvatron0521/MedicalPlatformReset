package com.shenkangyun.medicalplatform.BeanFolder;

import java.util.List;

public class ToApplyBean {

    /**
     * status : 0
     * data : {"areaList":[{"id":"370902","region":"泰山区"},{"id":"370911","region":"岱岳区"},{"id":"370921","region":"宁阳县"},{"id":"370923","region":"东平县"},{"id":"370982","region":"新泰市"},{"id":"370983","region":"肥城市"},{"id":"370990","region":"泰安市高新技术开发区"}],"recoveryList":[{"typeDetailCode":1,"typeDetailName":"儿童康复_智力"},{"typeDetailCode":2,"typeDetailName":"儿童康复_脑瘫"},{"typeDetailCode":3,"typeDetailName":"儿童康复_孤独症"},{"typeDetailCode":4,"typeDetailName":"儿童康复_听力"},{"typeDetailCode":5,"typeDetailName":"儿童康复_视力"},{"typeDetailCode":6,"typeDetailName":"听力_儿童_人工耳蜗"},{"typeDetailCode":7,"typeDetailName":"听力_儿童_语言训练"},{"typeDetailCode":8,"typeDetailName":"精神_服药申请"},{"typeDetailCode":9,"typeDetailName":"视力_低视力康复"}],"patientList":[{"id":6,"name":"李明刚","idType":"2","idCard":"37092219601205005523","canjiTypeContent":"听力"}]}
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
        private List<AreaListBean> areaList;
        private List<RecoveryListBean> recoveryList;
        private List<PatientListBean> patientList;

        public List<AreaListBean> getAreaList() {
            return areaList;
        }

        public void setAreaList(List<AreaListBean> areaList) {
            this.areaList = areaList;
        }

        public List<RecoveryListBean> getRecoveryList() {
            return recoveryList;
        }

        public void setRecoveryList(List<RecoveryListBean> recoveryList) {
            this.recoveryList = recoveryList;
        }

        public List<PatientListBean> getPatientList() {
            return patientList;
        }

        public void setPatientList(List<PatientListBean> patientList) {
            this.patientList = patientList;
        }

        public static class AreaListBean {
            /**
             * id : 370902
             * region : 泰山区
             */

            private String id;
            private String region;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }
        }

        public static class RecoveryListBean {
            /**
             * typeDetailCode : 1
             * typeDetailName : 儿童康复_智力
             */

            private int typeDetailCode;
            private String typeDetailName;

            public int getTypeDetailCode() {
                return typeDetailCode;
            }

            public void setTypeDetailCode(int typeDetailCode) {
                this.typeDetailCode = typeDetailCode;
            }

            public String getTypeDetailName() {
                return typeDetailName;
            }

            public void setTypeDetailName(String typeDetailName) {
                this.typeDetailName = typeDetailName;
            }
        }

        public static class PatientListBean {
            /**
             * id : 6
             * name : 李明刚
             * idType : 2
             * idCard : 37092219601205005523
             * canjiTypeContent : 听力
             */

            private int id;
            private String name;
            private String idType;
            private String idCard;
            private String canjiTypeContent;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIdType() {
                return idType;
            }

            public void setIdType(String idType) {
                this.idType = idType;
            }

            public String getIdCard() {
                return idCard;
            }

            public void setIdCard(String idCard) {
                this.idCard = idCard;
            }

            public String getCanjiTypeContent() {
                return canjiTypeContent;
            }

            public void setCanjiTypeContent(String canjiTypeContent) {
                this.canjiTypeContent = canjiTypeContent;
            }
        }
    }
}

package com.shenkangyun.medicalplatform.BeanFolder;

import java.util.List;

public class TownBean {

    /**
     * status : 0
     * data : {"typeList":[{"id":"370902001","name":"岱庙街道办事处"},{"id":"370902002","name":"财源街道办事处"},{"id":"370902003","name":"泰前街道办事处"},{"id":"370902004","name":"上高街道办事处"},{"id":"370902005","name":"徐家楼街道办事处"},{"id":"370902100","name":"省庄镇"},{"id":"370902101","name":"邱家店镇"},{"id":"370902202","name":"大津口乡"}]}
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
        private List<TypeListBean> typeList;

        public List<TypeListBean> getTypeList() {
            return typeList;
        }

        public void setTypeList(List<TypeListBean> typeList) {
            this.typeList = typeList;
        }

        public static class TypeListBean {
            /**
             * id : 370902001
             * name : 岱庙街道办事处
             */

            private String id;
            private String name;

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
        }
    }
}

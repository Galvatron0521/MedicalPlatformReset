package com.shenkangyun.medicalplatform.BeanFolder;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */

public class StreetBean {

    /**
     * status : 0
     * data : {"list":[{"id":"370902001","name":"岱庙街道办事处","pid":"370902"},{"id":"370902002","name":"财源街道办事处","pid":"370902"},{"id":"370902003","name":"泰前街道办事处","pid":"370902"},{"id":"370902004","name":"上高街道办事处","pid":"370902"},{"id":"370902005","name":"徐家楼街道办事处","pid":"370902"},{"id":"370902100","name":"省庄镇","pid":"370902"},{"id":"370902101","name":"邱家店镇","pid":"370902"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 370902001
             * name : 岱庙街道办事处
             * pid : 370902
             */

            private String id;
            private String name;
            private String pid;

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

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }
        }
    }
}

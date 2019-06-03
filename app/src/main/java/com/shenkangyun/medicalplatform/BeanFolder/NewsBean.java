package com.shenkangyun.medicalplatform.BeanFolder;

import java.util.List;

public class NewsBean {

    /**
     * status : 0
     * data : {"newslist":[{"id":9,"newsType":1,"title":"加强道路交通安全管理","picUrl":null,"createTime":1519611932000,"delFlag":0},{"id":8,"newsType":1,"title":"加强安全生产监管","picUrl":null,"createTime":1519438732000,"delFlag":0},{"id":7,"newsType":1,"title":"加强精神疾病防治","picUrl":null,"createTime":1519176646000,"delFlag":0},{"id":6,"newsType":1,"title":"加强慢性疾病防治","picUrl":null,"createTime":1519176500000,"delFlag":0},{"id":5,"newsType":1,"title":"有效控制地方性疾病","picUrl":null,"createTime":1519176448000,"delFlag":0},{"id":4,"newsType":1,"title":"有效控制传染性疾病","picUrl":null,"createTime":1517799264000,"delFlag":0},{"id":3,"newsType":1,"title":"加强新生儿及儿童筛查和干预","picUrl":null,"createTime":1517799126000,"delFlag":0},{"id":2,"newsType":1,"title":"做好产前筛查、诊断","picUrl":null,"createTime":1517798942000,"delFlag":0}]}
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
        private List<NewslistBean> newslist;

        public List<NewslistBean> getNewslist() {
            return newslist;
        }

        public void setNewslist(List<NewslistBean> newslist) {
            this.newslist = newslist;
        }

        public static class NewslistBean {
            /**
             * id : 9
             * newsType : 1
             * title : 加强道路交通安全管理
             * picUrl : null
             * createTime : 1519611932000
             * delFlag : 0
             */

            private int id;
            private int newsType;
            private String title;
            private Object picUrl;
            private long createTime;
            private int delFlag;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getNewsType() {
                return newsType;
            }

            public void setNewsType(int newsType) {
                this.newsType = newsType;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(Object picUrl) {
                this.picUrl = picUrl;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }
        }
    }
}

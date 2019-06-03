package com.shenkangyun.medicalplatform.BeanFolder;

public class NewInfoBean {

    /**
     * status : 0
     * data : {"news":{"id":1,"newsType":1,"title":"加强婚前、孕前健康检查","picUrl":null,"content":"<p>\n\t1.婚前医学检查；\n<\/p>\n<p>\n\t2.孕前优生健康检查；\n<\/p>\n<p>\n\t3.推进补服叶酸预防神经管缺陷。\n<\/p>\n<p>\n\t<br />\n<\/p>","createTime":"2018-01-06 ","delFlag":0}}
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
         * news : {"id":1,"newsType":1,"title":"加强婚前、孕前健康检查","picUrl":null,"content":"<p>\n\t1.婚前医学检查；\n<\/p>\n<p>\n\t2.孕前优生健康检查；\n<\/p>\n<p>\n\t3.推进补服叶酸预防神经管缺陷。\n<\/p>\n<p>\n\t<br />\n<\/p>","createTime":"2018-01-06 ","delFlag":0}
         */

        private NewsBean news;

        public NewsBean getNews() {
            return news;
        }

        public void setNews(NewsBean news) {
            this.news = news;
        }

        public static class NewsBean {
            /**
             * id : 1
             * newsType : 1
             * title : 加强婚前、孕前健康检查
             * picUrl : null
             * content : <p>
             1.婚前医学检查；
             </p>
             <p>
             2.孕前优生健康检查；
             </p>
             <p>
             3.推进补服叶酸预防神经管缺陷。
             </p>
             <p>
             <br />
             </p>
             * createTime : 2018-01-06
             * delFlag : 0
             */

            private int id;
            private int newsType;
            private String title;
            private Object picUrl;
            private String content;
            private String createTime;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
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

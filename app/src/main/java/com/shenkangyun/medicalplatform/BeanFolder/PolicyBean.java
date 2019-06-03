package com.shenkangyun.medicalplatform.BeanFolder;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7.
 */

public class PolicyBean {

    /**
     * status : 0
     * data : {"pageCount":8,"totalCount":7,"pageNo":0,"list":[{"id":"4028801e62a333730162a333734c0000","title":"低保重度残疾人生活补贴政策","disabilityType":"4028801e62a333730162a333734c0001","content":"低保重度残疾人生活补贴政策：82元/月。","createUser":"admin","createTime":1523156480000,"updateUser":"","updateTime":"","delFlag":0,"delTime":"","remark":""},{"id":"4028801e62a333730162a333f6790003","title":"残疾人驾驶骑车学费补贴政策","disabilityType":"4028801e62a333730162a333f6790004","content":"残疾人驾驶骑车学费补贴政策：申请人参加架势汽车所需学费，由区残联一次性给与每人1500元的补贴。","createUser":"admin","createTime":1523156514000,"updateUser":"","updateTime":"","delFlag":0,"delTime":"","remark":""},{"id":"4028801e62a333730162a33448d10006","title":"残疾人学费补贴政策","disabilityType":"4028801e62a333730162a33448d10007","content":"残疾人学费补贴政策：残疾人当年考取大中专院校，学费补贴4000-6000元。","createUser":"admin","createTime":1523156535000,"updateUser":"","updateTime":"","delFlag":0,"delTime":"","remark":""},{"id":"4028801e62a333730162a3351d550009","title":"重度残疾人缴纳居民医疗保险费补助政策","disabilityType":"4028801e62a333730162a3351d55000a","content":"重度残疾人缴纳居民医疗保险费补助政策：<br />\n&nbsp;&nbsp; &nbsp;1、当年享受低保的重度残疾人，选择一档（100元）的给予全额补助，个人不缴费。<br />\n&nbsp;&nbsp; &nbsp;选择二档（220元）的，按70%予以补助，个人缴纳66元。<br />\n&nbsp;&nbsp; &nbsp;2、其他重度残疾人按照缴费标准的70%予以补助，个人缴纳30元或66元。","createUser":"admin","createTime":1523156589000,"updateUser":"","updateTime":"","delFlag":0,"delTime":"","remark":""},{"id":"4028801e62a411d20162a411d2f70000","title":"低保重度不能自理残疾人护理补贴政策","disabilityType":"4028801e62a411d20162a411d2f70001","content":"低保重度不能自理残疾人护理补贴政策：80元/月","createUser":"admin","createTime":1523171054000,"updateUser":"","updateTime":"","delFlag":0,"delTime":"","remark":""},{"id":"4028801e62a411d20162a4135ca80003","title":"残疾人优惠政策","disabilityType":"4028801e62a411d20162a4135ca80004","content":"残疾人优惠政策：残疾人免费乘坐公交和进入国家级景点。","createUser":"admin","createTime":1523171155000,"updateUser":"","updateTime":"","delFlag":0,"delTime":"","remark":""},{"id":"4028805262a331020162a33102ba0000","title":"申请办理残疾人证程序 ","disabilityType":"4028805262a331020162a33102ba0001","content":"<p>\n\t1、 申请人本人(户籍在泰山区)到街道残联领取申请表、评定表(各一式三份)。\n<\/p>\n<p>\n\t2、申请人带身份证与评定表到医院做鉴定(肢体、视力、听力残疾到中医二院；精神、智力残疾到复退军人精神病医院)。\n<\/p>\n<p>\n\t3、申请人带身份证到泰山图片社(岱庙南门大槐树旁)进行照片采集。\n<\/p>\n<p>\n\t4、申请人填写申请表。(各村(居)负责人在申请表的下方签署\"情况属实\"，加盖公章，并签字。) &nbsp;&nbsp;&nbsp;&nbsp;\n<\/p>\n<p>\n\t5、申请人带申请表(3份)、评定表(3份)、照片(7张)、身份证与户口本(户口本首页与申请人登记卡)复印件(3份)上交街道残联。\n<\/p>","createUser":"admin","createTime":1523163520000,"updateUser":"","updateTime":"","delFlag":0,"delTime":"","remark":""}]}
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
         * totalCount : 7
         * pageNo : 0
         * list : [{"id":"4028801e62a333730162a333734c0000","title":"低保重度残疾人生活补贴政策","disabilityType":"4028801e62a333730162a333734c0001","content":"低保重度残疾人生活补贴政策：82元/月。","createUser":"admin","createTime":1523156480000,"updateUser":"","updateTime":"","delFlag":0,"delTime":"","remark":""},{"id":"4028801e62a333730162a333f6790003","title":"残疾人驾驶骑车学费补贴政策","disabilityType":"4028801e62a333730162a333f6790004","content":"残疾人驾驶骑车学费补贴政策：申请人参加架势汽车所需学费，由区残联一次性给与每人1500元的补贴。","createUser":"admin","createTime":1523156514000,"updateUser":"","updateTime":"","delFlag":0,"delTime":"","remark":""},{"id":"4028801e62a333730162a33448d10006","title":"残疾人学费补贴政策","disabilityType":"4028801e62a333730162a33448d10007","content":"残疾人学费补贴政策：残疾人当年考取大中专院校，学费补贴4000-6000元。","createUser":"admin","createTime":1523156535000,"updateUser":"","updateTime":"","delFlag":0,"delTime":"","remark":""},{"id":"4028801e62a333730162a3351d550009","title":"重度残疾人缴纳居民医疗保险费补助政策","disabilityType":"4028801e62a333730162a3351d55000a","content":"重度残疾人缴纳居民医疗保险费补助政策：<br />\n&nbsp;&nbsp; &nbsp;1、当年享受低保的重度残疾人，选择一档（100元）的给予全额补助，个人不缴费。<br />\n&nbsp;&nbsp; &nbsp;选择二档（220元）的，按70%予以补助，个人缴纳66元。<br />\n&nbsp;&nbsp; &nbsp;2、其他重度残疾人按照缴费标准的70%予以补助，个人缴纳30元或66元。","createUser":"admin","createTime":1523156589000,"updateUser":"","updateTime":"","delFlag":0,"delTime":"","remark":""},{"id":"4028801e62a411d20162a411d2f70000","title":"低保重度不能自理残疾人护理补贴政策","disabilityType":"4028801e62a411d20162a411d2f70001","content":"低保重度不能自理残疾人护理补贴政策：80元/月","createUser":"admin","createTime":1523171054000,"updateUser":"","updateTime":"","delFlag":0,"delTime":"","remark":""},{"id":"4028801e62a411d20162a4135ca80003","title":"残疾人优惠政策","disabilityType":"4028801e62a411d20162a4135ca80004","content":"残疾人优惠政策：残疾人免费乘坐公交和进入国家级景点。","createUser":"admin","createTime":1523171155000,"updateUser":"","updateTime":"","delFlag":0,"delTime":"","remark":""},{"id":"4028805262a331020162a33102ba0000","title":"申请办理残疾人证程序 ","disabilityType":"4028805262a331020162a33102ba0001","content":"<p>\n\t1、 申请人本人(户籍在泰山区)到街道残联领取申请表、评定表(各一式三份)。\n<\/p>\n<p>\n\t2、申请人带身份证与评定表到医院做鉴定(肢体、视力、听力残疾到中医二院；精神、智力残疾到复退军人精神病医院)。\n<\/p>\n<p>\n\t3、申请人带身份证到泰山图片社(岱庙南门大槐树旁)进行照片采集。\n<\/p>\n<p>\n\t4、申请人填写申请表。(各村(居)负责人在申请表的下方签署\"情况属实\"，加盖公章，并签字。) &nbsp;&nbsp;&nbsp;&nbsp;\n<\/p>\n<p>\n\t5、申请人带申请表(3份)、评定表(3份)、照片(7张)、身份证与户口本(户口本首页与申请人登记卡)复印件(3份)上交街道残联。\n<\/p>","createUser":"admin","createTime":1523163520000,"updateUser":"","updateTime":"","delFlag":0,"delTime":"","remark":""}]
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
             * id : 4028801e62a333730162a333734c0000
             * title : 低保重度残疾人生活补贴政策
             * disabilityType : 4028801e62a333730162a333734c0001
             * content : 低保重度残疾人生活补贴政策：82元/月。
             * createUser : admin
             * createTime : 1523156480000
             * updateUser :
             * updateTime :
             * delFlag : 0
             * delTime :
             * remark :
             */

            private String id;
            private String title;
            private String disabilityType;
            private String content;
            private String createUser;
            private long createTime;
            private String updateUser;
            private String updateTime;
            private int delFlag;
            private String delTime;
            private String remark;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDisabilityType() {
                return disabilityType;
            }

            public void setDisabilityType(String disabilityType) {
                this.disabilityType = disabilityType;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public String getDelTime() {
                return delTime;
            }

            public void setDelTime(String delTime) {
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

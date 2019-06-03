package com.shenkangyun.medicalplatform.BeanFolder;

import java.util.List;

public class ApplyToolBean {

    /**
     * status : 0
     * data : {"toolList":[{"id":"402880526203bb40016203c0e2900006","serviceObject":1,"name":"盲表","imageListURL":"{\"json\":[{\"sType\":1,\"attachmentID\":41,\"attachmentUrl\":\"attachment/crfImage/20180308/115628402880526203bb40016203c0c6b70005.png\"}]}","content":"盲表","createUser":"admin","createTime":1520481395000,"updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null},{"id":"40288052620d982e01620d983b320001","serviceObject":1,"name":"圆梦 四节成人折叠盲杖 V-801A","imageListURL":"{\"json\":[{\"sType\":1,\"attachmentID\":45,\"attachmentUrl\":\"attachment/crfImage/20180308/09481940288052620d982e01620d982e370000.jpg\"}]}","content":"圆梦 四节成人折叠盲杖 V-801A","createUser":"admin","createTime":1520646503000,"updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null},{"id":"40288052620d982e01620d9939da0004","serviceObject":1,"name":"盲人魔方V-869A","imageListURL":"{\"json\":[{\"sType\":1,\"attachmentID\":46,\"attachmentUrl\":\"attachment/crfImage/20180308/09492640288052620d982e01620d9933360003.png\"}]}","content":"<h3 style=\"font-size:16px;background:none;color:#666666;font-family:宋体, &quot;font-style:normal;text-align:start;text-indent:0px;\">\n\t盲人魔方V-869A\n<\/h3>","createUser":"admin","createTime":1520646568000,"updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null},{"id":"40288052620d982e01620dad68e00028","serviceObject":1,"name":"语音报时计时器CW9362","imageListURL":"{\"json\":[{\"sType\":1,\"attachmentID\":58,\"attachmentUrl\":\"attachment/crfImage/20180308/10113040288052620d982e01620dad646d0027.jpg\"}]}","content":"<span style=\"color:#4A4A4A;font-family:&quot;font-size:18px;font-style:normal;font-weight:normal;background-color:#FFFFFF;\">语音报时计时器CW9362<\/span>","createUser":"admin","createTime":1520647891000,"updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null},{"id":"40288052620d982e01620daf5530002e","serviceObject":1,"name":"漱口杯、杯子","imageListURL":"{\"json\":[{\"sType\":1,\"attachmentID\":60,\"attachmentUrl\":\"attachment/crfImage/20180308/10133640288052620d982e01620daf51d6002d.jpg\"}]}","content":"漱口杯、杯子","createUser":"admin","createTime":1520648017000,"updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null},{"id":"40288052620d982e01620dafd74a0031","serviceObject":1,"name":"震动闹钟KCVC-002","imageListURL":"{\"json\":[{\"sType\":1,\"attachmentID\":61,\"attachmentUrl\":\"attachment/crfImage/20180308/10140940288052620d982e01620dafd2990030.jpg\"}]}","content":"震动闹钟KCVC-002","createUser":"admin","createTime":1520648050000,"updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null}]}
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
        private List<ToolListBean> toolList;

        public List<ToolListBean> getToolList() {
            return toolList;
        }

        public void setToolList(List<ToolListBean> toolList) {
            this.toolList = toolList;
        }

        public static class ToolListBean {
            /**
             * id : 402880526203bb40016203c0e2900006
             * serviceObject : 1
             * name : 盲表
             * imageListURL : {"json":[{"sType":1,"attachmentID":41,"attachmentUrl":"attachment/crfImage/20180308/115628402880526203bb40016203c0c6b70005.png"}]}
             * content : 盲表
             * createUser : admin
             * createTime : 1520481395000
             * updateUser : null
             * updateTime : null
             * delFlag : 0
             * delTime : null
             * remark : null
             */

            private String id;
            private int serviceObject;
            private String name;
            private String imageListURL;
            private String content;
            private String createUser;
            private long createTime;
            private Object updateUser;
            private Object updateTime;
            private int delFlag;
            private Object delTime;
            private Object remark;
            private boolean checked;

            public boolean isChecked() {
                return checked;
            }

            public void setChecked(boolean checked) {
                this.checked = checked;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getServiceObject() {
                return serviceObject;
            }

            public void setServiceObject(int serviceObject) {
                this.serviceObject = serviceObject;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImageListURL() {
                return imageListURL;
            }

            public void setImageListURL(String imageListURL) {
                this.imageListURL = imageListURL;
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

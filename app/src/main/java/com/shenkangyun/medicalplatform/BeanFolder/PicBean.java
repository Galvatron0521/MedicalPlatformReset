package com.shenkangyun.medicalplatform.BeanFolder;

import java.util.List;

public class PicBean {

    private List<JsonBean> json;

    public List<JsonBean> getJson() {
        return json;
    }

    public void setJson(List<JsonBean> json) {
        this.json = json;
    }

    public static class JsonBean {
        /**
         * sType : 1
         * attachmentID : 124
         * attachmentUrl : attachment/crfImage/20190319/160809402880566994efa3016994fe541b0004.jpg
         */

        private int sType;
        private int attachmentID;
        private String attachmentUrl;

        public int getSType() {
            return sType;
        }

        public void setSType(int sType) {
            this.sType = sType;
        }

        public int getAttachmentID() {
            return attachmentID;
        }

        public void setAttachmentID(int attachmentID) {
            this.attachmentID = attachmentID;
        }

        public String getAttachmentUrl() {
            return attachmentUrl;
        }

        public void setAttachmentUrl(String attachmentUrl) {
            this.attachmentUrl = attachmentUrl;
        }
    }
}

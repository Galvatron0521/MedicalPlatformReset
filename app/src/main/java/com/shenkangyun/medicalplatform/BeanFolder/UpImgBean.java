package com.shenkangyun.medicalplatform.BeanFolder;

public class UpImgBean {

    /**
     * fileId : 121
     * fileType : 1
     * uploadMsg : 上传成功！
     * filePath : D:\apache-tomcat-6.0.37\webapps\disability\attachment\photo\20190319\152421402880526994d639016994d639ea0000.png
     * uploadStatus : 0
     * fileName : 152421402880526994d639016994d639ea0000.png
     * fileUrl : attachment/photo/20190319/152421402880526994d639016994d639ea0000.png
     */

    private int fileId;
    private int fileType;
    private String uploadMsg;
    private String filePath;
    private int uploadStatus;
    private String fileName;
    private String fileUrl;

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getUploadMsg() {
        return uploadMsg;
    }

    public void setUploadMsg(String uploadMsg) {
        this.uploadMsg = uploadMsg;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(int uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}

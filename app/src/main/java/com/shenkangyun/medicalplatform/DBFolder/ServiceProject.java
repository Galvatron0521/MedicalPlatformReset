package com.shenkangyun.medicalplatform.DBFolder;

import org.litepal.crud.LitePalSupport;

/**
 * Created by Administrator on 2018/3/5.
 */

public class ServiceProject extends LitePalSupport {

    private String typeName;
    private int typeDetailCode;
    private String typeCode;
    private String typeDetailName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getTypeDetailCode() {
        return typeDetailCode;
    }

    public void setTypeDetailCode(int typeDetailCode) {
        this.typeDetailCode = typeDetailCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeDetailName() {
        return typeDetailName;
    }

    public void setTypeDetailName(String typeDetailName) {
        this.typeDetailName = typeDetailName;
    }
}

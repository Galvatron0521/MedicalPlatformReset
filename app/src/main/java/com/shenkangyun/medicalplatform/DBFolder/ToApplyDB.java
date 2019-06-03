package com.shenkangyun.medicalplatform.DBFolder;

import org.litepal.crud.LitePalSupport;

public class ToApplyDB extends LitePalSupport {
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

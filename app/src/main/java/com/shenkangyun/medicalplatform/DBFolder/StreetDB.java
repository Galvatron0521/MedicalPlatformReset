package com.shenkangyun.medicalplatform.DBFolder;

import org.litepal.crud.LitePalSupport;

public class StreetDB extends LitePalSupport {
    private String Pid;
    private String Streetname;

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public String getStreetname() {
        return Streetname;
    }

    public void setStreetname(String streetname) {
        Streetname = streetname;
    }
}

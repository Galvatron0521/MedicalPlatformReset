package com.shenkangyun.medicalplatform.BeanFolder;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2018/4/7.
 */

public class AdaptationBean implements MultiItemEntity {
    private String unitName;
    private String unitContacts;
    private String unitPhone;

    public static final int service = 1;
    public static final int street = 2;
    private int itemType;

    public AdaptationBean(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }


    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitContacts() {
        return unitContacts;
    }

    public void setUnitContacts(String unitContacts) {
        this.unitContacts = unitContacts;
    }

    public String getUnitPhone() {
        return unitPhone;
    }

    public void setUnitPhone(String unitPhone) {
        this.unitPhone = unitPhone;
    }
}

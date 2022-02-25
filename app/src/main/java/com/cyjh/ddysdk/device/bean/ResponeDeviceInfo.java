package com.cyjh.ddysdk.device.bean;

import com.cyjh.ddy.base.p033a.NoProGuard;
import java.io.Serializable;

/* loaded from: classes.dex */
public class ResponeDeviceInfo implements NoProGuard, Serializable {
    public String EquipmentType;
    public String ICCid;
    public String IMEI;
    public String IMSI;
    public String Manufacturer;
    public String MobilePhone;
    public long PhoneModelId;
    public String SerialNumber;
    public String WIFI;
    public String WIFIMac;
    public String msg;

    public String getManufacturer() {
        return this.Manufacturer;
    }

    public void setManufacturer(String str) {
        this.Manufacturer = str;
    }

    public String getEquipmentType() {
        return this.EquipmentType;
    }

    public void setEquipmentType(String str) {
        this.EquipmentType = str;
    }

    public String getSerialNumber() {
        return this.SerialNumber;
    }

    public void setSerialNumber(String str) {
        this.SerialNumber = str;
    }

    public String getMobilePhone() {
        return this.MobilePhone;
    }

    public void setMobilePhone(String str) {
        this.MobilePhone = str;
    }

    public String getIMEI() {
        return this.IMEI;
    }

    public void setIMEI(String str) {
        this.IMEI = str;
    }

    public String getIMSI() {
        return this.IMSI;
    }

    public void setIMSI(String str) {
        this.IMSI = str;
    }

    public String getICCid() {
        return this.ICCid;
    }

    public void setICCid(String str) {
        this.ICCid = str;
    }

    public String getWIFI() {
        return this.WIFI;
    }

    public void setWIFI(String str) {
        this.WIFI = str;
    }

    public String getWIFIMac() {
        return this.WIFIMac;
    }

    public void setWIFIMac(String str) {
        this.WIFIMac = str;
    }

    public long getPhoneModelId() {
        return this.PhoneModelId;
    }

    public void setPhoneModelId(long j) {
        this.PhoneModelId = j;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}

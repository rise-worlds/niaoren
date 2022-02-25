package com.cyjh.ddysdk.ddyobs.bean.response;

/* loaded from: classes.dex */
public class AppBlack {
    public static final int BAN_OFF = 0;
    public static final int BAN_ON = 1;
    private String AppName;
    private String AppPackageName;
    private int BanInstall;
    private int BanUpload;

    public String getAppName() {
        return this.AppName;
    }

    public void setAppName(String str) {
        this.AppName = str;
    }

    public String getAppPackageName() {
        return this.AppPackageName;
    }

    public void setAppPackageName(String str) {
        this.AppPackageName = str;
    }

    public int getBanUpload() {
        return this.BanUpload;
    }

    public void setBanUpload(int i) {
        this.BanUpload = i;
    }

    public int getBanInstall() {
        return this.BanInstall;
    }

    public void setBanInstall(int i) {
        this.BanInstall = i;
    }
}

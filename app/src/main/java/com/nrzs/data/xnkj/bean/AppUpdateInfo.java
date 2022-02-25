package com.nrzs.data.xnkj.bean;

/* loaded from: classes2.dex */
public class AppUpdateInfo {
    private int fileSize;

    /* renamed from: id */
    private int f10658id;
    private String installUrl;
    private int packageUpdateType;
    private String updateContent;
    private int updateType;
    private int versionCode;
    private String versionNum;

    public int getPackageUpdateType() {
        return this.packageUpdateType;
    }

    public void setPackageUpdateType(int i) {
        this.packageUpdateType = i;
    }

    public int getId() {
        return this.f10658id;
    }

    public void setId(int i) {
        this.f10658id = i;
    }

    public String getVersionNum() {
        return this.versionNum;
    }

    public void setVersionNum(String str) {
        this.versionNum = str;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(int i) {
        this.versionCode = i;
    }

    public String getUpdateContent() {
        return this.updateContent;
    }

    public void setUpdateContent(String str) {
        this.updateContent = str;
    }

    public int getFileSzie() {
        return this.fileSize;
    }

    public void setFileSzie(int i) {
        this.fileSize = i;
    }

    public int getUpdateType() {
        return this.updateType;
    }

    public void setUpdateType(int i) {
        this.updateType = i;
    }

    public String getInstallUrl() {
        return this.installUrl;
    }

    public void setInstallUrl(String str) {
        this.installUrl = str;
    }

    public String toString() {
        return "AppUpdateInfo{id=" + this.f10658id + ", versionNum='" + this.versionNum + "', versionCode='" + this.versionCode + "', updateContent='" + this.updateContent + "', fileSzie=" + this.fileSize + ", updateType=" + this.updateType + ", installUrl='" + this.installUrl + "'}";
    }
}

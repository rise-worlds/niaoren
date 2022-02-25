package com.cyjh.ddysdk.device.bean;

/* loaded from: classes.dex */
public class ResponseNoticeApps {
    private String icon;
    private boolean isListenNotify;
    private boolean isSystem;
    private String name;
    private String packageName;
    private String packagePath;
    private int versionCode;
    private String versionName;

    public boolean isSystem() {
        return this.isSystem;
    }

    public void setSystem(boolean z) {
        this.isSystem = z;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getPackagePath() {
        return this.packagePath;
    }

    public void setPackagePath(String str) {
        this.packagePath = str;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(int i) {
        this.versionCode = i;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public void setVersionName(String str) {
        this.versionName = str;
    }

    public boolean isListenNotify() {
        return this.isListenNotify;
    }

    public void setListenNotify(boolean z) {
        this.isListenNotify = z;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String str) {
        this.icon = str;
    }
}

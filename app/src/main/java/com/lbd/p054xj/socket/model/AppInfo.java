package com.lbd.p054xj.socket.model;

import java.io.Serializable;

/* renamed from: com.lbd.xj.socket.model.AppInfo */
/* loaded from: classes.dex */
public class AppInfo implements Serializable {
    public String appIcon;
    public String appName;
    public String packageName;
    public String path;
    public int versionCode;
    public String versionName;

    public String toString() {
        return "AppInfo{packageName='" + this.packageName + "', path='" + this.path + "', appName='" + this.appName + "', versionName='" + this.versionName + "', versionCode=" + this.versionCode + ", appIcon='" + this.appIcon + "'}";
    }
}

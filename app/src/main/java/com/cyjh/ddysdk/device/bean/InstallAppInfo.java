package com.cyjh.ddysdk.device.bean;

import com.cyjh.ddy.base.p033a.NoProGuard;
import java.util.Map;

/* loaded from: classes.dex */
public class InstallAppInfo implements NoProGuard {
    public String activityName;
    public String ddyStringExtras;
    public String packageName;
    public boolean runAfterInstall;
    public Map<String, String> stringExtras;
    public String url;

    public InstallAppInfo(String str, String str2, String str3, boolean z, String str4, Map<String, String> map) {
        this.url = str;
        this.packageName = str2;
        this.activityName = str3;
        this.runAfterInstall = z;
        this.ddyStringExtras = str4;
        this.stringExtras = map;
    }
}

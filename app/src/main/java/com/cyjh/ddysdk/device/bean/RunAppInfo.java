package com.cyjh.ddysdk.device.bean;

import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class RunAppInfo {
    public String activityName;
    public String ddyStringExtras;
    public String frontPath;
    public String gameAccount;
    public String packageName;
    public int saveMode;
    public List<String> savePaths;
    public Map<String, String> stringExtras;

    public RunAppInfo(String str, String str2, String str3, Map<String, String> map) {
        this.packageName = str;
        this.activityName = str2;
        this.ddyStringExtras = str3;
        this.stringExtras = map;
    }
}

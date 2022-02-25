package com.lody.virtual.client;

/* loaded from: classes.dex */
public class DexOverride {
    public String newDexPath;
    public String newOdexPath;
    public String originDexPath;
    public String originOdexPath;

    public DexOverride(String str, String str2, String str3, String str4) {
        this.originDexPath = str;
        this.newDexPath = str2;
        this.originOdexPath = str3;
        this.newOdexPath = str4;
    }
}

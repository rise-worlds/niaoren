package com.lbd.xj.socket.model;

import p110z1.GsonUtil;

/* renamed from: com.lbd.xj.socket.model.CheckUpdate */
/* loaded from: classes.dex */
public class CheckUpdate {
    private int isUpdate;
    private String versionName;

    public String getVersionName() {
        return this.versionName;
    }

    public void setVersionName(String str) {
        this.versionName = str;
    }

    public int getIsUpdate() {
        return this.isUpdate;
    }

    public void setIsUpdate(int i) {
        this.isUpdate = i;
    }

    public CheckUpdate(int i) {
        this.isUpdate = i;
    }

    public String toString() {
        return GsonUtil.m13969a(this);
    }
}

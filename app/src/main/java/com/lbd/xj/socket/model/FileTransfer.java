package com.lbd.xj.socket.model;

import p110z1.GsonUtil;

/* renamed from: com.lbd.xj.socket.model.FileTransfer */
/* loaded from: classes.dex */
public class FileTransfer {
    private String dst;
    private String packageName;
    private String path;
    private int progress;
    private String src;

    public FileTransfer(String str, String str2) {
        this.src = str;
        this.dst = str2;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
    }

    public String getSrc() {
        return this.src;
    }

    public void setSrc(String str) {
        this.src = str;
    }

    public String getDst() {
        return this.dst;
    }

    public void setDst(String str) {
        this.dst = str;
    }

    public String toString() {
        return GsonUtil.m13969a(this);
    }
}

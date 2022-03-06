package com.lbd.xj.socket.model;

/* renamed from: com.lbd.xj.socket.model.SetInfo */
/* loaded from: classes.dex */
public class SetInfo {
    private boolean isFloating;
    private boolean isVirtualKey;
    private String type;

    public boolean isFloating() {
        return this.isFloating;
    }

    public void setFloating(boolean z) {
        this.isFloating = z;
    }

    public boolean isVirtualKey() {
        return this.isVirtualKey;
    }

    public void setVirtualKey(boolean z) {
        this.isVirtualKey = z;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String toString() {
        return "SetInfo{isFloating=" + this.isFloating + ", isVirtualKey=" + this.isVirtualKey + '}';
    }
}

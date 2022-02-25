package com.lbd.p054xj.socket.model;

/* renamed from: com.lbd.xj.socket.model.Resolution */
/* loaded from: classes.dex */
public class Resolution {
    private int dpi;
    private int height;
    private int width;

    public Resolution(int i, int i2, int i3) {
        this.width = i;
        this.height = i2;
        this.dpi = i3;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getDpi() {
        return this.dpi;
    }
}

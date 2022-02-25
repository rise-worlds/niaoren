package com.lidroid.xutils.bitmap.core;

/* loaded from: classes.dex */
public class BitmapSize {
    public static final BitmapSize ZERO = new BitmapSize(0, 0);
    private final int height;
    private final int width;

    public BitmapSize(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public BitmapSize scaleDown(int i) {
        return new BitmapSize(this.width / i, this.height / i);
    }

    public BitmapSize scale(float f) {
        return new BitmapSize((int) (this.width * f), (int) (this.height * f));
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String toString() {
        return "_" + this.width + "_" + this.height;
    }
}

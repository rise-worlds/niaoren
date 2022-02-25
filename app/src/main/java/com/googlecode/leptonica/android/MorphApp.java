package com.googlecode.leptonica.android;

/* loaded from: classes.dex */
public class MorphApp {

    /* renamed from: a */
    public static final int f9139a = 0;

    /* renamed from: b */
    public static final int f9140b = 1;

    /* renamed from: c */
    public static final int f9141c = 7;

    /* renamed from: d */
    public static final int f9142d = 7;

    private static native long nativePixFastTophat(long j, int i, int i2, int i3);

    private static native long nativePixTophat(long j, int i, int i2, int i3);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* renamed from: a */
    private static Pix m20234a(Pix pix, int i, int i2, int i3) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (pix.m20224e() != 8) {
            throw new IllegalArgumentException("Source pix depth must be 8bpp");
        } else if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("hsize or vsize < 1");
        } else if (i3 < 0 || i3 > 1) {
            throw new IllegalArgumentException("Type must be L_TOPHAT_BLACK or L_TOPHAT_WHITE");
        } else {
            long nativePixTophat = nativePixTophat(pix.m20232a(), i, i2, i3);
            if (nativePixTophat != 0) {
                return new Pix(nativePixTophat);
            }
            throw new RuntimeException("Failed to perform Tophat on image");
        }
    }

    /* renamed from: a */
    private static Pix m20236a(Pix pix) {
        return m20235a(pix, 1);
    }

    /* renamed from: b */
    private static Pix m20233b(Pix pix) {
        return m20235a(pix, 0);
    }

    /* renamed from: a */
    private static Pix m20235a(Pix pix, int i) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (pix.m20224e() != 8) {
            throw new IllegalArgumentException("Source pix depth must be 8bpp");
        } else if (i < 0 || i > 1) {
            throw new IllegalArgumentException("Type must be L_TOPHAT_BLACK or L_TOPHAT_WHITE");
        } else {
            long nativePixFastTophat = nativePixFastTophat(pix.m20232a(), 7, 7, i);
            if (nativePixFastTophat != 0) {
                return new Pix(nativePixFastTophat);
            }
            throw new RuntimeException("Failed to perform pixFastTophat on image");
        }
    }
}

package com.googlecode.leptonica.android;

/* loaded from: classes.dex */
public class Skew {

    /* renamed from: a */
    public static final float f9162a = 30.0f;

    /* renamed from: b */
    public static final float f9163b = 5.0f;

    /* renamed from: c */
    public static final int f9164c = 8;

    /* renamed from: d */
    public static final int f9165d = 4;

    /* renamed from: e */
    public static final float f9166e = 0.01f;

    private static native float nativeFindSkew(long j, float f, float f2, int i, int i2, float f3);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* renamed from: b */
    private static float m20177b(Pix pix) {
        if (pix != null) {
            return nativeFindSkew(pix.m20232a(), 30.0f, 5.0f, 8, 4, 0.01f);
        }
        throw new IllegalArgumentException("Source pix must be non-null");
    }

    /* renamed from: a */
    private static float m20178a(Pix pix) {
        if (pix != null) {
            return nativeFindSkew(pix.m20232a(), 30.0f, 5.0f, 8, 4, 0.01f);
        }
        throw new IllegalArgumentException("Source pix must be non-null");
    }
}

package com.googlecode.leptonica.android;

/* loaded from: classes.dex */
public class Binarize {

    /* renamed from: a */
    public static final int f9113a = 32;

    /* renamed from: b */
    public static final int f9114b = 32;

    /* renamed from: c */
    public static final int f9115c = 2;

    /* renamed from: d */
    public static final int f9116d = 2;

    /* renamed from: e */
    public static final float f9117e = 0.1f;

    /* renamed from: f */
    public static final int f9118f = 8;

    /* renamed from: g */
    public static final float f9119g = 0.35f;

    /* renamed from: h */
    public static final int f9120h = 1;

    /* renamed from: i */
    public static final int f9121i = 1;

    private static native long nativeOtsuAdaptiveThreshold(long j, int i, int i2, int i3, int i4, float f);

    private static native long nativeSauvolaBinarizeTiled(long j, int i, float f, int i2, int i3);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* renamed from: b */
    private static Pix m20262b(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (pix.m20224e() == 8) {
            long nativeOtsuAdaptiveThreshold = nativeOtsuAdaptiveThreshold(pix.m20232a(), 32, 32, 2, 2, 0.1f);
            if (nativeOtsuAdaptiveThreshold != 0) {
                return new Pix(nativeOtsuAdaptiveThreshold);
            }
            throw new RuntimeException("Failed to perform Otsu adaptive threshold on image");
        } else {
            throw new IllegalArgumentException("Source pix depth must be 8bpp");
        }
    }

    /* renamed from: d */
    private static Pix m20260d(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (pix.m20224e() == 8) {
            long nativeSauvolaBinarizeTiled = nativeSauvolaBinarizeTiled(pix.m20232a(), 8, 0.35f, 1, 1);
            if (nativeSauvolaBinarizeTiled != 0) {
                return new Pix(nativeSauvolaBinarizeTiled);
            }
            throw new RuntimeException("Failed to perform Sauvola binarization on image");
        } else {
            throw new IllegalArgumentException("Source pix depth must be 8bpp");
        }
    }

    /* renamed from: a */
    private static Pix m20263a(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (pix.m20224e() == 8) {
            long nativeOtsuAdaptiveThreshold = nativeOtsuAdaptiveThreshold(pix.m20232a(), 32, 32, 2, 2, 0.1f);
            if (nativeOtsuAdaptiveThreshold != 0) {
                return new Pix(nativeOtsuAdaptiveThreshold);
            }
            throw new RuntimeException("Failed to perform Otsu adaptive threshold on image");
        } else {
            throw new IllegalArgumentException("Source pix depth must be 8bpp");
        }
    }

    /* renamed from: c */
    private static Pix m20261c(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (pix.m20224e() == 8) {
            long nativeSauvolaBinarizeTiled = nativeSauvolaBinarizeTiled(pix.m20232a(), 8, 0.35f, 1, 1);
            if (nativeSauvolaBinarizeTiled != 0) {
                return new Pix(nativeSauvolaBinarizeTiled);
            }
            throw new RuntimeException("Failed to perform Sauvola binarization on image");
        } else {
            throw new IllegalArgumentException("Source pix depth must be 8bpp");
        }
    }
}

package com.googlecode.leptonica.android;

/* loaded from: classes.dex */
public class AdaptiveMap {

    /* renamed from: a */
    public static final int f9105a = 10;

    /* renamed from: b */
    public static final int f9106b = 15;

    /* renamed from: c */
    public static final int f9107c = 40;

    /* renamed from: d */
    public static final int f9108d = 2;

    /* renamed from: e */
    public static final int f9109e = 1;

    /* renamed from: f */
    private static final int f9110f = 16;

    /* renamed from: g */
    private static final int f9111g = 3;

    /* renamed from: h */
    private static final int f9112h = 200;

    private static native long nativeBackgroundNormMorph(long j, int i, int i2, int i3);

    private static native long nativePixContrastNorm(long j, int i, int i2, int i3, int i4, int i5);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* renamed from: b */
    private static Pix m20266b(Pix pix) {
        if (pix != null) {
            long nativeBackgroundNormMorph = nativeBackgroundNormMorph(pix.m20232a(), 16, 3, 200);
            if (nativeBackgroundNormMorph != 0) {
                return new Pix(nativeBackgroundNormMorph);
            }
            throw new RuntimeException("Failed to normalize image background");
        }
        throw new IllegalArgumentException("Source pix must be non-null");
    }

    /* renamed from: d */
    private static Pix m20264d(Pix pix) {
        if (pix != null) {
            long nativePixContrastNorm = nativePixContrastNorm(pix.m20232a(), 10, 15, 40, 2, 1);
            if (nativePixContrastNorm != 0) {
                return new Pix(nativePixContrastNorm);
            }
            throw new RuntimeException("Failed to normalize image contrast");
        }
        throw new IllegalArgumentException("Source pix must be non-null");
    }

    /* renamed from: a */
    private static Pix m20267a(Pix pix) {
        if (pix != null) {
            long nativeBackgroundNormMorph = nativeBackgroundNormMorph(pix.m20232a(), 16, 3, 200);
            if (nativeBackgroundNormMorph != 0) {
                return new Pix(nativeBackgroundNormMorph);
            }
            throw new RuntimeException("Failed to normalize image background");
        }
        throw new IllegalArgumentException("Source pix must be non-null");
    }

    /* renamed from: c */
    private static Pix m20265c(Pix pix) {
        if (pix != null) {
            long nativePixContrastNorm = nativePixContrastNorm(pix.m20232a(), 10, 15, 40, 2, 1);
            if (nativePixContrastNorm != 0) {
                return new Pix(nativePixContrastNorm);
            }
            throw new RuntimeException("Failed to normalize image contrast");
        }
        throw new IllegalArgumentException("Source pix must be non-null");
    }
}

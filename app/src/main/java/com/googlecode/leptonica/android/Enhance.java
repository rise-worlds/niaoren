package com.googlecode.leptonica.android;

/* loaded from: classes.dex */
public class Enhance {

    /* renamed from: a */
    public static final int f9135a = 1;

    /* renamed from: b */
    public static final float f9136b = 0.3f;

    private static native long nativeUnsharpMasking(long j, int i, float f);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* renamed from: b */
    private static Pix m20240b(Pix pix) {
        if (pix != null) {
            long nativeUnsharpMasking = nativeUnsharpMasking(pix.m20232a(), 1, 0.3f);
            if (nativeUnsharpMasking != 0) {
                return new Pix(nativeUnsharpMasking);
            }
            throw new OutOfMemoryError();
        }
        throw new IllegalArgumentException("Source pix must be non-null");
    }

    /* renamed from: a */
    private static Pix m20241a(Pix pix) {
        if (pix != null) {
            long nativeUnsharpMasking = nativeUnsharpMasking(pix.m20232a(), 1, 0.3f);
            if (nativeUnsharpMasking != 0) {
                return new Pix(nativeUnsharpMasking);
            }
            throw new OutOfMemoryError();
        }
        throw new IllegalArgumentException("Source pix must be non-null");
    }
}

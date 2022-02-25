package com.googlecode.leptonica.android;

/* loaded from: classes.dex */
public class GrayQuant {
    private static native long nativePixThresholdToBinary(long j, int i);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* renamed from: a */
    private static Pix m20239a(Pix pix, int i) {
        if (pix != null) {
            int e = pix.m20224e();
            if (e != 4 && e != 8) {
                throw new IllegalArgumentException("Source pix depth must be 4 or 8 bpp");
            } else if (e == 4 && i > 16) {
                throw new IllegalArgumentException("4 bpp thresh not in {0-16}");
            } else if (e != 8 || i <= 256) {
                long nativePixThresholdToBinary = nativePixThresholdToBinary(pix.m20232a(), i);
                if (nativePixThresholdToBinary != 0) {
                    return new Pix(nativePixThresholdToBinary);
                }
                throw new RuntimeException("Failed to perform binarization");
            } else {
                throw new IllegalArgumentException("8 bpp thresh not in {0-256}");
            }
        } else {
            throw new IllegalArgumentException("Source pix must be non-null");
        }
    }
}

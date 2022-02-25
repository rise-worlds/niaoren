package com.googlecode.leptonica.android;

/* loaded from: classes.dex */
public class Rotate {

    /* renamed from: a */
    public static final boolean f9156a = true;

    private static native long nativeRotate(long j, float f, boolean z, boolean z2);

    private static native int nativeRotateOrth(long j, int i);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* renamed from: a */
    private static Pix m20186a(Pix pix, float f, boolean z) {
        if (pix != null) {
            long nativeRotate = nativeRotate(pix.m20232a(), f, z, true);
            if (nativeRotate == 0) {
                return null;
            }
            return new Pix(nativeRotate);
        }
        throw new IllegalArgumentException("Source pix must be non-null");
    }

    /* renamed from: a */
    private static Pix m20185a(Pix pix, int i) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (i < 0 || i > 3) {
            throw new IllegalArgumentException("quads not in {0,1,2,3}");
        } else {
            int nativeRotateOrth = nativeRotateOrth(pix.m20232a(), i);
            if (nativeRotateOrth == 0) {
                return null;
            }
            return new Pix(nativeRotateOrth);
        }
    }

    /* renamed from: a */
    private static Pix m20187a(Pix pix, float f) {
        if (pix != null) {
            long nativeRotate = nativeRotate(pix.m20232a(), f, false, true);
            if (nativeRotate == 0) {
                return null;
            }
            return new Pix(nativeRotate);
        }
        throw new IllegalArgumentException("Source pix must be non-null");
    }

    /* renamed from: b */
    private static Pix m20184b(Pix pix, float f) {
        if (pix != null) {
            long nativeRotate = nativeRotate(pix.m20232a(), f, false, true);
            if (nativeRotate == 0) {
                return null;
            }
            return new Pix(nativeRotate);
        }
        throw new IllegalArgumentException("Source pix must be non-null");
    }
}

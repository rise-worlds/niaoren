package com.googlecode.leptonica.android;

/* loaded from: classes.dex */
public class Clip {
    private static native int nativeClipRectangle(long j, long j2);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* renamed from: a */
    private static Pix m20244a(Pix pix, Box box) {
        int nativeClipRectangle = nativeClipRectangle(pix.m20232a(), box.m20259a());
        if (nativeClipRectangle != 0) {
            return new Pix(nativeClipRectangle);
        }
        return null;
    }
}

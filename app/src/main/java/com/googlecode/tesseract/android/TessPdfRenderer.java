package com.googlecode.tesseract.android;

/* loaded from: classes.dex */
public class TessPdfRenderer {

    /* renamed from: a */
    private final long f9236a;

    /* renamed from: b */
    private boolean f9237b = false;

    private static native long nativeCreate(long j, String str);

    private static native void nativeRecycle(long j);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
        System.loadLibrary("tess");
    }

    private TessPdfRenderer(TessBaseAPI tessBaseAPI, String str) {
        this.f9236a = nativeCreate(tessBaseAPI.f9210a, str);
    }

    /* renamed from: a */
    public final long m20121a() {
        if (!this.f9237b) {
            return this.f9236a;
        }
        throw new IllegalStateException();
    }

    /* renamed from: b */
    private void m20120b() {
        nativeRecycle(this.f9236a);
        this.f9237b = true;
    }
}

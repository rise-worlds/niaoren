package com.googlecode.tesseract.android;

import android.graphics.Rect;

/* loaded from: classes.dex */
public class PageIterator {

    /* renamed from: a */
    private final long f9199a;

    private static native void nativeBegin(long j);

    private static native int[] nativeBoundingBox(long j, int i);

    private static native boolean nativeNext(long j, int i);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
        System.loadLibrary("tess");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PageIterator(long j) {
        this.f9199a = j;
    }

    /* renamed from: a */
    private void m20172a() {
        nativeBegin(this.f9199a);
    }

    /* renamed from: a */
    private boolean m20171a(int i) {
        return nativeNext(this.f9199a, i);
    }

    /* renamed from: b */
    private int[] m20170b(int i) {
        return nativeBoundingBox(this.f9199a, i);
    }

    /* renamed from: c */
    private Rect m20169c(int i) {
        int[] nativeBoundingBox = nativeBoundingBox(this.f9199a, i);
        return new Rect(nativeBoundingBox[0], nativeBoundingBox[1], nativeBoundingBox[2], nativeBoundingBox[3]);
    }
}

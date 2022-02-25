package com.googlecode.leptonica.android;

import android.graphics.Rect;
import android.util.Log;

/* loaded from: classes.dex */
public class Boxa {

    /* renamed from: a */
    private static final String f9129a = Boxa.class.getSimpleName();

    /* renamed from: b */
    private final long f9130b;

    /* renamed from: c */
    private boolean f9131c;

    private static native void nativeDestroy(long j);

    private static native int nativeGetCount(long j);

    private static native boolean nativeGetGeometry(long j, int i, int[] iArr);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    private Boxa(long j) {
        this.f9131c = false;
        this.f9130b = j;
        this.f9131c = false;
    }

    /* renamed from: a */
    private long m20250a() {
        if (!this.f9131c) {
            return this.f9130b;
        }
        throw new IllegalStateException();
    }

    /* renamed from: b */
    private int m20247b() {
        if (!this.f9131c) {
            return nativeGetCount(this.f9130b);
        }
        throw new IllegalStateException();
    }

    /* renamed from: b */
    private int[] m20246b(int i) {
        boolean z = this.f9131c;
        if (!z) {
            int[] iArr = new int[4];
            if (z) {
                throw new IllegalStateException();
            } else if (nativeGetGeometry(this.f9130b, i, iArr)) {
                return iArr;
            } else {
                return null;
            }
        } else {
            throw new IllegalStateException();
        }
    }

    /* renamed from: a */
    private boolean m20248a(int i, int[] iArr) {
        if (!this.f9131c) {
            return nativeGetGeometry(this.f9130b, i, iArr);
        }
        throw new IllegalStateException();
    }

    /* renamed from: c */
    private synchronized void m20245c() {
        if (!this.f9131c) {
            nativeDestroy(this.f9130b);
            this.f9131c = true;
        }
    }

    protected void finalize() throws Throwable {
        try {
            if (!this.f9131c) {
                Log.w(f9129a, "Boxa was not terminated using recycle()");
                m20245c();
            }
        } finally {
            super.finalize();
        }
    }

    /* renamed from: a */
    private Rect m20249a(int i) {
        boolean z = this.f9131c;
        if (!z) {
            int[] iArr = new int[4];
            if (!z) {
                if (!nativeGetGeometry(this.f9130b, i, iArr)) {
                    iArr = null;
                }
                int i2 = iArr[0];
                int i3 = iArr[1];
                return new Rect(i2, i3, iArr[2] + i2, iArr[3] + i3);
            }
            throw new IllegalStateException();
        }
        throw new IllegalStateException();
    }
}

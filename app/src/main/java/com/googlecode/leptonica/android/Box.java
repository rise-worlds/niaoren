package com.googlecode.leptonica.android;

import android.graphics.Rect;
import android.util.Log;

/* loaded from: classes.dex */
public class Box {

    /* renamed from: a */
    public static final int f9122a = 0;

    /* renamed from: b */
    public static final int f9123b = 1;

    /* renamed from: c */
    public static final int f9124c = 2;

    /* renamed from: d */
    public static final int f9125d = 3;

    /* renamed from: e */
    private static final String f9126e = Box.class.getSimpleName();

    /* renamed from: f */
    private final long f9127f;

    /* renamed from: g */
    private boolean f9128g;

    private static native long nativeCreate(int i, int i2, int i3, int i4);

    private static native void nativeDestroy(long j);

    private static native boolean nativeGetGeometry(long j, int[] iArr);

    private static native int nativeGetHeight(long j);

    private static native int nativeGetWidth(long j);

    private static native int nativeGetX(long j);

    private static native int nativeGetY(long j);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Box(long j) {
        this.f9128g = false;
        this.f9127f = j;
        this.f9128g = false;
    }

    private Box(int i, int i2, int i3, int i4) {
        this.f9128g = false;
        if (i < 0 || i2 < 0 || i3 < 0 || i4 < 0) {
            throw new IllegalArgumentException("All box dimensions must be non-negative");
        }
        long nativeCreate = nativeCreate(i, i2, i3, i4);
        if (nativeCreate != 0) {
            this.f9127f = nativeCreate;
            this.f9128g = false;
            return;
        }
        throw new OutOfMemoryError();
    }

    /* renamed from: a */
    public final long m20259a() {
        if (!this.f9128g) {
            return this.f9127f;
        }
        throw new IllegalStateException();
    }

    /* renamed from: b */
    private int m20257b() {
        if (!this.f9128g) {
            return nativeGetX(this.f9127f);
        }
        throw new IllegalStateException();
    }

    /* renamed from: c */
    private int m20256c() {
        if (!this.f9128g) {
            return nativeGetY(this.f9127f);
        }
        throw new IllegalStateException();
    }

    /* renamed from: d */
    private int m20255d() {
        if (!this.f9128g) {
            return nativeGetWidth(this.f9127f);
        }
        throw new IllegalStateException();
    }

    /* renamed from: e */
    private int m20254e() {
        if (!this.f9128g) {
            return nativeGetHeight(this.f9127f);
        }
        throw new IllegalStateException();
    }

    /* renamed from: g */
    private int[] m20252g() {
        int[] iArr = new int[4];
        if (this.f9128g) {
            throw new IllegalStateException();
        } else if (nativeGetGeometry(this.f9127f, iArr)) {
            return iArr;
        } else {
            return null;
        }
    }

    /* renamed from: a */
    private boolean m20258a(int[] iArr) {
        if (!this.f9128g) {
            return nativeGetGeometry(this.f9127f, iArr);
        }
        throw new IllegalStateException();
    }

    /* renamed from: h */
    private void m20251h() {
        if (!this.f9128g) {
            nativeDestroy(this.f9127f);
            this.f9128g = true;
        }
    }

    protected void finalize() throws Throwable {
        try {
            if (!this.f9128g) {
                Log.w(f9126e, "Box was not terminated using recycle()");
                if (!this.f9128g) {
                    nativeDestroy(this.f9127f);
                    this.f9128g = true;
                }
            }
        } finally {
            super.finalize();
        }
    }

    /* renamed from: f */
    private Rect m20253f() {
        int[] iArr = new int[4];
        if (!this.f9128g) {
            if (!nativeGetGeometry(this.f9127f, iArr)) {
                iArr = null;
            }
            int i = iArr[0];
            int i2 = iArr[1];
            return new Rect(i, i2, iArr[2] + i, iArr[3] + i2);
        }
        throw new IllegalStateException();
    }
}

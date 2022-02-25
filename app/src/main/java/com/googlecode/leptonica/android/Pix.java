package com.googlecode.leptonica.android;

import android.graphics.Rect;

/* loaded from: classes.dex */
public class Pix {

    /* renamed from: a */
    public static final int f9143a = 0;

    /* renamed from: b */
    public static final int f9144b = 1;

    /* renamed from: c */
    public static final int f9145c = 2;

    /* renamed from: d */
    final long f9146d;

    /* renamed from: e */
    boolean f9147e;

    private static native long nativeClone(long j);

    private static native long nativeCopy(long j);

    private static native long nativeCreateFromData(byte[] bArr, int i, int i2, int i3);

    private static native long nativeCreatePix(int i, int i2, int i3);

    private static native void nativeDestroy(long j);

    private static native byte[] nativeGetData(long j);

    private static native int nativeGetDepth(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean nativeGetDimensions(long j, int[] iArr);

    private static native int nativeGetHeight(long j);

    private static native int nativeGetPixel(long j, int i, int i2);

    private static native int nativeGetRefCount(long j);

    private static native int nativeGetWidth(long j);

    private static native boolean nativeInvert(long j);

    private static native void nativeSetPixel(long j, int i, int i2, int i3);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    public Pix(long j) {
        this.f9146d = j;
        this.f9147e = false;
    }

    private Pix(int i, int i2, int i3) {
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("Pix width and height must be > 0");
        } else if (i3 == 1 || i3 == 2 || i3 == 4 || i3 == 8 || i3 == 16 || i3 == 24 || i3 == 32) {
            this.f9146d = nativeCreatePix(i, i2, i3);
            this.f9147e = false;
        } else {
            throw new IllegalArgumentException("Depth must be one of 1, 2, 4, 8, 16, or 32");
        }
    }

    /* renamed from: a */
    public final long m20232a() {
        if (!this.f9147e) {
            return this.f9146d;
        }
        throw new IllegalStateException();
    }

    /* renamed from: f */
    private byte[] m20223f() {
        if (!this.f9147e) {
            byte[] nativeGetData = nativeGetData(this.f9146d);
            if (nativeGetData != null) {
                return nativeGetData;
            }
            throw new RuntimeException("native getData failed");
        }
        throw new IllegalStateException();
    }

    /* renamed from: g */
    private int[] m20222g() {
        boolean z = this.f9147e;
        if (!z) {
            int[] iArr = new int[3];
            if (z) {
                throw new IllegalStateException();
            } else if (nativeGetDimensions(this.f9146d, iArr)) {
                return iArr;
            } else {
                return null;
            }
        } else {
            throw new IllegalStateException();
        }
    }

    /* renamed from: a */
    private boolean m20228a(int[] iArr) {
        if (!this.f9147e) {
            return nativeGetDimensions(this.f9146d, iArr);
        }
        throw new IllegalStateException();
    }

    /* renamed from: h */
    private Pix m20221h() {
        if (!this.f9147e) {
            long nativeClone = nativeClone(this.f9146d);
            if (nativeClone != 0) {
                return new Pix(nativeClone);
            }
            throw new OutOfMemoryError();
        }
        throw new IllegalStateException();
    }

    /* renamed from: i */
    private Pix m20220i() {
        if (!this.f9147e) {
            long nativeCopy = nativeCopy(this.f9146d);
            if (nativeCopy != 0) {
                return new Pix(nativeCopy);
            }
            throw new OutOfMemoryError();
        }
        throw new IllegalStateException();
    }

    /* renamed from: j */
    private boolean m20219j() {
        if (!this.f9147e) {
            return nativeInvert(this.f9146d);
        }
        throw new IllegalStateException();
    }

    /* renamed from: b */
    public final void m20227b() {
        if (!this.f9147e) {
            nativeDestroy(this.f9146d);
            this.f9147e = true;
        }
    }

    /* renamed from: a */
    private static Pix m20229a(byte[] bArr, int i, int i2, int i3) {
        long nativeCreateFromData = nativeCreateFromData(bArr, i, i2, i3);
        if (nativeCreateFromData != 0) {
            return new Pix(nativeCreateFromData);
        }
        throw new OutOfMemoryError();
    }

    /* renamed from: k */
    private Rect m20218k() {
        return new Rect(0, 0, m20226c(), m20225d());
    }

    /* renamed from: c */
    public final int m20226c() {
        if (!this.f9147e) {
            return nativeGetWidth(this.f9146d);
        }
        throw new IllegalStateException();
    }

    /* renamed from: d */
    public final int m20225d() {
        if (!this.f9147e) {
            return nativeGetHeight(this.f9146d);
        }
        throw new IllegalStateException();
    }

    /* renamed from: e */
    public final int m20224e() {
        if (!this.f9147e) {
            return nativeGetDepth(this.f9146d);
        }
        throw new IllegalStateException();
    }

    /* renamed from: l */
    private int m20217l() {
        return nativeGetRefCount(this.f9146d);
    }

    /* renamed from: a */
    private int m20231a(int i, int i2) {
        if (this.f9147e) {
            throw new IllegalStateException();
        } else if (i < 0 || i >= m20226c()) {
            throw new IllegalArgumentException("Supplied x coordinate exceeds image bounds");
        } else if (i2 >= 0 && i2 < m20225d()) {
            return nativeGetPixel(this.f9146d, i, i2);
        } else {
            throw new IllegalArgumentException("Supplied y coordinate exceeds image bounds");
        }
    }

    /* renamed from: a */
    private void m20230a(int i, int i2, int i3) {
        if (this.f9147e) {
            throw new IllegalStateException();
        } else if (i < 0 || i >= m20226c()) {
            throw new IllegalArgumentException("Supplied x coordinate exceeds image bounds");
        } else if (i2 < 0 || i2 >= m20225d()) {
            throw new IllegalArgumentException("Supplied y coordinate exceeds image bounds");
        } else {
            nativeSetPixel(this.f9146d, i, i2, i3);
        }
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        if (!this.f9147e) {
            long nativeClone = nativeClone(this.f9146d);
            if (nativeClone != 0) {
                return new Pix(nativeClone);
            }
            throw new OutOfMemoryError();
        }
        throw new IllegalStateException();
    }
}

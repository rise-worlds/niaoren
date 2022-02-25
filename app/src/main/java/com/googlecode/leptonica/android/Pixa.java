package com.googlecode.leptonica.android;

import android.graphics.Rect;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class Pixa implements Iterable<Pix> {

    /* renamed from: e */
    private static final String f9148e = Pixa.class.getSimpleName();

    /* renamed from: a */
    final long f9149a;

    /* renamed from: b */
    final int f9150b;

    /* renamed from: c */
    final int f9151c;

    /* renamed from: d */
    boolean f9152d = false;

    private static native void nativeAdd(long j, long j2, long j3, int i);

    private static native void nativeAddBox(long j, long j2, int i);

    private static native void nativeAddPix(long j, long j2, int i);

    private static native int nativeCopy(long j);

    private static native int nativeCreate(int i);

    private static native void nativeDestroy(long j);

    private static native long nativeGetBox(long j, int i);

    private static native boolean nativeGetBoxGeometry(long j, int i, int[] iArr);

    static native int nativeGetCount(long j);

    static native long nativeGetPix(long j, int i);

    private static native boolean nativeJoin(long j, long j2);

    private static native void nativeMergeAndReplacePix(long j, int i, int i2);

    private static native void nativeReplacePix(long j, int i, long j2, long j3);

    private static native int nativeSort(long j, int i, int i2);

    private static native boolean nativeWriteToFileRandomCmap(long j, String str, int i, int i2);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* renamed from: b */
    private static Pixa m20205b(int i) {
        long nativeCreate = nativeCreate(i);
        if (nativeCreate != 0) {
            return new Pixa(nativeCreate, 0, 0);
        }
        throw new OutOfMemoryError();
    }

    public Pixa(long j, int i, int i2) {
        this.f9149a = j;
        this.f9150b = i;
        this.f9151c = i2;
    }

    /* renamed from: a */
    private long m20216a() {
        if (!this.f9152d) {
            return this.f9149a;
        }
        throw new IllegalStateException();
    }

    /* renamed from: b */
    private Pixa m20206b() {
        if (!this.f9152d) {
            int nativeCopy = nativeCopy(this.f9149a);
            if (nativeCopy != 0) {
                return new Pixa(nativeCopy, this.f9150b, this.f9151c);
            }
            throw new OutOfMemoryError();
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    private Pixa m20214a(int i, int i2) {
        if (!this.f9152d) {
            int nativeSort = nativeSort(this.f9149a, i, i2);
            if (nativeSort != 0) {
                return new Pixa(nativeSort, this.f9150b, this.f9151c);
            }
            throw new OutOfMemoryError();
        }
        throw new IllegalStateException();
    }

    /* renamed from: c */
    private int m20203c() {
        if (!this.f9152d) {
            return nativeGetCount(this.f9149a);
        }
        throw new IllegalStateException();
    }

    /* renamed from: d */
    private synchronized void m20201d() {
        if (!this.f9152d) {
            nativeDestroy(this.f9149a);
            this.f9152d = true;
        }
    }

    protected void finalize() throws Throwable {
        try {
            if (!this.f9152d) {
                Log.w(f9148e, "Pixa was not terminated using recycle()");
                m20201d();
            }
        } finally {
            super.finalize();
        }
    }

    /* renamed from: a */
    private boolean m20208a(Pixa pixa) {
        if (!this.f9152d) {
            return nativeJoin(this.f9149a, pixa.f9149a);
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    private void m20210a(Pix pix, int i) {
        if (!this.f9152d) {
            nativeAddPix(this.f9149a, pix.m20232a(), i);
            return;
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    private void m20211a(Box box, int i) {
        if (!this.f9152d) {
            nativeAddBox(this.f9149a, box.m20259a(), i);
            return;
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    private void m20209a(Pix pix, Box box, int i) {
        if (!this.f9152d) {
            nativeAdd(this.f9149a, pix.m20232a(), box.m20259a(), i);
            return;
        }
        throw new IllegalStateException();
    }

    /* renamed from: c */
    private Box m20202c(int i) {
        if (!this.f9152d) {
            long nativeGetBox = nativeGetBox(this.f9149a, i);
            if (nativeGetBox == 0) {
                return null;
            }
            return new Box(nativeGetBox);
        }
        throw new IllegalStateException();
    }

    /* renamed from: d */
    private Pix m20200d(int i) {
        if (!this.f9152d) {
            long nativeGetPix = nativeGetPix(this.f9149a, i);
            if (nativeGetPix == 0) {
                return null;
            }
            return new Pix(nativeGetPix);
        }
        throw new IllegalStateException();
    }

    /* renamed from: e */
    private int m20199e() {
        if (!this.f9152d) {
            return this.f9150b;
        }
        throw new IllegalStateException();
    }

    /* renamed from: f */
    private int m20197f() {
        if (!this.f9152d) {
            return this.f9151c;
        }
        throw new IllegalStateException();
    }

    /* renamed from: g */
    private Rect m20195g() {
        if (!this.f9152d) {
            return new Rect(0, 0, this.f9150b, this.f9151c);
        }
        throw new IllegalStateException();
    }

    /* renamed from: e */
    private int[] m20198e(int i) {
        if (!this.f9152d) {
            int[] iArr = new int[4];
            if (m20212a(i, iArr)) {
                return iArr;
            }
            return null;
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    private boolean m20212a(int i, int[] iArr) {
        if (!this.f9152d) {
            return nativeGetBoxGeometry(this.f9149a, i, iArr);
        }
        throw new IllegalStateException();
    }

    /* renamed from: h */
    private ArrayList<Rect> m20194h() {
        if (!this.f9152d) {
            int nativeGetCount = nativeGetCount(this.f9149a);
            int[] iArr = new int[4];
            ArrayList<Rect> arrayList = new ArrayList<>(nativeGetCount);
            for (int i = 0; i < nativeGetCount; i++) {
                m20212a(i, iArr);
                int i2 = iArr[0];
                int i3 = iArr[1];
                arrayList.add(new Rect(i2, i3, iArr[2] + i2, iArr[3] + i3));
            }
            return arrayList;
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    private void m20213a(int i, Pix pix, Box box) {
        if (!this.f9152d) {
            nativeReplacePix(this.f9149a, i, pix.m20232a(), box.m20259a());
            return;
        }
        throw new IllegalStateException();
    }

    /* renamed from: b */
    private void m20204b(int i, int i2) {
        if (!this.f9152d) {
            nativeMergeAndReplacePix(this.f9149a, i, i2);
            return;
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    private boolean m20207a(File file) {
        if (!this.f9152d) {
            return nativeWriteToFileRandomCmap(this.f9149a, file.getAbsolutePath(), this.f9150b, this.f9151c);
        }
        throw new IllegalStateException();
    }

    @Override // java.lang.Iterable
    public Iterator<Pix> iterator() {
        return new C1424a(this, (byte) 0);
    }

    /* renamed from: com.googlecode.leptonica.android.Pixa$a */
    /* loaded from: classes.dex */
    private class C1424a implements Iterator<Pix> {

        /* renamed from: b */
        private int f9154b;

        /* synthetic */ C1424a(Pixa pixa, byte b) {
            this();
        }

        private C1424a() {
            this.f9154b = 0;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            Pixa pixa = Pixa.this;
            if (!pixa.f9152d) {
                int nativeGetCount = Pixa.nativeGetCount(pixa.f9149a);
                return nativeGetCount > 0 && this.f9154b < nativeGetCount;
            }
            throw new IllegalStateException();
        }

        /* renamed from: a */
        private Pix m20193a() {
            Pixa pixa = Pixa.this;
            int i = this.f9154b;
            this.f9154b = i + 1;
            if (!pixa.f9152d) {
                long nativeGetPix = Pixa.nativeGetPix(pixa.f9149a, i);
                if (nativeGetPix == 0) {
                    return null;
                }
                return new Pix(nativeGetPix);
            }
            throw new IllegalStateException();
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Iterator
        public final /* synthetic */ Pix next() {
            Pixa pixa = Pixa.this;
            int i = this.f9154b;
            this.f9154b = i + 1;
            if (!pixa.f9152d) {
                long nativeGetPix = Pixa.nativeGetPix(pixa.f9149a, i);
                if (nativeGetPix == 0) {
                    return null;
                }
                return new Pix(nativeGetPix);
            }
            throw new IllegalStateException();
        }
    }

    /* renamed from: a */
    private static Pixa m20215a(int i) {
        long nativeCreate = nativeCreate(i);
        if (nativeCreate != 0) {
            return new Pixa(nativeCreate, 0, 0);
        }
        throw new OutOfMemoryError();
    }

    /* renamed from: f */
    private Rect m20196f(int i) {
        if (!this.f9152d) {
            int[] iArr = new int[4];
            if (!m20212a(i, iArr)) {
                iArr = null;
            }
            if (iArr == null) {
                return null;
            }
            int i2 = iArr[0];
            int i3 = iArr[1];
            return new Rect(i2, i3, iArr[2] + i2, iArr[3] + i3);
        }
        throw new IllegalStateException();
    }
}

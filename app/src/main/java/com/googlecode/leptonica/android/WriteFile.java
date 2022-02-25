package com.googlecode.leptonica.android;

import android.graphics.Bitmap;
import java.io.File;

/* loaded from: classes.dex */
public class WriteFile {
    private static native boolean nativeWriteBitmap(long j, Bitmap bitmap);

    private static native int nativeWriteBytes8(long j, byte[] bArr);

    private static native boolean nativeWriteImpliedFormat(long j, String str);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* renamed from: b */
    private static byte[] m20173b(Pix pix) {
        if (pix != null) {
            byte[] bArr = new byte[pix.m20226c() * pix.m20225d()];
            if (pix.m20224e() != 8) {
                Pix a = Convert.m20243a(pix);
                m20174a(a, bArr);
                a.m20227b();
            } else {
                m20174a(pix, bArr);
            }
            return bArr;
        }
        throw new IllegalArgumentException("Source pix must be non-null");
    }

    /* renamed from: a */
    private static int m20174a(Pix pix, byte[] bArr) {
        if (pix != null) {
            if (bArr.length >= pix.m20226c() * pix.m20225d()) {
                return nativeWriteBytes8(pix.m20232a(), bArr);
            }
            throw new IllegalArgumentException("Data array must be large enough to hold image bytes");
        }
        throw new IllegalArgumentException("Source pix must be non-null");
    }

    /* renamed from: a */
    private static boolean m20175a(Pix pix, File file) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (file != null) {
            return nativeWriteImpliedFormat(pix.m20232a(), file.getAbsolutePath());
        } else {
            throw new IllegalArgumentException("File must be non-null");
        }
    }

    /* renamed from: a */
    public static Bitmap m20176a(Pix pix) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (!pix.f9147e) {
            int[] iArr = new int[3];
            if (!pix.f9147e) {
                if (!Pix.nativeGetDimensions(pix.f9146d, iArr)) {
                    iArr = null;
                }
                Bitmap createBitmap = Bitmap.createBitmap(iArr[0], iArr[1], Bitmap.Config.ARGB_8888);
                if (nativeWriteBitmap(pix.m20232a(), createBitmap)) {
                    return createBitmap;
                }
                createBitmap.recycle();
                return null;
            }
            throw new IllegalStateException();
        } else {
            throw new IllegalStateException();
        }
    }
}

package com.googlecode.leptonica.android;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class JpegIO {

    /* renamed from: a */
    public static final int f9137a = 85;

    /* renamed from: b */
    public static final boolean f9138b = false;

    private static native byte[] nativeCompressToJpeg(long j, int i, boolean z);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* renamed from: a */
    private static byte[] m20238a(Pix pix) {
        return m20237b(pix);
    }

    /* renamed from: b */
    private static byte[] m20237b(Pix pix) {
        if (pix != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bitmap a = WriteFile.m20176a(pix);
            a.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
            a.recycle();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return byteArray;
        }
        throw new IllegalArgumentException("Source pix must be non-null");
    }
}

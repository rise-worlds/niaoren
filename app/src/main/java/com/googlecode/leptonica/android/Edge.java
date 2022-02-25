package com.googlecode.leptonica.android;

/* loaded from: classes.dex */
public class Edge {

    /* renamed from: a */
    public static final int f9132a = 0;

    /* renamed from: b */
    public static final int f9133b = 1;

    /* renamed from: c */
    public static final int f9134c = 2;

    private static native long nativePixSobelEdgeFilter(long j, int i);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* renamed from: a */
    private static Pix m20242a(Pix pix, int i) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (pix.m20224e() != 8) {
            throw new IllegalArgumentException("Source pix depth must be 8bpp");
        } else if (i < 0 || i > 2) {
            throw new IllegalArgumentException("Invalid orientation flag");
        } else {
            long nativePixSobelEdgeFilter = nativePixSobelEdgeFilter(pix.m20232a(), i);
            if (nativePixSobelEdgeFilter != 0) {
                return new Pix(nativePixSobelEdgeFilter);
            }
            throw new RuntimeException("Failed to perform Sobel edge filter on image");
        }
    }
}

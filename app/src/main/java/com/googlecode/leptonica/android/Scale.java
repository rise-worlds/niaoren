package com.googlecode.leptonica.android;

/* loaded from: classes.dex */
public class Scale {
    private static native long nativeScale(long j, float f, float f2);

    private static native long nativeScaleGeneral(long j, float f, float f2, float f3, int i);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier removed */
    /* renamed from: com.googlecode.leptonica.android.Scale$a */
    /* loaded from: classes.dex */
    public static final class EnumC1426a extends Enum<EnumC1426a> {

        /* renamed from: a */
        public static final int f9158a = 1;

        /* renamed from: b */
        public static final int f9159b = 2;

        /* renamed from: c */
        public static final int f9160c = 3;

        /* renamed from: d */
        private static final /* synthetic */ int[] f9161d = {f9158a, f9159b, f9160c};

        private EnumC1426a(String str, int i) {
        }

        /* renamed from: a */
        public static int[] m20179a() {
            return (int[]) f9161d.clone();
        }
    }

    /* renamed from: a */
    private static Pix m20181a(Pix pix, int i, int i2, int i3) {
        if (pix != null) {
            float c = i / pix.m20226c();
            float d = i2 / pix.m20225d();
            switch (C14251.f9157a[i3 - 1]) {
                case 2:
                    c = Math.min(c, d);
                    d = c;
                    break;
                case 3:
                    c = Math.min(1.0f, Math.min(c, d));
                    d = c;
                    break;
            }
            return m20182a(pix, c, d);
        }
        throw new IllegalArgumentException("Source pix must be non-null");
    }

    /* renamed from: com.googlecode.leptonica.android.Scale$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C14251 {

        /* renamed from: a */
        static final /* synthetic */ int[] f9157a = new int[EnumC1426a.m20179a().length];

        static {
            try {
                f9157a[EnumC1426a.f9158a - 1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9157a[EnumC1426a.f9159b - 1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9157a[EnumC1426a.f9160c - 1] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* renamed from: a */
    private static Pix m20183a(Pix pix, float f) {
        return m20182a(pix, f, f);
    }

    /* renamed from: b */
    private static Pix m20180b(Pix pix, float f) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (f > 0.0f) {
            return new Pix(nativeScaleGeneral(pix.m20232a(), f, f, 0.0f, 0));
        } else {
            throw new IllegalArgumentException("Scaling factor must be positive");
        }
    }

    /* renamed from: a */
    private static Pix m20182a(Pix pix, float f, float f2) {
        if (pix == null) {
            throw new IllegalArgumentException("Source pix must be non-null");
        } else if (f <= 0.0f) {
            throw new IllegalArgumentException("X scaling factor must be positive");
        } else if (f2 > 0.0f) {
            long nativeScale = nativeScale(pix.m20232a(), f, f2);
            if (nativeScale != 0) {
                return new Pix(nativeScale);
            }
            throw new RuntimeException("Failed to natively scale pix");
        } else {
            throw new IllegalArgumentException("Y scaling factor must be positive");
        }
    }
}

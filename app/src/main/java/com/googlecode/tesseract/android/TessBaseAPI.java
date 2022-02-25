package com.googlecode.tesseract.android;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.googlecode.leptonica.android.Pix;
import com.googlecode.leptonica.android.Pixa;
import com.googlecode.leptonica.android.ReadFile;
import java.io.File;

/* loaded from: classes.dex */
public class TessBaseAPI {

    /* renamed from: b */
    public static final String f9201b = "tessedit_char_whitelist";

    /* renamed from: c */
    public static final String f9202c = "tessedit_char_blacklist";

    /* renamed from: d */
    public static final String f9203d = "save_blob_choices";

    /* renamed from: e */
    public static final String f9204e = "T";

    /* renamed from: f */
    public static final String f9205f = "F";

    /* renamed from: g */
    public static final int f9206g = 0;
    @Deprecated

    /* renamed from: h */
    public static final int f9207h = 1;
    @Deprecated

    /* renamed from: i */
    public static final int f9208i = 2;

    /* renamed from: j */
    public static final int f9209j = 3;

    /* renamed from: a */
    long f9210a;

    /* renamed from: k */
    private AbstractC1429c f9211k;

    /* renamed from: l */
    private boolean f9212l;

    /* renamed from: com.googlecode.tesseract.android.TessBaseAPI$a */
    /* loaded from: classes.dex */
    public static final class C1427a {

        /* renamed from: a */
        public static final int f9213a = 0;

        /* renamed from: b */
        public static final int f9214b = 1;

        /* renamed from: c */
        public static final int f9215c = 2;

        /* renamed from: d */
        public static final int f9216d = 3;

        /* renamed from: e */
        public static final int f9217e = 4;
    }

    /* renamed from: com.googlecode.tesseract.android.TessBaseAPI$b */
    /* loaded from: classes.dex */
    public static final class C1428b {

        /* renamed from: a */
        public static final int f9218a = 0;

        /* renamed from: b */
        public static final int f9219b = 1;

        /* renamed from: c */
        public static final int f9220c = 2;

        /* renamed from: d */
        public static final int f9221d = 3;

        /* renamed from: e */
        public static final int f9222e = 4;

        /* renamed from: f */
        public static final int f9223f = 5;

        /* renamed from: g */
        public static final int f9224g = 6;

        /* renamed from: h */
        public static final int f9225h = 7;

        /* renamed from: i */
        public static final int f9226i = 8;

        /* renamed from: j */
        public static final int f9227j = 9;

        /* renamed from: k */
        public static final int f9228k = 10;

        /* renamed from: l */
        public static final int f9229l = 11;

        /* renamed from: m */
        public static final int f9230m = 12;

        /* renamed from: n */
        public static final int f9231n = 13;
    }

    /* renamed from: com.googlecode.tesseract.android.TessBaseAPI$c */
    /* loaded from: classes.dex */
    public interface AbstractC1429c {
        /* renamed from: a */
        void m20125a();
    }

    private native boolean nativeAddPageToDocument(long j, long j2, String str, long j3);

    private native boolean nativeBeginDocument(long j, String str);

    private static native void nativeClassInit();

    private native void nativeClear(long j);

    private native long nativeConstruct();

    private native void nativeEnd(long j);

    private native boolean nativeEndDocument(long j);

    private native String nativeGetBoxText(long j, int i);

    private native long nativeGetConnectedComponents(long j);

    private native String nativeGetHOCRText(long j, int i);

    private native String nativeGetInitLanguagesAsString(long j);

    private native int nativeGetPageSegMode(long j);

    private native long nativeGetRegions(long j);

    private native long nativeGetResultIterator(long j);

    private native long nativeGetStrips(long j);

    private native long nativeGetTextlines(long j);

    private native long nativeGetThresholdedImage(long j);

    private native String nativeGetUTF8Text(long j);

    private native String nativeGetVersion(long j);

    private native long nativeGetWords(long j);

    private native boolean nativeInit(long j, String str, String str2);

    private native boolean nativeInitOem(long j, String str, String str2, int i);

    private native int nativeMeanConfidence(long j);

    private native void nativeReadConfigFile(long j, String str);

    private native void nativeSetDebug(long j, boolean z);

    private native void nativeSetImageBytes(long j, byte[] bArr, int i, int i2, int i3, int i4);

    private native void nativeSetImagePix(long j, long j2);

    private native void nativeSetInputName(long j, String str);

    private native void nativeSetOutputName(long j, String str);

    private native void nativeSetPageSegMode(long j, int i);

    private native void nativeSetRectangle(long j, int i, int i2, int i3, int i4);

    private native boolean nativeSetVariable(long j, String str, String str2);

    private native void nativeStop(long j);

    private native int[] nativeWordConfidences(long j);

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lept");
        System.loadLibrary("tess");
        nativeClassInit();
    }

    /* renamed from: com.googlecode.tesseract.android.TessBaseAPI$d */
    /* loaded from: classes.dex */
    public class C1430d {

        /* renamed from: b */
        private final int f9233b;

        /* renamed from: c */
        private final Rect f9234c;

        /* renamed from: d */
        private final Rect f9235d;

        public C1430d(int i, Rect rect, Rect rect2) {
            this.f9233b = i;
            this.f9234c = rect;
            this.f9235d = rect2;
        }

        /* renamed from: a */
        private int m20124a() {
            return this.f9233b;
        }

        /* renamed from: b */
        private Rect m20123b() {
            return this.f9234c;
        }

        /* renamed from: c */
        private Rect m20122c() {
            return this.f9235d;
        }
    }

    public TessBaseAPI() {
        this.f9210a = nativeConstruct();
        if (this.f9210a != 0) {
            this.f9212l = false;
            return;
        }
        throw new RuntimeException("Can't create TessBaseApi object");
    }

    private TessBaseAPI(AbstractC1429c cVar) {
        this();
        this.f9211k = cVar;
    }

    /* renamed from: b */
    private boolean m20144b(String str, String str2) {
        String[] split;
        if (str != null) {
            String str3 = !str.endsWith(File.separator) ? str + File.separator : str;
            if (new File(str3).exists()) {
                File file = new File(str3 + "tessdata");
                if (!file.exists() || !file.isDirectory()) {
                    throw new IllegalArgumentException("Data path must contain subfolder tessdata!");
                }
                for (String str4 : str2.split("\\+")) {
                    if (!str4.startsWith("~")) {
                        File file2 = new File(file + File.separator + str4 + ".traineddata");
                        if (!file2.exists()) {
                            throw new IllegalArgumentException("Data file not found at " + file2);
                        }
                    }
                }
                boolean nativeInitOem = nativeInitOem(this.f9210a, str3, str2, 3);
                if (nativeInitOem) {
                    this.f9212l = false;
                }
                return nativeInitOem;
            }
            throw new IllegalArgumentException("Data path does not exist!");
        }
        throw new IllegalArgumentException("Data path must not be null!");
    }

    /* renamed from: b */
    private String m20148b() {
        if (!this.f9212l) {
            return nativeGetInitLanguagesAsString(this.f9210a);
        }
        throw new IllegalStateException();
    }

    /* renamed from: c */
    private void m20143c() {
        if (!this.f9212l) {
            nativeClear(this.f9210a);
            return;
        }
        throw new IllegalStateException();
    }

    /* renamed from: d */
    private void m20139d() {
        if (!this.f9212l) {
            nativeEnd(this.f9210a);
            this.f9212l = true;
        }
    }

    /* renamed from: c */
    private boolean m20140c(String str, String str2) {
        if (!this.f9212l) {
            return nativeSetVariable(this.f9210a, str, str2);
        }
        throw new IllegalStateException();
    }

    /* renamed from: e */
    private int m20138e() {
        if (!this.f9212l) {
            return nativeGetPageSegMode(this.f9210a);
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    private void m20161a(int i) {
        if (!this.f9212l) {
            nativeSetPageSegMode(this.f9210a, i);
            return;
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    private void m20150a(boolean z) {
        if (!this.f9212l) {
            nativeSetDebug(this.f9210a, z);
            return;
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    private void m20158a(Rect rect) {
        if (!this.f9212l) {
            int i = rect.left;
            int i2 = rect.top;
            int width = rect.width();
            int height = rect.height();
            if (!this.f9212l) {
                nativeSetRectangle(this.f9210a, i, i2, width, height);
                return;
            }
            throw new IllegalStateException();
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    private void m20160a(int i, int i2, int i3, int i4) {
        if (!this.f9212l) {
            nativeSetRectangle(this.f9210a, i, i2, i3, i4);
            return;
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    private void m20153a(File file) {
        if (!this.f9212l) {
            Pix a = ReadFile.m20190a(file);
            if (a != null) {
                nativeSetImagePix(this.f9210a, a.m20232a());
                a.m20227b();
                return;
            }
            throw new RuntimeException("Failed to read image file");
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    public final void m20159a(Bitmap bitmap) {
        if (!this.f9212l) {
            Pix a = ReadFile.m20192a(bitmap);
            if (a != null) {
                nativeSetImagePix(this.f9210a, a.m20232a());
                a.m20227b();
                return;
            }
            throw new RuntimeException("Failed to read bitmap");
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    private void m20157a(Pix pix) {
        if (!this.f9212l) {
            nativeSetImagePix(this.f9210a, pix.m20232a());
            return;
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    private void m20149a(byte[] bArr, int i, int i2, int i3, int i4) {
        if (!this.f9212l) {
            nativeSetImageBytes(this.f9210a, bArr, i, i2, i3, i4);
            return;
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    public final String m20162a() {
        if (!this.f9212l) {
            String nativeGetUTF8Text = nativeGetUTF8Text(this.f9210a);
            if (nativeGetUTF8Text != null) {
                return nativeGetUTF8Text.trim();
            }
            return null;
        }
        throw new IllegalStateException();
    }

    /* renamed from: f */
    private int m20137f() {
        if (!this.f9212l) {
            return nativeMeanConfidence(this.f9210a);
        }
        throw new IllegalStateException();
    }

    /* renamed from: g */
    private int[] m20136g() {
        if (!this.f9212l) {
            int[] nativeWordConfidences = nativeWordConfidences(this.f9210a);
            return nativeWordConfidences == null ? new int[0] : nativeWordConfidences;
        }
        throw new IllegalStateException();
    }

    /* renamed from: h */
    private Pix m20135h() {
        if (!this.f9212l) {
            return new Pix(nativeGetThresholdedImage(this.f9210a));
        }
        throw new IllegalStateException();
    }

    /* renamed from: i */
    private Pixa m20134i() {
        if (!this.f9212l) {
            return new Pixa(nativeGetRegions(this.f9210a), 0, 0);
        }
        throw new IllegalStateException();
    }

    /* renamed from: j */
    private Pixa m20133j() {
        if (!this.f9212l) {
            return new Pixa(nativeGetTextlines(this.f9210a), 0, 0);
        }
        throw new IllegalStateException();
    }

    /* renamed from: k */
    private Pixa m20132k() {
        if (!this.f9212l) {
            return new Pixa(nativeGetStrips(this.f9210a), 0, 0);
        }
        throw new IllegalStateException();
    }

    /* renamed from: l */
    private Pixa m20131l() {
        if (!this.f9212l) {
            return new Pixa(nativeGetWords(this.f9210a), 0, 0);
        }
        throw new IllegalStateException();
    }

    /* renamed from: m */
    private Pixa m20130m() {
        if (!this.f9212l) {
            return new Pixa(nativeGetConnectedComponents(this.f9210a), 0, 0);
        }
        throw new IllegalStateException();
    }

    /* renamed from: n */
    private ResultIterator m20129n() {
        if (!this.f9212l) {
            long nativeGetResultIterator = nativeGetResultIterator(this.f9210a);
            if (nativeGetResultIterator == 0) {
                return null;
            }
            return new ResultIterator(nativeGetResultIterator);
        }
        throw new IllegalStateException();
    }

    /* renamed from: b */
    private String m20147b(int i) {
        if (!this.f9212l) {
            return nativeGetHOCRText(this.f9210a, i);
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    private void m20152a(String str) {
        if (!this.f9212l) {
            nativeSetInputName(this.f9210a, str);
            return;
        }
        throw new IllegalStateException();
    }

    /* renamed from: b */
    private void m20145b(String str) {
        if (!this.f9212l) {
            nativeSetOutputName(this.f9210a, str);
            return;
        }
        throw new IllegalStateException();
    }

    /* renamed from: c */
    private void m20141c(String str) {
        if (!this.f9212l) {
            nativeReadConfigFile(this.f9210a, str);
            return;
        }
        throw new IllegalStateException();
    }

    /* renamed from: c */
    private String m20142c(int i) {
        if (!this.f9212l) {
            return nativeGetBoxText(this.f9210a, i);
        }
        throw new IllegalStateException();
    }

    /* renamed from: o */
    private String m20128o() {
        return nativeGetVersion(this.f9210a);
    }

    /* renamed from: p */
    private void m20127p() {
        if (!this.f9212l) {
            nativeStop(this.f9210a);
            return;
        }
        throw new IllegalStateException();
    }

    protected void onProgressValues(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        if (this.f9211k != null) {
            new C1430d(i, new Rect(i2, i8 - i4, i3, i8 - i5), new Rect(i6, i9, i7, i8));
        }
    }

    /* renamed from: a */
    private boolean m20154a(TessPdfRenderer tessPdfRenderer, String str) {
        return nativeBeginDocument(tessPdfRenderer.m20121a(), str);
    }

    /* renamed from: a */
    private boolean m20155a(TessPdfRenderer tessPdfRenderer) {
        return nativeBeginDocument(tessPdfRenderer.m20121a(), "");
    }

    /* renamed from: b */
    private boolean m20146b(TessPdfRenderer tessPdfRenderer) {
        return nativeEndDocument(tessPdfRenderer.m20121a());
    }

    /* renamed from: a */
    private boolean m20156a(Pix pix, String str, TessPdfRenderer tessPdfRenderer) {
        return nativeAddPageToDocument(this.f9210a, pix.m20232a(), str, tessPdfRenderer.m20121a());
    }

    /* renamed from: q */
    private long m20126q() {
        return this.f9210a;
    }

    /* renamed from: a */
    public final boolean m20151a(String str, String str2) {
        String[] split;
        if (str != null) {
            String str3 = !str.endsWith(File.separator) ? str + File.separator : str;
            if (new File(str3).exists()) {
                File file = new File(str3 + "tessdata");
                if (!file.exists() || !file.isDirectory()) {
                    throw new IllegalArgumentException("Data path must contain subfolder tessdata!");
                }
                for (String str4 : str2.split("\\+")) {
                    if (!str4.startsWith("~")) {
                        File file2 = new File(file + File.separator + str4 + ".traineddata");
                        if (!file2.exists()) {
                            throw new IllegalArgumentException("Data file not found at " + file2);
                        }
                    }
                }
                boolean nativeInitOem = nativeInitOem(this.f9210a, str3, str2, 3);
                if (nativeInitOem) {
                    this.f9212l = false;
                }
                return nativeInitOem;
            }
            throw new IllegalArgumentException("Data path does not exist!");
        }
        throw new IllegalArgumentException("Data path must not be null!");
    }
}

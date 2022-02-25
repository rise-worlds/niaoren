package p110z1;

import java.util.EnumMap;
import java.util.Map;

/* compiled from: Result.java */
/* renamed from: z1.ol */
/* loaded from: classes3.dex */
public final class C5422ol {

    /* renamed from: a */
    public final String f22707a;

    /* renamed from: b */
    public final byte[] f22708b;

    /* renamed from: c */
    public final int f22709c;

    /* renamed from: d */
    public ResultPoint[] f22710d;

    /* renamed from: e */
    public final BarcodeFormat f22711e;

    /* renamed from: f */
    public Map<ResultMetadataType, Object> f22712f;

    /* renamed from: g */
    public final long f22713g;

    public C5422ol(String str, byte[] bArr, ResultPoint[] onVarArr, BarcodeFormat fuVar) {
        this(str, bArr, onVarArr, fuVar, System.currentTimeMillis());
    }

    private C5422ol(String str, byte[] bArr, ResultPoint[] onVarArr, BarcodeFormat fuVar, long j) {
        this(str, bArr, bArr == null ? 0 : bArr.length * 8, onVarArr, fuVar, j);
    }

    public C5422ol(String str, byte[] bArr, int i, ResultPoint[] onVarArr, BarcodeFormat fuVar, long j) {
        this.f22707a = str;
        this.f22708b = bArr;
        this.f22709c = i;
        this.f22710d = onVarArr;
        this.f22711e = fuVar;
        this.f22712f = null;
        this.f22713g = j;
    }

    /* renamed from: a */
    private String m1635a() {
        return this.f22707a;
    }

    /* renamed from: b */
    private byte[] m1631b() {
        return this.f22708b;
    }

    /* renamed from: c */
    private int m1630c() {
        return this.f22709c;
    }

    /* renamed from: d */
    private ResultPoint[] m1629d() {
        return this.f22710d;
    }

    /* renamed from: e */
    private BarcodeFormat m1628e() {
        return this.f22711e;
    }

    /* renamed from: f */
    private Map<ResultMetadataType, Object> m1627f() {
        return this.f22712f;
    }

    /* renamed from: a */
    public final void m1633a(ResultMetadataType omVar, Object obj) {
        if (this.f22712f == null) {
            this.f22712f = new EnumMap(ResultMetadataType.class);
        }
        this.f22712f.put(omVar, obj);
    }

    /* renamed from: a */
    public final void m1634a(Map<ResultMetadataType, Object> map) {
        if (map != null) {
            Map<ResultMetadataType, Object> map2 = this.f22712f;
            if (map2 == null) {
                this.f22712f = map;
            } else {
                map2.putAll(map);
            }
        }
    }

    /* renamed from: a */
    private void m1632a(ResultPoint[] onVarArr) {
        ResultPoint[] onVarArr2 = this.f22710d;
        if (onVarArr2 == null) {
            this.f22710d = onVarArr;
        } else if (onVarArr != null && onVarArr.length > 0) {
            ResultPoint[] onVarArr3 = new ResultPoint[onVarArr2.length + onVarArr.length];
            System.arraycopy(onVarArr2, 0, onVarArr3, 0, onVarArr2.length);
            System.arraycopy(onVarArr, 0, onVarArr3, onVarArr2.length, onVarArr.length);
            this.f22710d = onVarArr3;
        }
    }

    /* renamed from: g */
    private long m1626g() {
        return this.f22713g;
    }

    public final String toString() {
        return this.f22707a;
    }
}

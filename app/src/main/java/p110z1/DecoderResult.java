package p110z1;

import java.util.List;

/* renamed from: z1.ig */
/* loaded from: classes3.dex */
public final class DecoderResult {

    /* renamed from: a */
    public final byte[] f21989a;

    /* renamed from: b */
    public int f21990b;

    /* renamed from: c */
    public final String f21991c;

    /* renamed from: d */
    public final List<byte[]> f21992d;

    /* renamed from: e */
    public final String f21993e;

    /* renamed from: f */
    public Integer f21994f;

    /* renamed from: g */
    public Integer f21995g;

    /* renamed from: h */
    public Object f21996h;

    /* renamed from: i */
    public final int f21997i;

    /* renamed from: j */
    public final int f21998j;

    public DecoderResult(byte[] bArr, String str, List<byte[]> list, String str2) {
        this(bArr, str, list, str2, -1, -1);
    }

    public DecoderResult(byte[] bArr, String str, List<byte[]> list, String str2, int i, int i2) {
        this.f21989a = bArr;
        this.f21990b = bArr == null ? 0 : bArr.length * 8;
        this.f21991c = str;
        this.f21992d = list;
        this.f21993e = str2;
        this.f21997i = i2;
        this.f21998j = i;
    }

    /* renamed from: b */
    private byte[] m2456b() {
        return this.f21989a;
    }

    /* renamed from: c */
    private int m2454c() {
        return this.f21990b;
    }

    /* renamed from: a */
    private void m2459a(int i) {
        this.f21990b = i;
    }

    /* renamed from: d */
    private String m2453d() {
        return this.f21991c;
    }

    /* renamed from: e */
    private List<byte[]> m2452e() {
        return this.f21992d;
    }

    /* renamed from: f */
    private String m2451f() {
        return this.f21993e;
    }

    /* renamed from: g */
    private Integer m2450g() {
        return this.f21994f;
    }

    /* renamed from: a */
    private void m2458a(Integer num) {
        this.f21994f = num;
    }

    /* renamed from: h */
    private Integer m2449h() {
        return this.f21995g;
    }

    /* renamed from: b */
    private void m2455b(Integer num) {
        this.f21995g = num;
    }

    /* renamed from: i */
    private Object m2448i() {
        return this.f21996h;
    }

    /* renamed from: a */
    private void m2457a(Object obj) {
        this.f21996h = obj;
    }

    /* renamed from: a */
    public final boolean m2460a() {
        return this.f21997i >= 0 && this.f21998j >= 0;
    }

    /* renamed from: j */
    private int m2447j() {
        return this.f21997i;
    }

    /* renamed from: k */
    private int m2446k() {
        return this.f21998j;
    }
}

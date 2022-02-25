package com.tendcloud.tenddata;

import p110z1.cin;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.jd */
/* loaded from: classes2.dex */
public final class C2999jd {

    /* renamed from: k */
    private static final int f14303k = 64;

    /* renamed from: l */
    private static final int f14304l = 67108864;

    /* renamed from: a */
    private final byte[] f14305a;

    /* renamed from: b */
    private int f14306b;

    /* renamed from: c */
    private int f14307c;

    /* renamed from: d */
    private int f14308d;

    /* renamed from: e */
    private int f14309e;

    /* renamed from: f */
    private int f14310f;

    /* renamed from: h */
    private int f14312h;

    /* renamed from: g */
    private int f14311g = Integer.MAX_VALUE;

    /* renamed from: i */
    private int f14313i = 64;

    /* renamed from: j */
    private int f14314j = f14304l;

    /* renamed from: a */
    public static long m15376a(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    /* renamed from: b */
    public static int m15371b(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    /* renamed from: a */
    public static C2999jd m15374a(byte[] bArr) {
        return m15373a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public static C2999jd m15373a(byte[] bArr, int i, int i2) {
        return new C2999jd(bArr, i, i2);
    }

    /* renamed from: a */
    public int m15378a() {
        if (m15348w()) {
            this.f14310f = 0;
            return 0;
        }
        this.f14310f = m15352s();
        int i = this.f14310f;
        if (i != 0) {
            return i;
        }
        throw C3009jl.m15264d();
    }

    public void checkLastTagWas(int i) {
        if (this.f14310f != i) {
            throw C3009jl.m15263e();
        }
    }

    /* renamed from: a */
    public boolean m15377a(int i) {
        switch (C3013jp.m15243a(i)) {
            case 0:
                m15364g();
                return true;
            case 1:
                m15349v();
                return true;
            case 2:
                skipRawBytes(m15352s());
                return true;
            case 3:
                m15372b();
                checkLastTagWas(C3013jp.m15242a(C3013jp.m15240b(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                m15350u();
                return true;
            default:
                throw C3009jl.m15262f();
        }
    }

    /* renamed from: b */
    public void m15372b() {
        int a;
        do {
            a = m15378a();
            if (a == 0) {
                return;
            }
        } while (m15377a(a));
    }

    /* renamed from: c */
    public double m15370c() {
        return Double.longBitsToDouble(m15349v());
    }

    /* renamed from: d */
    public float m15368d() {
        return Float.intBitsToFloat(m15350u());
    }

    /* renamed from: e */
    public long m15366e() {
        return m15351t();
    }

    /* renamed from: f */
    public long m15365f() {
        return m15351t();
    }

    /* renamed from: g */
    public int m15364g() {
        return m15352s();
    }

    /* renamed from: h */
    public long m15363h() {
        return m15349v();
    }

    /* renamed from: i */
    public int m15362i() {
        return m15350u();
    }

    /* renamed from: j */
    public boolean m15361j() {
        return m15352s() != 0;
    }

    /* renamed from: k */
    public String m15360k() {
        int s = m15352s();
        int i = this.f14307c;
        int i2 = this.f14309e;
        if (s > i - i2 || s <= 0) {
            return new String(m15367d(s), "UTF-8");
        }
        String str = new String(this.f14305a, i2, s, "UTF-8");
        this.f14309e += s;
        return str;
    }

    /* renamed from: a */
    public void m15375a(AbstractC3010jm jmVar, int i) {
        int i2 = this.f14312h;
        if (i2 < this.f14313i) {
            this.f14312h = i2 + 1;
            jmVar.m15259a(this);
            checkLastTagWas(C3013jp.m15242a(i, 4));
            this.f14312h--;
            return;
        }
        throw C3009jl.m15261g();
    }

    public void readMessage(AbstractC3010jm jmVar) {
        int s = m15352s();
        if (this.f14312h < this.f14313i) {
            int c = m15369c(s);
            this.f14312h++;
            jmVar.m15259a(this);
            checkLastTagWas(0);
            this.f14312h--;
            popLimit(c);
            return;
        }
        throw C3009jl.m15261g();
    }

    /* renamed from: l */
    public byte[] m15359l() {
        int s = m15352s();
        int i = this.f14307c;
        int i2 = this.f14309e;
        if (s <= i - i2 && s > 0) {
            byte[] bArr = new byte[s];
            System.arraycopy(this.f14305a, i2, bArr, 0, s);
            this.f14309e += s;
            return bArr;
        } else if (s == 0) {
            return C3013jp.f14343i;
        } else {
            return m15367d(s);
        }
    }

    /* renamed from: m */
    public int m15358m() {
        return m15352s();
    }

    /* renamed from: n */
    public int m15357n() {
        return m15352s();
    }

    /* renamed from: o */
    public int m15356o() {
        return m15350u();
    }

    /* renamed from: p */
    public long m15355p() {
        return m15349v();
    }

    /* renamed from: q */
    public int m15354q() {
        return m15371b(m15352s());
    }

    /* renamed from: r */
    public long m15353r() {
        return m15376a(m15351t());
    }

    /* renamed from: s */
    public int m15352s() {
        byte x = m15347x();
        if (x >= 0) {
            return x;
        }
        int i = x & cin.f20710b;
        byte x2 = m15347x();
        if (x2 >= 0) {
            return i | (x2 << 7);
        }
        int i2 = i | ((x2 & cin.f20710b) << 7);
        byte x3 = m15347x();
        if (x3 >= 0) {
            return i2 | (x3 << 14);
        }
        int i3 = i2 | ((x3 & cin.f20710b) << 14);
        byte x4 = m15347x();
        if (x4 >= 0) {
            return i3 | (x4 << 21);
        }
        int i4 = i3 | ((x4 & cin.f20710b) << 21);
        byte x5 = m15347x();
        int i5 = i4 | (x5 << 28);
        if (x5 >= 0) {
            return i5;
        }
        for (int i6 = 0; i6 < 5; i6++) {
            if (m15347x() >= 0) {
                return i5;
            }
        }
        throw C3009jl.m15265c();
    }

    /* renamed from: t */
    public long m15351t() {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte x = m15347x();
            j |= (x & cin.f20710b) << i;
            if ((x & cin.f20709a) == 0) {
                return j;
            }
        }
        throw C3009jl.m15265c();
    }

    /* renamed from: u */
    public int m15350u() {
        return (m15347x() & 255) | ((m15347x() & 255) << 8) | ((m15347x() & 255) << 16) | ((m15347x() & 255) << 24);
    }

    /* renamed from: v */
    public long m15349v() {
        byte x = m15347x();
        byte x2 = m15347x();
        return ((x2 & 255) << 8) | (x & 255) | ((m15347x() & 255) << 16) | ((m15347x() & 255) << 24) | ((m15347x() & 255) << 32) | ((m15347x() & 255) << 40) | ((m15347x() & 255) << 48) | ((m15347x() & 255) << 56);
    }

    private C2999jd(byte[] bArr, int i, int i2) {
        this.f14305a = bArr;
        this.f14306b = i;
        this.f14307c = i2 + i;
        this.f14309e = i;
    }

    /* renamed from: c */
    public int m15369c(int i) {
        if (i >= 0) {
            int i2 = i + this.f14309e;
            int i3 = this.f14311g;
            if (i2 <= i3) {
                this.f14311g = i2;
                m15346y();
                return i3;
            }
            throw C3009jl.m15267a();
        }
        throw C3009jl.m15266b();
    }

    /* renamed from: y */
    private void m15346y() {
        this.f14307c += this.f14308d;
        int i = this.f14307c;
        int i2 = this.f14311g;
        if (i > i2) {
            this.f14308d = i - i2;
            this.f14307c = i - this.f14308d;
            return;
        }
        this.f14308d = 0;
    }

    public void popLimit(int i) {
        this.f14311g = i;
        m15346y();
    }

    /* renamed from: w */
    public boolean m15348w() {
        return this.f14309e == this.f14307c;
    }

    /* renamed from: x */
    public byte m15347x() {
        int i = this.f14309e;
        if (i != this.f14307c) {
            byte[] bArr = this.f14305a;
            this.f14309e = i + 1;
            return bArr[i];
        }
        throw C3009jl.m15267a();
    }

    /* renamed from: d */
    public byte[] m15367d(int i) {
        if (i >= 0) {
            int i2 = this.f14309e;
            int i3 = i2 + i;
            int i4 = this.f14311g;
            if (i3 > i4) {
                skipRawBytes(i4 - i2);
                throw C3009jl.m15267a();
            } else if (i <= this.f14307c - i2) {
                byte[] bArr = new byte[i];
                System.arraycopy(this.f14305a, i2, bArr, 0, i);
                this.f14309e += i;
                return bArr;
            } else {
                throw C3009jl.m15267a();
            }
        } else {
            throw C3009jl.m15266b();
        }
    }

    public void skipRawBytes(int i) {
        if (i >= 0) {
            int i2 = this.f14309e;
            int i3 = i2 + i;
            int i4 = this.f14311g;
            if (i3 > i4) {
                skipRawBytes(i4 - i2);
                throw C3009jl.m15267a();
            } else if (i <= this.f14307c - i2) {
                this.f14309e = i2 + i;
            } else {
                throw C3009jl.m15267a();
            }
        } else {
            throw C3009jl.m15266b();
        }
    }
}

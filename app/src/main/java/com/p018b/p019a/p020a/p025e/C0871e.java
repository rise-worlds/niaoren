package com.p018b.p019a.p020a.p025e;

import com.p018b.p029b.BufferedSource;
import com.p018b.p029b.ByteString;
import com.p018b.p029b.Okio;
import com.p018b.p029b.Source;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Hpack.java */
/* renamed from: com.b.a.a.e.e */
/* loaded from: classes.dex */
public final class C0871e {

    /* renamed from: a */
    Header[] f5907a;

    /* renamed from: b */
    int f5908b;

    /* renamed from: c */
    int f5909c;

    /* renamed from: d */
    int f5910d;

    /* renamed from: e */
    private final List<Header> f5911e;

    /* renamed from: f */
    private final BufferedSource f5912f;

    /* renamed from: g */
    private final int f5913g;

    /* renamed from: h */
    private int f5914h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0871e(Source xVar) {
        this(xVar, (byte) 0);
    }

    private C0871e(Source xVar, byte b) {
        this.f5911e = new ArrayList();
        this.f5907a = new Header[8];
        this.f5908b = this.f5907a.length - 1;
        this.f5909c = 0;
        this.f5910d = 0;
        this.f5913g = 4096;
        this.f5914h = 4096;
        this.f5912f = Okio.m24305a(xVar);
    }

    /* renamed from: c */
    private void m24657c() {
        int i = this.f5914h;
        int i2 = this.f5910d;
        if (i >= i2) {
            return;
        }
        if (i == 0) {
            m24655d();
        } else {
            m24662a(i2 - i);
        }
    }

    /* renamed from: d */
    private void m24655d() {
        Arrays.fill(this.f5907a, (Object) null);
        this.f5908b = this.f5907a.length - 1;
        this.f5909c = 0;
        this.f5910d = 0;
    }

    /* renamed from: a */
    private int m24662a(int i) {
        int i2 = 0;
        if (i > 0) {
            int length = this.f5907a.length;
            while (true) {
                length--;
                if (length < this.f5908b || i <= 0) {
                    break;
                }
                i -= this.f5907a[length].f5904i;
                this.f5910d -= this.f5907a[length].f5904i;
                this.f5909c--;
                i2++;
            }
            Header[] cVarArr = this.f5907a;
            int i3 = this.f5908b;
            System.arraycopy(cVarArr, i3 + 1, cVarArr, i3 + 1 + i2, this.f5909c);
            this.f5908b += i2;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m24663a() {
        while (!this.f5912f.mo24282d()) {
            int e = this.f5912f.mo24281e() & 255;
            if (e == 128) {
                throw new IOException("index == 0");
            } else if ((e & 128) == 128) {
                int a = m24661a(e, 127) - 1;
                if (m24654d(a)) {
                    this.f5911e.add(Hpack.f5905a[a]);
                } else {
                    int b = m24658b(a - Hpack.f5905a.length);
                    if (b >= 0) {
                        Header[] cVarArr = this.f5907a;
                        if (b <= cVarArr.length - 1) {
                            this.f5911e.add(cVarArr[b]);
                        }
                    }
                    throw new IOException("Header index too large " + (a + 1));
                }
            } else if (e == 64) {
                m24660a(new Header(Hpack.m24664a(m24652f()), m24652f()));
            } else if ((e & 64) == 64) {
                m24660a(new Header(m24656c(m24661a(e, 63) - 1), m24652f()));
            } else if ((e & 32) == 32) {
                this.f5914h = m24661a(e, 31);
                int i = this.f5914h;
                if (i < 0 || i > this.f5913g) {
                    throw new IOException("Invalid dynamic table size update " + this.f5914h);
                }
                m24657c();
            } else if (e == 16 || e == 0) {
                this.f5911e.add(new Header(Hpack.m24664a(m24652f()), m24652f()));
            } else {
                this.f5911e.add(new Header(m24656c(m24661a(e, 15) - 1), m24652f()));
            }
        }
    }

    /* renamed from: b */
    public final List<Header> m24659b() {
        ArrayList arrayList = new ArrayList(this.f5911e);
        this.f5911e.clear();
        return arrayList;
    }

    /* renamed from: b */
    private int m24658b(int i) {
        return this.f5908b + 1 + i;
    }

    /* renamed from: c */
    private ByteString m24656c(int i) {
        if (m24654d(i)) {
            return Hpack.f5905a[i].f5902g;
        }
        return this.f5907a[m24658b(i - Hpack.f5905a.length)].f5902g;
    }

    /* renamed from: d */
    private static boolean m24654d(int i) {
        return i >= 0 && i <= Hpack.f5905a.length - 1;
    }

    /* renamed from: a */
    private void m24660a(Header cVar) {
        this.f5911e.add(cVar);
        int i = cVar.f5904i;
        int i2 = this.f5914h;
        if (i > i2) {
            m24655d();
            return;
        }
        m24662a((this.f5910d + i) - i2);
        int i3 = this.f5909c + 1;
        Header[] cVarArr = this.f5907a;
        if (i3 > cVarArr.length) {
            Header[] cVarArr2 = new Header[cVarArr.length * 2];
            System.arraycopy(cVarArr, 0, cVarArr2, cVarArr.length, cVarArr.length);
            this.f5908b = this.f5907a.length - 1;
            this.f5907a = cVarArr2;
        }
        int i4 = this.f5908b;
        this.f5908b = i4 - 1;
        this.f5907a[i4] = cVar;
        this.f5909c++;
        this.f5910d += i;
    }

    /* renamed from: e */
    private int m24653e() {
        return this.f5912f.mo24281e() & 255;
    }

    /* renamed from: a */
    private int m24661a(int i, int i2) {
        int i3 = i & i2;
        if (i3 < i2) {
            return i3;
        }
        int i4 = 0;
        while (true) {
            int e = m24653e();
            if ((e & 128) == 0) {
                return i2 + (e << i4);
            }
            i2 += (e & 127) << i4;
            i4 += 7;
        }
    }

    /* renamed from: f */
    private ByteString m24652f() {
        int e = m24653e();
        boolean z = (e & 128) == 128;
        int a = m24661a(e, 127);
        if (z) {
            return ByteString.m24314a(Huffman.m24685a().m24682a(this.f5912f.mo24280e(a)));
        }
        return this.f5912f.mo24283c(a);
    }
}

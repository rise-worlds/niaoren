package com.p018b.p019a.p020a.p025e;

import com.p018b.p019a.p020a.Util;
import com.p018b.p029b.Buffer;
import com.p018b.p029b.ByteString;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Hpack.java */
/* renamed from: com.b.a.a.e.f */
/* loaded from: classes.dex */
public final class C0872f {

    /* renamed from: a */
    int f5915a;

    /* renamed from: b */
    int f5916b;

    /* renamed from: c */
    Header[] f5917c;

    /* renamed from: d */
    int f5918d;

    /* renamed from: e */
    int f5919e;

    /* renamed from: f */
    int f5920f;

    /* renamed from: g */
    private final Buffer f5921g;

    /* renamed from: h */
    private final boolean f5922h;

    /* renamed from: i */
    private int f5923i;

    /* renamed from: j */
    private boolean f5924j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0872f(Buffer fVar) {
        this(fVar, (byte) 0);
    }

    private C0872f(Buffer fVar, byte b) {
        this.f5923i = Integer.MAX_VALUE;
        this.f5917c = new Header[8];
        this.f5918d = this.f5917c.length - 1;
        this.f5919e = 0;
        this.f5920f = 0;
        this.f5915a = 4096;
        this.f5916b = 4096;
        this.f5922h = true;
        this.f5921g = fVar;
    }

    /* renamed from: a */
    private void m24651a() {
        Arrays.fill(this.f5917c, (Object) null);
        this.f5918d = this.f5917c.length - 1;
        this.f5919e = 0;
        this.f5920f = 0;
    }

    /* renamed from: b */
    private int m24645b(int i) {
        int i2 = 0;
        if (i > 0) {
            int length = this.f5917c.length;
            while (true) {
                length--;
                if (length < this.f5918d || i <= 0) {
                    break;
                }
                i -= this.f5917c[length].f5904i;
                this.f5920f -= this.f5917c[length].f5904i;
                this.f5919e--;
                i2++;
            }
            Header[] cVarArr = this.f5917c;
            int i3 = this.f5918d;
            System.arraycopy(cVarArr, i3 + 1, cVarArr, i3 + 1 + i2, this.f5919e);
            Header[] cVarArr2 = this.f5917c;
            int i4 = this.f5918d;
            Arrays.fill(cVarArr2, i4 + 1, i4 + 1 + i2, (Object) null);
            this.f5918d += i2;
        }
        return i2;
    }

    /* renamed from: a */
    private void m24648a(Header cVar) {
        int i = cVar.f5904i;
        int i2 = this.f5916b;
        if (i > i2) {
            m24651a();
            return;
        }
        m24645b((this.f5920f + i) - i2);
        int i3 = this.f5919e + 1;
        Header[] cVarArr = this.f5917c;
        if (i3 > cVarArr.length) {
            Header[] cVarArr2 = new Header[cVarArr.length * 2];
            System.arraycopy(cVarArr, 0, cVarArr2, cVarArr.length, cVarArr.length);
            this.f5918d = this.f5917c.length - 1;
            this.f5917c = cVarArr2;
        }
        int i4 = this.f5918d;
        this.f5918d = i4 - 1;
        this.f5917c[i4] = cVar;
        this.f5919e++;
        this.f5920f += i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m24646a(List<Header> list) {
        int i;
        int i2;
        if (this.f5924j) {
            int i3 = this.f5923i;
            if (i3 < this.f5916b) {
                m24649a(i3, 31, 32);
            }
            this.f5924j = false;
            this.f5923i = Integer.MAX_VALUE;
            m24649a(this.f5916b, 31, 32);
        }
        int size = list.size();
        for (int i4 = 0; i4 < size; i4++) {
            Header cVar = list.get(i4);
            ByteString f = cVar.f5902g.mo24256f();
            ByteString iVar = cVar.f5903h;
            Integer num = Hpack.f5906b.get(f);
            if (num != null) {
                i2 = num.intValue() + 1;
                if (i2 > 1 && i2 < 8) {
                    if (Util.m24761a(Hpack.f5905a[i2 - 1].f5903h, iVar)) {
                        i = i2;
                    } else if (Util.m24761a(Hpack.f5905a[i2].f5903h, iVar)) {
                        i2++;
                        i = i2;
                    }
                }
                i = i2;
                i2 = -1;
            } else {
                i2 = -1;
                i = -1;
            }
            if (i2 == -1) {
                int i5 = this.f5918d + 1;
                int length = this.f5917c.length;
                while (true) {
                    if (i5 >= length) {
                        break;
                    }
                    if (Util.m24761a(this.f5917c[i5].f5902g, f)) {
                        if (Util.m24761a(this.f5917c[i5].f5903h, iVar)) {
                            i2 = Hpack.f5905a.length + (i5 - this.f5918d);
                            break;
                        } else if (i == -1) {
                            i = (i5 - this.f5918d) + Hpack.f5905a.length;
                        }
                    }
                    i5++;
                }
            }
            if (i2 != -1) {
                m24649a(i2, 127, 128);
            } else if (i == -1) {
                this.f5921g.mo24293h(64);
                m24647a(f);
                m24647a(iVar);
                m24648a(cVar);
            } else {
                ByteString iVar2 = Header.f5896a;
                if (!f.mo24264a(0, iVar2, 0, iVar2.mo24255g()) || Header.f5901f.equals(f)) {
                    m24649a(i, 63, 64);
                    m24647a(iVar);
                    m24648a(cVar);
                } else {
                    m24649a(i, 15, 0);
                    m24647a(iVar);
                }
            }
        }
    }

    /* renamed from: a */
    private void m24649a(int i, int i2, int i3) {
        if (i < i2) {
            this.f5921g.mo24293h(i | i3);
            return;
        }
        this.f5921g.mo24293h(i3 | i2);
        int i4 = i - i2;
        while (i4 >= 128) {
            this.f5921g.mo24293h(128 | (i4 & 127));
            i4 >>>= 7;
        }
        this.f5921g.mo24293h(i4);
    }

    /* renamed from: a */
    private void m24647a(ByteString iVar) {
        if (this.f5922h) {
            Huffman.m24685a();
            if (Huffman.m24684a(iVar) < iVar.mo24255g()) {
                Buffer fVar = new Buffer();
                Huffman.m24685a();
                Huffman.m24683a(iVar, fVar);
                ByteString k = fVar.m24321k();
                m24649a(k.mo24255g(), 127, 128);
                this.f5921g.m24337a(k);
                return;
            }
        }
        m24649a(iVar.mo24255g(), 127, 0);
        this.f5921g.m24337a(iVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m24650a(int i) {
        this.f5915a = i;
        int min = Math.min(i, 16384);
        int i2 = this.f5916b;
        if (i2 != min) {
            if (min < i2) {
                this.f5923i = Math.min(this.f5923i, min);
            }
            this.f5924j = true;
            this.f5916b = min;
            int i3 = this.f5916b;
            int i4 = this.f5920f;
            if (i3 >= i4) {
                return;
            }
            if (i3 == 0) {
                m24651a();
            } else {
                m24645b(i4 - i3);
            }
        }
    }
}

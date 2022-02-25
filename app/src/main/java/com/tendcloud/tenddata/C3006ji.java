package com.tendcloud.tenddata;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ji */
/* loaded from: classes2.dex */
public final class C3006ji implements Cloneable {

    /* renamed from: a */
    private static final C3007jj f14320a = new C3007jj();

    /* renamed from: b */
    private boolean f14321b;

    /* renamed from: c */
    private int[] f14322c;

    /* renamed from: d */
    private C3007jj[] f14323d;

    /* renamed from: e */
    private int f14324e;

    /* renamed from: c */
    private int m15281c(int i) {
        for (int i2 = 4; i2 < 32; i2++) {
            int i3 = (1 << i2) - 12;
            if (i <= i3) {
                return i3;
            }
        }
        return i;
    }

    C3006ji() {
        this(10);
    }

    C3006ji(int i) {
        this.f14321b = false;
        int b = m15283b(i);
        this.f14322c = new int[b];
        this.f14323d = new C3007jj[b];
        this.f14324e = 0;
    }

    /* renamed from: d */
    private void m15280d() {
        int i = this.f14324e;
        int[] iArr = this.f14322c;
        C3007jj[] jjVarArr = this.f14323d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            C3007jj jjVar = jjVarArr[i3];
            if (jjVar != f14320a) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    jjVarArr[i2] = jjVar;
                    jjVarArr[i3] = null;
                }
                i2++;
            }
        }
        this.f14321b = false;
        this.f14324e = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public int m15288a() {
        if (this.f14321b) {
            m15280d();
        }
        return this.f14324e;
    }

    /* renamed from: b */
    public boolean m15284b() {
        return m15288a() == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public C3007jj m15287a(int i) {
        if (this.f14321b) {
            m15280d();
        }
        return this.f14323d[i];
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C3006ji)) {
            return false;
        }
        C3006ji jiVar = (C3006ji) obj;
        return m15288a() == jiVar.m15288a() && m15286a(this.f14322c, jiVar.f14322c, this.f14324e) && m15285a(this.f14323d, jiVar.f14323d, this.f14324e);
    }

    public int hashCode() {
        if (this.f14321b) {
            m15280d();
        }
        int i = 17;
        for (int i2 = 0; i2 < this.f14324e; i2++) {
            i = (((i * 31) + this.f14322c[i2]) * 31) + this.f14323d[i2].hashCode();
        }
        return i;
    }

    /* renamed from: b */
    private int m15283b(int i) {
        return m15281c(i * 4) / 4;
    }

    /* renamed from: a */
    private boolean m15286a(int[] iArr, int[] iArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    private boolean m15285a(C3007jj[] jjVarArr, C3007jj[] jjVarArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (!jjVarArr[i2].equals(jjVarArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: c */
    public final C3006ji clone() {
        int a = m15288a();
        C3006ji jiVar = new C3006ji(a);
        System.arraycopy(this.f14322c, 0, jiVar.f14322c, 0, a);
        for (int i = 0; i < a; i++) {
            C3007jj[] jjVarArr = this.f14323d;
            if (jjVarArr[i] != null) {
                jiVar.f14323d[i] = jjVarArr[i].clone();
            }
        }
        jiVar.f14324e = a;
        return jiVar;
    }
}

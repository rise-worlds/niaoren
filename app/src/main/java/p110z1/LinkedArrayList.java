package p110z1;

import java.util.ArrayList;

/* renamed from: z1.bsy */
/* loaded from: classes3.dex */
public class LinkedArrayList {

    /* renamed from: a */
    final int f20065a;

    /* renamed from: b */
    Object[] f20066b;

    /* renamed from: c */
    Object[] f20067c;

    /* renamed from: d */
    volatile int f20068d;

    /* renamed from: e */
    int f20069e;

    public LinkedArrayList(int i) {
        this.f20065a = i;
    }

    /* renamed from: a */
    public void m9420a(Object obj) {
        if (this.f20068d == 0) {
            this.f20066b = new Object[this.f20065a + 1];
            Object[] objArr = this.f20066b;
            this.f20067c = objArr;
            objArr[0] = obj;
            this.f20069e = 1;
            this.f20068d = 1;
            return;
        }
        int i = this.f20069e;
        int i2 = this.f20065a;
        if (i == i2) {
            Object[] objArr2 = new Object[i2 + 1];
            objArr2[0] = obj;
            this.f20067c[i2] = objArr2;
            this.f20067c = objArr2;
            this.f20069e = 1;
            this.f20068d++;
            return;
        }
        this.f20067c[i] = obj;
        this.f20069e = i + 1;
        this.f20068d++;
    }

    /* renamed from: a */
    public Object[] m9421a() {
        return this.f20066b;
    }

    /* renamed from: b */
    public int m9419b() {
        return this.f20068d;
    }

    public String toString() {
        int i = this.f20065a;
        int i2 = this.f20068d;
        ArrayList arrayList = new ArrayList(i2 + 1);
        Object[] a = m9421a();
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            arrayList.add(a[i4]);
            i3++;
            i4++;
            if (i4 == i) {
                a = (Object[]) a[i];
                i4 = 0;
            }
        }
        return arrayList.toString();
    }
}

package com.tendcloud.tenddata;

import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.BitSet;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ax */
/* loaded from: classes2.dex */
public final class C2690ax {

    /* renamed from: a */
    private static final int f13552a = 1048576;

    /* renamed from: b */
    private static final int[] f13553b = {5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53};

    /* renamed from: c */
    private BitSet f13554c = new BitSet(1048576);

    /* renamed from: d */
    private C2691a[] f13555d = new C2691a[f13553b.length];

    private C2690ax() {
        int i = 0;
        while (true) {
            int[] iArr = f13553b;
            if (i < iArr.length) {
                this.f13555d[i] = new C2691a(1048576, iArr[i]);
                i++;
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    public static C2690ax m16282a(String str) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(str.getBytes(), 0)));
            Object readObject = objectInputStream.readObject();
            objectInputStream.close();
            C2690ax axVar = new C2690ax();
            axVar.f13554c = (BitSet) readObject;
            return axVar;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: b */
    public boolean m16281b(String str) {
        if (str == null) {
            return false;
        }
        boolean z = true;
        for (C2691a aVar : this.f13555d) {
            z = z && this.f13554c.get(aVar.hash(str));
        }
        return z;
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.ax$a */
    /* loaded from: classes2.dex */
    static class C2691a {
        private int cap;
        private int seed;

        C2691a(int i, int i2) {
            this.cap = i;
            this.seed = i2;
        }

        int hash(String str) {
            int length = str.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                i = str.charAt(i2) + (this.seed * i);
            }
            return (this.cap - 1) & i;
        }
    }
}

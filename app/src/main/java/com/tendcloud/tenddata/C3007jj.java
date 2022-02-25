package com.tendcloud.tenddata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.jj */
/* loaded from: classes2.dex */
class C3007jj implements Cloneable {

    /* renamed from: a */
    private C3003jg f14325a;

    /* renamed from: b */
    private Object f14326b;

    /* renamed from: c */
    private List f14327c = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public int m15279a() {
        Object obj = this.f14326b;
        if (obj != null) {
            return this.f14325a.computeSerializedSize(obj);
        }
        int i = 0;
        for (C3012jo joVar : this.f14327c) {
            i += joVar.m15245a();
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m15278a(C3000je jeVar) {
        Object obj = this.f14326b;
        if (obj != null) {
            this.f14325a.writeTo(obj, jeVar);
            return;
        }
        for (C3012jo joVar : this.f14327c) {
            joVar.m15244a(jeVar);
        }
    }

    public boolean equals(Object obj) {
        List list;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C3007jj)) {
            return false;
        }
        C3007jj jjVar = (C3007jj) obj;
        if (this.f14326b == null || jjVar.f14326b == null) {
            List list2 = this.f14327c;
            if (list2 != null && (list = jjVar.f14327c) != null) {
                return list2.equals(list);
            }
            try {
                return Arrays.equals(m15276c(), jjVar.m15276c());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else {
            C3003jg jgVar = this.f14325a;
            if (jgVar != jjVar.f14325a) {
                return false;
            }
            if (!jgVar.clazz.isArray()) {
                return this.f14326b.equals(jjVar.f14326b);
            }
            Object obj2 = this.f14326b;
            if (obj2 instanceof byte[]) {
                return Arrays.equals((byte[]) obj2, (byte[]) jjVar.f14326b);
            }
            if (obj2 instanceof int[]) {
                return Arrays.equals((int[]) obj2, (int[]) jjVar.f14326b);
            }
            if (obj2 instanceof long[]) {
                return Arrays.equals((long[]) obj2, (long[]) jjVar.f14326b);
            }
            if (obj2 instanceof float[]) {
                return Arrays.equals((float[]) obj2, (float[]) jjVar.f14326b);
            }
            if (obj2 instanceof double[]) {
                return Arrays.equals((double[]) obj2, (double[]) jjVar.f14326b);
            }
            if (obj2 instanceof boolean[]) {
                return Arrays.equals((boolean[]) obj2, (boolean[]) jjVar.f14326b);
            }
            return Arrays.deepEquals((Object[]) obj2, (Object[]) jjVar.f14326b);
        }
    }

    public int hashCode() {
        try {
            return 527 + Arrays.hashCode(m15276c());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* renamed from: c */
    private byte[] m15276c() {
        byte[] bArr = new byte[m15279a()];
        m15278a(C3000je.m15325a(bArr));
        return bArr;
    }

    /* renamed from: b */
    public final C3007jj clone() {
        C3007jj jjVar = new C3007jj();
        try {
            jjVar.f14325a = this.f14325a;
            if (this.f14327c == null) {
                jjVar.f14327c = null;
            } else {
                jjVar.f14327c.addAll(this.f14327c);
            }
            if (this.f14326b != null) {
                if (this.f14326b instanceof AbstractC3010jm) {
                    jjVar.f14326b = ((AbstractC3010jm) this.f14326b).clone();
                } else if (this.f14326b instanceof byte[]) {
                    jjVar.f14326b = ((byte[]) this.f14326b).clone();
                } else {
                    int i = 0;
                    if (this.f14326b instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) this.f14326b;
                        byte[][] bArr2 = new byte[bArr.length];
                        jjVar.f14326b = bArr2;
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                    } else if (this.f14326b instanceof boolean[]) {
                        jjVar.f14326b = ((boolean[]) this.f14326b).clone();
                    } else if (this.f14326b instanceof int[]) {
                        jjVar.f14326b = ((int[]) this.f14326b).clone();
                    } else if (this.f14326b instanceof long[]) {
                        jjVar.f14326b = ((long[]) this.f14326b).clone();
                    } else if (this.f14326b instanceof float[]) {
                        jjVar.f14326b = ((float[]) this.f14326b).clone();
                    } else if (this.f14326b instanceof double[]) {
                        jjVar.f14326b = ((double[]) this.f14326b).clone();
                    } else if (this.f14326b instanceof AbstractC3010jm[]) {
                        AbstractC3010jm[] jmVarArr = (AbstractC3010jm[]) this.f14326b;
                        AbstractC3010jm[] jmVarArr2 = new AbstractC3010jm[jmVarArr.length];
                        jjVar.f14326b = jmVarArr2;
                        while (i < jmVarArr.length) {
                            jmVarArr2[i] = jmVarArr[i].clone();
                            i++;
                        }
                    }
                }
            }
            return jjVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}

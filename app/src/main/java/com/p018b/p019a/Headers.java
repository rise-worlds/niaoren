package com.p018b.p019a;

import java.util.Arrays;
import java.util.Collections;

/* renamed from: com.b.a.aa */
/* loaded from: classes.dex */
public final class Headers {

    /* renamed from: a */
    private final String[] f6057a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Headers(C0896ab abVar) {
        this.f6057a = (String[]) abVar.f6058a.toArray(new String[abVar.f6058a.size()]);
    }

    /* renamed from: a */
    public final String m24557a(String str) {
        String[] strArr = this.f6057a;
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    /* renamed from: a */
    public final int m24559a() {
        return this.f6057a.length / 2;
    }

    /* renamed from: a */
    public final String m24558a(int i) {
        return this.f6057a[i * 2];
    }

    /* renamed from: b */
    public final String m24555b(int i) {
        return this.f6057a[(i * 2) + 1];
    }

    /* renamed from: b */
    public final C0896ab m24556b() {
        C0896ab abVar = new C0896ab();
        Collections.addAll(abVar.f6058a, this.f6057a);
        return abVar;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof Headers) && Arrays.equals(((Headers) obj).f6057a, this.f6057a);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f6057a);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int length = this.f6057a.length / 2;
        for (int i = 0; i < length; i++) {
            sb.append(m24558a(i));
            sb.append(": ");
            sb.append(m24555b(i));
            sb.append("\n");
        }
        return sb.toString();
    }
}

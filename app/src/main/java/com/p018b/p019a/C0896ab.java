package com.p018b.p019a;

import com.p018b.p019a.p020a.Util;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Headers.java */
/* renamed from: com.b.a.ab */
/* loaded from: classes.dex */
public final class C0896ab {

    /* renamed from: a */
    final List<String> f6058a = new ArrayList(20);

    /* renamed from: a */
    public final C0896ab m24552a(String str, String str2) {
        m24549d(str, str2);
        return m24551b(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final C0896ab m24551b(String str, String str2) {
        this.f6058a.add(str);
        this.f6058a.add(str2.trim());
        return this;
    }

    /* renamed from: a */
    public final C0896ab m24553a(String str) {
        int i = 0;
        while (i < this.f6058a.size()) {
            if (str.equalsIgnoreCase(this.f6058a.get(i))) {
                this.f6058a.remove(i);
                this.f6058a.remove(i);
                i -= 2;
            }
            i += 2;
        }
        return this;
    }

    /* renamed from: c */
    public final C0896ab m24550c(String str, String str2) {
        m24549d(str, str2);
        m24553a(str);
        m24551b(str, str2);
        return this;
    }

    /* renamed from: d */
    private static void m24549d(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (!str.isEmpty()) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (charAt <= ' ' || charAt >= 127) {
                    throw new IllegalArgumentException(Util.m24755a("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(charAt), Integer.valueOf(i), str));
                }
            }
            if (str2 != null) {
                int length2 = str2.length();
                for (int i2 = 0; i2 < length2; i2++) {
                    char charAt2 = str2.charAt(i2);
                    if ((charAt2 <= 31 && charAt2 != '\t') || charAt2 >= 127) {
                        throw new IllegalArgumentException(Util.m24755a("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(charAt2), Integer.valueOf(i2), str, str2));
                    }
                }
                return;
            }
            throw new NullPointerException("value == null");
        } else {
            throw new IllegalArgumentException("name is empty");
        }
    }

    /* renamed from: a */
    public final Headers m24554a() {
        return new Headers(this);
    }
}

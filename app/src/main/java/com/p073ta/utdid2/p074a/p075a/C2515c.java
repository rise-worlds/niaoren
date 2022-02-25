package com.p073ta.utdid2.p074a.p075a;

import android.os.Build;

/* renamed from: com.ta.utdid2.a.a.c */
/* loaded from: classes2.dex */
public class C2515c {
    /* renamed from: a */
    public static boolean m17177a() {
        if (Build.VERSION.SDK_INT < 29) {
            return Build.VERSION.CODENAME.length() == 1 && Build.VERSION.CODENAME.charAt(0) >= 'Q' && Build.VERSION.CODENAME.charAt(0) <= 'Z';
        }
        return true;
    }
}

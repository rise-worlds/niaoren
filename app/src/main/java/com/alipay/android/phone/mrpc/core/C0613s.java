package com.alipay.android.phone.mrpc.core;

import android.content.Context;

/* renamed from: com.alipay.android.phone.mrpc.core.s */
/* loaded from: classes.dex */
public final class C0613s {

    /* renamed from: a */
    private static Boolean f200a;

    /* renamed from: a */
    public static final boolean m25452a(Context context) {
        Boolean bool = f200a;
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            Boolean valueOf = Boolean.valueOf((context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 2) != 0);
            f200a = valueOf;
            return valueOf.booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }
}
